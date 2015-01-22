package nz.govt.doc.t1m.services.person;

import nz.govt.doc.t1m.domain.person.PersonEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.credentials.PersonCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class PersonService {

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected PersonCredentialsService personCredentialsService;

    public PagedResponse<PersonEntity> findByCriteria(PersonCriteria criteria) {
        return personRepository.findByCriteria(criteria);
    }

    public Response<PersonEntity> findPersonById(Integer personId) {
        return new Response<>(personRepository.findOne(personId));
    }

    public Response<PersonEntity> findPersonByEmail(String emailAddress) {
        return new Response<>(personRepository.findOneByEmailAddress(emailAddress));
    }

    @Transactional
    public Response<PersonEntity> savePerson(PersonEntity person) {
        // validate the personEntity - is there already a person with this email address (with a different personId)?

        PersonEntity personResponse = personRepository.save(person);
        System.out.println(person.getFirstName());

        // This is a bit ugly but we need personId from after the save because the id is assigned by the database
        // and would be null for new persons otherwise. But we also need passwordEdit from before the save because
        // passwordEdit is a transient field and gets blanked out by the save. C'est la vie.
        personCredentialsService.saveCredentials(personResponse.getPersonId(), person.getPasswordEdit());

        return new Response<>(personResponse);
    }

    public void removePerson(Integer personId) {
        personRepository.delete(personId);
    }
}
