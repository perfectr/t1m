package nz.govt.doc.t1m.services.credentials;

import nz.govt.doc.t1m.domain.person.PersonCredentials;
import org.springframework.data.repository.CrudRepository;

/**
 */
public interface PersonCredentialsRepository extends CrudRepository<PersonCredentials, Integer> {

    PersonCredentials findOneByPersonId(Integer personId);
}
