package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;
import nz.govt.doc.t1m.domain.dataSheet.litterBeach.LitterBeachEntity;
import nz.govt.doc.t1m.domain.dataSheet.litterLarge.LitterLargeEntity;
import nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.BeachCharacterizationEntity;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationService;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachService;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Component
public class LitterParser {

    @Autowired
    protected LitterBeachService litterBeachService;
    @Autowired
    protected LitterLargeService litterLargeService;
    @Autowired
    protected BeachCharacterizationService beachCharacterizationService;

    private String[] field;
    private String[] data;

    public void initialize(String[] field, String[] data) {
        this.field = field;
        this.data = data;
    }

    public DataSheetEntity saveEntity(String dataSheetName, Integer surveyId) {
        if (dataSheetName.equals("litterBeach")) return litterBeach(surveyId);
        else if (dataSheetName.equals("litterLarge")) return litterLarge(surveyId);
        else if (dataSheetName.equals("BeachCharacterization")) return beachCharacterization(surveyId);
        else {
            System.out.println("Bad litter data sheet type(s) received");
            return null;
        }
    }

    private LitterBeachEntity litterBeach(Integer surveyId) {
        System.out.println("New beach litter data sheet found");
        LitterBeachEntity litterBeachEntity = new LitterBeachEntity();
        litterBeachEntity.setSurveyId(surveyId);
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
            try {
                Class[] paramString = new Class[1];
                paramString[0] = String.class;
                Class bce = Class.forName("nz.govt.doc.t1m.domain.dataSheet.litterBeach.LitterBeachEntity");
                Method set = bce.getDeclaredMethod("set"+field[i],paramString);
                set.invoke(litterBeachEntity,data[i]);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        litterBeachService.saveLitterBeach(litterBeachEntity);
        return litterBeachEntity;
    }

    private LitterLargeEntity litterLarge(Integer surveyId) {
        System.out.println("New large litter data sheet found");
        LitterLargeEntity litterLargeEntity = new LitterLargeEntity();
        litterLargeEntity.setSurveyId(surveyId);
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
            try {
                Class[] paramString = new Class[1];
                paramString[0] = String.class;
                Class bce = Class.forName("nz.govt.doc.t1m.domain.dataSheet.litterLarge.LitterLargeEntity");
                Method set = bce.getDeclaredMethod("set"+field[i],paramString);
                set.invoke(litterLargeEntity,data[i]);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        litterLargeService.saveLitterLarge(litterLargeEntity);
        return litterLargeEntity;
    }

    private BeachCharacterizationEntity beachCharacterization(Integer surveyId) {
        System.out.println("New beach characterization data sheet found");
        BeachCharacterizationEntity beachCharacterizationEntity = new BeachCharacterizationEntity();
        beachCharacterizationEntity.setSurveyId(surveyId);
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
            try {
                Class[] paramString = new Class[1];
                paramString[0] = String.class;
                Class bce = Class.forName("nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.BeachCharacterizationEntity");
                Method set = bce.getDeclaredMethod("set"+field[i],paramString);
                set.invoke(beachCharacterizationEntity,data[i]);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        beachCharacterizationService.saveBeachCharacterization(beachCharacterizationEntity);
        return beachCharacterizationEntity;
    }

}
