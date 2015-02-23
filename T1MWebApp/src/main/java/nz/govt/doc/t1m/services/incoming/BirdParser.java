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
 * parses bird survey data sheets and calls instance parsers
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

    /**
     * used to initialise the parser for a new data sheet
     * @param field
     * @param data
     */
    public void initialize(String[] field, String[] data) {
        this.field = field;
        this.data = data;
    }

    /**
     * dictates data sheet type for processing
     * @param dataSheetName
     * @param surveyId
     * @return generic data sheet response
     */
    public DataSheetEntity saveEntity(String dataSheetName, Integer surveyId) {
        if (dataSheetName.equals("birdCount")) return birdCount(surveyId);
        else if (dataSheetName.equals("birdDistance")) return birdDistance(surveyId);
        else if (dataSheetName.equals("incidentalBird")) return incidentalBird(surveyId);
        else {
            System.out.println("Bad bird data sheet type(s) received");
            return null;
        }
    }

    /**
     * parses data into a bird count entity and calls instance parser
     * incomplete in prototype
     * @param surveyId
     * @return birdCount data sheet response
     */
    private BirdCountEntity birdCount(Integer surveyId) {
        System.out.println("New 5 minute bird count data sheet found");
        BirdCountEntity birdCountEntity = new BirdCountEntity();
        birdCountEntity.setSurveyId(surveyId);
        if (field.length == 0) return new BirdCountEntity();
        int numInstances = 0;
        for (int i = 0 ; i < field.length ; i++) {
            if (field[i].equals("Instances")) {
                numInstances = Integer.parseInt(data[i]);
//                System.out.println(numInstances + " Instance(s) found");
            } else {
//                System.out.println(field[i] + ": " + data[i]);
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

    /**
     * parses data into a bird distance entity and calls instance parser
     * incomplete in prototype
     * @param surveyId
     * @return birdDistance data sheet response
     */
    private BirdDistanceEntity birdDistance(Integer surveyId) {
        System.out.println("New 5 minute bird distance data sheet found");
        BirdDistanceEntity birdDistanceEntity = new BirdDistanceEntity();
        birdDistanceEntity.setSurveyId(surveyId);
        if (field.length == 0) return new BirdDistanceEntity();
        int numInstances = 0;
        for (int i = 0 ; i < field.length ; i++) {
            if (field[i].equals("Instances")) {
                numInstances = Integer.parseInt(data[i]);
//                System.out.println(numInstances + " Instance(s) found");
            } else {
//                System.out.println(field[i] + ": " + data[i]);
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

    /**
     * parses data into a incidental bird entity
     * incomplete in prototype
     * @param surveyId
     * @return incidentalBird data sheet response
     */
    private IncidentalBirdEntity incidentalBird(Integer surveyId) {
        System.out.println("New incidental bird sighting data sheet found");
        IncidentalBirdEntity incidentalBirdEntity = new IncidentalBirdEntity();
        incidentalBirdEntity.setSurveyId(surveyId);
        for (int i = 0 ; i < field.length ; i++) {
//            System.out.println(field[i] + ": " + data[i]);
            try {
                Class[] paramString = new Class[1];
                paramString[0] = String.class;
                Class bce = Class.forName("nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity");
                Method set = bce.getDeclaredMethod("set"+field[i],paramString);
                set.invoke(incidentalBirdEntity,data[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        incidentalBirdService.saveIncidentalBird(incidentalBirdEntity);
        return incidentalBirdEntity;
    }

}
