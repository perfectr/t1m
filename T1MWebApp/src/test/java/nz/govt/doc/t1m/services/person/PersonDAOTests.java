package nz.govt.doc.t1m.services.person;

import nz.govt.doc.t1m.Application;
import nz.govt.doc.t1m.domain.person.PersonEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
public class PersonDAOTests {

    //@Autowired
    protected PersonRepository personRepository;

    //@Test
    public void whenSimpleCRUD() {
        PersonEntity p1 = new PersonEntity();
        p1.setFirstName("Mary");
        p1.setFamilyName("Smith" + System.currentTimeMillis());
        p1.setEmailAddress(p1.getFirstName() + "." + p1.getFamilyName() + "@fronde.com");

        //personDAO.save(p);
        PersonEntity p2 = personRepository.save(p1);
        PersonEntity p3 = personRepository.findOne(p2.getPersonId());

        assertThat(p1.getEmailAddress(), equalTo(p3.getEmailAddress()));

        PersonEntity p4 = personRepository.findOneByEmailAddress(p1.getEmailAddress());
        assertThat(p1.getEmailAddress(), equalTo(p4.getEmailAddress()));

        // One example of how to test that an exception is thrown, can also use the "expected" attribute of the @Test annotation
        // but wanted to do a logical delete in this same method.
        DataAccessException expectedException = null;
        try {
            PersonEntity p5 = new PersonEntity();
            p5.setEmailAddress(p1.getEmailAddress());
            personRepository.save(p5);
        }
        catch (DataAccessException ex) {
            expectedException = ex;
        }
        assertThat(expectedException, notNullValue());

        PersonCriteria personCriteria = new PersonCriteria();
        personCriteria.setFamilyName(p1.getFamilyName());

        PagedResponse<PersonEntity> p6List = personRepository.findByCriteria(personCriteria);
        assertThat(p6List.getResults().size(), equalTo(1));
        assertThat(p6List.getResults().get(0).getPersonId(), equalTo(p1.getPersonId()));


        personRepository.delete(p1.getPersonId());
        PersonEntity p7 = personRepository.findOne(p1.getPersonId());
        assertThat(p7, nullValue());
    }
}
