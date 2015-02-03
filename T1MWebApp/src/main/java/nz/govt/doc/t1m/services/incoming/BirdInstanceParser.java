package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.instance.birdCount.BCBirdEntity;
import nz.govt.doc.t1m.domain.instance.birdDistance.BDBirdEntity;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdService;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by McCaulC on 2/02/2015.
 */
@Component
public class BirdInstanceParser {

    @Autowired
    protected BCBirdService bcBirdService;
    @Autowired
    protected BDBirdService bdBirdService;

    private String[][] instanceField;
    private String[][] instanceData;

    private int count;

    public void initialize(String[][] instanceField, String[][] instanceData) {
        this.instanceField = instanceField;
        this.instanceData = instanceData;
        this.count = 0;
    }

    public void saveBCBird(Integer dataSheetId, int numBCBirds){
        for (int i = 0 ; i < numBCBirds ; i++) {
            System.out.println("New BCBird instance found");
            BCBirdEntity bcBirdEntity = new BCBirdEntity();
            bcBirdEntity.setDataSheetId(dataSheetId);
            for (int j = 0 ; j < instanceField[count].length ; j++) {
                //System.out.println(instanceField[count][j] + ": " + instanceData[count][j]);
                try {
                    Class[] paramString = new Class[1];
                    paramString[0] = String.class;
                    Class classType = Class.forName("nz.govt.doc.t1m.domain.instance.birdCount.BCBirdEntity");
                    Method set = classType.getDeclaredMethod("set" + instanceField[count][j], paramString);
                    set.invoke(bcBirdEntity, instanceData[count][j]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bcBirdService.saveBCBird(bcBirdEntity);
            count++;
        }
    }

    public void saveBDBird(Integer dataSheetId, int numBDBirds) {
        for (int i = 0 ; i < numBDBirds ; i++) {
            System.out.println("New BDBird instance found");
            BDBirdEntity bdBirdEntity = new BDBirdEntity();
            bdBirdEntity.setDataSheetId(dataSheetId);
            for (int j = 0 ; j < instanceField[count].length ; j++) {
                //System.out.println(instanceField[count][j] + ": " + instanceData[count][j]);
                try {
                    Class[] paramString = new Class[1];
                    paramString[0] = String.class;
                    Class classType = Class.forName("nz.govt.doc.t1m.domain.instance.birdDistance.BDBirdEntity");
                    Method set = classType.getDeclaredMethod("set" + instanceField[count][j], paramString);
                    set.invoke(bdBirdEntity, instanceData[count][j]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bdBirdService.saveBDBird(bdBirdEntity);
            count++;
        }
    }
}
