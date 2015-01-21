package nz.govt.doc.t1m.services.person;

import nz.govt.doc.t1m.domain.person.PersonEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/person")
public class PersonController {

    @Autowired
    protected PersonService personService;

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public Response<PersonEntity> getPerson(@PathVariable(value = "personId") Integer personId) {
        return personService.findPersonById(personId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<PersonEntity> search(@RequestBody PersonCriteria criteria) {
        return personService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<PersonEntity> savePerson(@RequestBody Response<PersonEntity> request) {
        return personService.savePerson(request.getModel());
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable(value = "personId") Integer personId) {
        personService.removePerson(personId);
    }

}
