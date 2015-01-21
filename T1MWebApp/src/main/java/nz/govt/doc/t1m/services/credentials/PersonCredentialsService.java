package nz.govt.doc.t1m.services.credentials;

import nz.govt.doc.t1m.domain.person.PersonCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 */
@Component
public class PersonCredentialsService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected PersonCredentialsRepository personCredentialsRepository;

    public PersonCredentials findByPersonId(Integer personId) {
        return personCredentialsRepository.findOneByPersonId(personId);
    }

    /**
     * As part of saving a person this method will be called to save the password. If the password is specified then
     * it is converted into a hash value and saved in a separate PersonCredentials object for security reasons so there
     * is less chance that database password hash values can be exposed to the web tier.
     *
     */
    @Transactional
    public void saveCredentials(@NotNull Integer personId, String passwordEdit) {

        if (personId == null) {
            throw new NullPointerException("personId must not be null");
        }

        if(passwordEdit != null) {

            // TODO: validate the clear text password for length/complexity rules here...

            String passwordHash = passwordEncoder.encode(passwordEdit);

            PersonCredentials personCredentials = personCredentialsRepository.findOneByPersonId(personId);
            if (personCredentials == null) {
                personCredentials = new PersonCredentials();
                personCredentials.setPersonId(personId);
                personCredentials.setPasswordHash(passwordHash);
                personCredentialsRepository.save(personCredentials);
            }
            else {
                // Credentials object already saved in database, so update the property. With Hibernate in this case
                // since the object was read within a transaction we don't actually need to call save() on the object
                // since it is already attached to the transaction when it was read.
                personCredentials.setPasswordHash(passwordHash);
            }
        }
    }
}
