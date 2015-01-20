package nz.govt.doc.t1m.domain.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 */
@Entity
public class PersonCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personCredentialsId;

    private Integer personId;
    private String passwordHash;

    public Integer getPersonCredentialsId() {
        return personCredentialsId;
    }

    public void setPersonCredentialsId(Integer personCredentialsId) {
        this.personCredentialsId = personCredentialsId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
