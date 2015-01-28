package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;
import nz.govt.doc.t1m.domain.dataSheet.litterBeach.LitterBeachEntity;
import nz.govt.doc.t1m.domain.dataSheet.litterLarge.LitterLargeEntity;
import nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.BeachCharacterizationEntity;

/**
 * Created by McCaulC on 27/01/2015.
 */
public class LitterParser {

    private String[] field;
    private String[] data;

    public LitterParser(String[] field, String[] data) {
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
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
        }
        LitterBeachEntity litterBeachEntity = new LitterBeachEntity(surveyId);
        return litterBeachEntity;
    }

    private LitterLargeEntity litterLarge(Integer surveyId) {
        System.out.println("New large litter data sheet found");
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
        }
        LitterLargeEntity litterLargeEntity = new LitterLargeEntity(surveyId);
        return litterLargeEntity;
    }

    private BeachCharacterizationEntity beachCharacterization(Integer surveyId) {
        System.out.println("New beach characterization data sheet found");
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
        }
        BeachCharacterizationEntity beachCharacterizationEntity = new BeachCharacterizationEntity(surveyId);
        return beachCharacterizationEntity;
    }

}
