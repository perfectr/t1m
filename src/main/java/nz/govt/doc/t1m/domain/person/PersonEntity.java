package nz.govt.doc.t1m.domain.person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 */
@Entity
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;
    private String familyName;
    private String firstName;

    @NotNull
    @Column(unique = true)
    private String emailAddress;

    @Transient
    private String passwordEdit;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPasswordEdit() {
        return passwordEdit;
    }

    public void setPasswordEdit(String passwordEdit) {
        this.passwordEdit = passwordEdit;
    }
}
