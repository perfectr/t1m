package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;
import nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity;
import nz.govt.doc.t1m.domain.dataSheet.birdCount.BirdCountEntity;
import nz.govt.doc.t1m.domain.dataSheet.birdDistance.BirdDistanceEntity;
import nz.govt.doc.t1m.services.dataSheet.birdCount.BirdCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Component
public class BirdParser {

    @Autowired
    protected BirdCountService birdCountService;

    private String[] field;
    private String[] data;

    public void initialize(String[] field, String[] data) {
        this.field = field;
        this.data = data;
    }

    public DataSheetEntity saveEntity(String dataSheetName, Integer surveyId) {
        if (dataSheetName.equals("birdCount")) return BirdCount(surveyId);
        else if (dataSheetName.equals("birdDistance")) return BirdDistance(surveyId);
        else if (dataSheetName.equals("incidentalBird")) return IncidentalBird(surveyId);
        else {
            System.out.println("Bad bird data sheet type(s) received");
            return null;
        }
    }

    private BirdCountEntity BirdCount(Integer surveyId) {
        System.out.println("New 5 minute bird count data sheet found");
        BirdCountEntity birdCountEntity = new BirdCountEntity(surveyId);
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
        }
        birdCountService.saveBirdCount(birdCountEntity);
        return birdCountEntity;
    }

    private BirdDistanceEntity BirdDistance(Integer surveyId) {
        System.out.println("New 5 minute bird distance data sheet found");
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
        }
        BirdDistanceEntity birdDistanceEntity = new BirdDistanceEntity(surveyId);
        return birdDistanceEntity;
    }

    private IncidentalBirdEntity IncidentalBird(Integer surveyId) {
        System.out.println("New incidental bird sighting data sheet found");
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
        }
        IncidentalBirdEntity incidentalBirdEntity = new IncidentalBirdEntity(surveyId);
        return incidentalBirdEntity;
    }

}
