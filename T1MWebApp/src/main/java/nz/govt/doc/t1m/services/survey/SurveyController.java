package nz.govt.doc.t1m.services.survey;

import nz.govt.doc.t1m.domain.survey.SurveyEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.incoming.DataForm;
import nz.govt.doc.t1m.services.incoming.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/survey")
public class SurveyController {

    @Autowired
    protected SurveyService surveyService;

    @Autowired
    protected DataParser dataParser;

    @RequestMapping(value = "/{surveyId}", method = RequestMethod.GET)
    public Response<SurveyEntity> getSurvey(@PathVariable(value = "surveyId") Integer surveyId) {
        return surveyService.findSurveyById(surveyId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<SurveyEntity> search(@RequestBody SurveyCriteria criteria) {
        return surveyService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<SurveyEntity> saveSurvey(@RequestBody DataForm dataForm) {
        return surveyService.saveSurvey(dataForm);
    }

//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
//    public Response<SurveyEntity> saveSurvey(@RequestBody Response<SurveyEntity> request) {
//        return surveyService.saveSurvey(request.getModel());
//    }

    @RequestMapping(value = "/{surveyId}", method = RequestMethod.DELETE)
    public void removeSurvey(@PathVariable(value = "surveyId") Integer surveyId) {
        surveyService.removeSurvey(surveyId);
    }

}
