package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.survey.SurveyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Component
public class DataParser {

    @Autowired
    protected BirdParser birdParser;
    @Autowired
    protected LitterParser litterParser;
    @Autowired
    protected BirdInstanceParser birdInstanceParser;
    @Autowired
    protected LitterInstanceParser litterInstanceParser;

    private DataForm dataForm;

    private String[] dataSheetType;
    private String[][] field;
    private String[][] data;

    public void initialize(DataForm dataForm) {
        this.dataForm = dataForm;
        this.dataSheetType = dataForm.getDst();
        this.field = dataForm.getFld();
        this.data = dataForm.getDat();
    }

    public DataForm getDataForm() { return dataForm; }

    /** pulls a surveyEntity from the dataForm to be saved to the DB */
    public SurveyEntity getSurvey() {
        if (dataForm.getSli() != null && dataForm.getObs() != null) {
            SurveyEntity surveyEntity = new SurveyEntity();
            surveyEntity.setObserver(dataForm.getObs());
            surveyEntity.setLocationId(dataForm.getSli());
            surveyEntity.setStartD(parseDate(dataForm.getSdt()));
            surveyEntity.setEndD(parseDate(dataForm.getEdt()));
            surveyEntity.setSurveyType(dataForm.getTyp());
            surveyEntity.setReceivedD();
            return surveyEntity;
        } else return null;
    }

    public void saveDataSheets(Integer surveyId) {
        if (dataForm.getTyp().contains("ird")) {
            System.out.println("New bird survey found");
            birdInstanceParser.initialize(dataForm.getInf(), dataForm.getInd());
            for (int i = 0 ; i < dataSheetType.length ; i++) {
                birdParser.initialize(field[i], data[i]);
                birdParser.saveEntity(dataSheetType[i], surveyId);
            }
        } else if (dataForm.getTyp().contains("itter")) {
            System.out.println("New litter survey found");
            litterInstanceParser.initialize(dataForm.getInf(), dataForm.getInd());
            for (int i = 0 ; i < dataSheetType.length ; i++) {
                litterParser.initialize(field[i], data[i]);
                litterParser.saveEntity(dataSheetType[i], surveyId);
            }
        } else System.out.println("Bad survey type received");
    }

    /** to parse formatted strings from JSONs into date objects */
    private Date parseDate(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
