package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.instance.litterBeach.LBItemEntity;
import nz.govt.doc.t1m.domain.instance.litterLarge.LLItemEntity;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemService;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by McCaulC on 3/02/2015.
 */
@Component
public class LitterInstanceParser {

    @Autowired
    protected LBItemService lbItemService;
    @Autowired
    protected LLItemService llItemService;

    @Autowired
    protected ImageParser imageParser;

    private String[][] instanceField;
    private String[][] instanceData;

    private int count;

    public void initialize(String[][] instanceField, String[][] instanceData) {
        this.instanceField = instanceField;
        this.instanceData = instanceData;
        this.count = 0;
    }

    public void saveLBItem(Integer dataSheetId, int numLBItems){
        for (int i = 0 ; i < numLBItems ; i++) {
            System.out.println("New LBItem instance found");
            LBItemEntity lbItemEntity = new LBItemEntity();
            lbItemEntity.setDataSheetId(dataSheetId);
            String image = null;
            for (int j = 0 ; j < instanceField[count].length ; j++) {
//                System.out.println(instanceField[count][j] + ": " + instanceData[count][j]);
                if (instanceField[count][j].equals("image") || instanceField[count][j].equals("Image")) {
                    image = instanceData[count][j];
                } else {
                    try {
                        Class[] paramString = new Class[1];
                        paramString[0] = String.class;
                        Class classType = Class.forName("nz.govt.doc.t1m.domain.instance.litterBeach.LBItemEntity");
                        Method set = classType.getDeclaredMethod("set" + instanceField[count][j], paramString);
                        set.invoke(lbItemEntity, instanceData[count][j]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            LBItemEntity res = lbItemService.saveLBItem(lbItemEntity);
            if (image != null) System.out.println(imageParser.saveImage(res.getInstanceId(),"LBItem",image).getImageString());
            count++;
        }
    }

    public void saveLLItem(Integer dataSheetId, int numLLItems) {
        for (int i = 0 ; i < numLLItems ; i++) {
            System.out.println("New LLItem instance found");
            LLItemEntity llItemEntity = new LLItemEntity();
            llItemEntity.setDataSheetId(dataSheetId);
            for (int j = 0 ; j < instanceField[count].length ; j++) {
//                System.out.println(instanceField[count][j] + ": " + instanceData[count][j]);
                try {
                    Class[] paramString = new Class[1];
                    paramString[0] = String.class;
                    Class classType = Class.forName("nz.govt.doc.t1m.domain.instance.litterLarge.LLItemEntity");
                    Method set = classType.getDeclaredMethod("set" + instanceField[count][j], paramString);
                    set.invoke(llItemEntity, instanceData[count][j]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LLItemEntity res = llItemService.saveLLItem(llItemEntity);
            count++;
        }
    }
}
