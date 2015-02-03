package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;
import nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity;
import nz.govt.doc.t1m.domain.dataSheet.birdCount.BirdCountEntity;
import nz.govt.doc.t1m.domain.dataSheet.birdDistance.BirdDistanceEntity;
import nz.govt.doc.t1m.services.dataSheet.birdCount.BirdCountService;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceService;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Component
public class BirdParser {

    @Autowired
    protected BirdCountService birdCountService;
    @Autowired
    protected BirdDistanceService birdDistanceService;
    @Autowired
    protected IncidentalBirdService incidentalBirdService;
    @Autowired
    protected BirdInstanceParser birdInstanceParser;

    private String[] field;
    private String[] data;

    public void initialize(String[] field, String[] data) {
        this.field = field;
        this.data = data;
    }

    public DataSheetEntity saveEntity(String dataSheetName, Integer surveyId) {
        if (dataSheetName.equals("birdCount")) return birdCount(surveyId);
        else if (dataSheetName.equals("birdDistance")) return birdDistance(surveyId);
        else if (dataSheetName.equals("incidentalBird")) return incidentalBird(surveyId);
        else {
            System.out.println("Bad bird data sheet type(s) received");
            return null;
        }
    }

    private BirdCountEntity birdCount(Integer surveyId) {
        System.out.println("New 5 minute bird count data sheet found");
        BirdCountEntity birdCountEntity = new BirdCountEntity();
        birdCountEntity.setSurveyId(surveyId);
        if (field.length == 0) return new BirdCountEntity();
        int numInstances = 0;
        for (int i = 0 ; i < field.length ; i++) {
            if (field[i].equals("Instances")) {
                numInstances = Integer.parseInt(data[i]);
                //System.out.println(numInstances + " Instance(s) found");
            } else {
                //System.out.println(field[i] + ": " + data[i]);
                try {
                    Class[] paramString = new Class[1];
                    paramString[0] = String.class;
                    Class bce = Class.forName("nz.govt.doc.t1m.domain.dataSheet.birdCount.BirdCountEntity");
                    Method set = bce.getDeclaredMethod("set" + field[i], paramString);
                    set.invoke(birdCountEntity, data[i]);
                } catch (Exception e) {
                       e.printStackTrace();
                }
            }
        }
        BirdCountEntity response = birdCountService.saveBirdCount(birdCountEntity);
        birdInstanceParser.saveBCBird(response.getDataSheetId(), numInstances);
        return birdCountEntity;
    }

    private BirdDistanceEntity birdDistance(Integer surveyId) {
        System.out.println("New 5 minute bird distance data sheet found");
        BirdDistanceEntity birdDistanceEntity = new BirdDistanceEntity();
        birdDistanceEntity.setSurveyId(surveyId);
        if (field.length == 0) return new BirdDistanceEntity();
        int numInstances = 0;
        for (int i = 0 ; i < field.length ; i++) {
            if (field[i].equals("Instances")) {
                numInstances = Integer.parseInt(data[i]);
                //System.out.println(numInstances + " Instance(s) found");
            } else {
                //System.out.println(field[i] + ": " + data[i]);
                try {
                    Class[] paramString = new Class[1];
                    paramString[0] = String.class;
                    Class classType = Class.forName("nz.govt.doc.t1m.domain.dataSheet.birdDistance.BirdDistanceEntity");
                    Method set = classType.getDeclaredMethod("set" + field[i], paramString);
                    set.invoke(birdDistanceEntity, data[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        BirdDistanceEntity response = birdDistanceService.saveBirdDistance(birdDistanceEntity);
        birdInstanceParser.saveBDBird(response.getDataSheetId(), numInstances);
        return birdDistanceEntity;
    }

    private IncidentalBirdEntity incidentalBird(Integer surveyId) {
        System.out.println("New incidental bird sighting data sheet found");
        IncidentalBirdEntity incidentalBirdEntity = new IncidentalBirdEntity();
        incidentalBirdEntity.setSurveyId(surveyId);
        for (int i = 0 ; i < field.length ; i++) {
            System.out.println(field[i] + ": " + data[i]);
            try {
                Class[] paramString = new Class[1];
                paramString[0] = String.class;
                Class bce = Class.forName("nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity");
                Method set = bce.getDeclaredMethod("set"+field[i],paramString);
                set.invoke(incidentalBirdEntity,data[i]);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        incidentalBirdService.saveIncidentalBird(incidentalBirdEntity);
        return incidentalBirdEntity;
    }

}
