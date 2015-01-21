package nz.govt.doc.t1m.services.person;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 */
public class PersonCriteria extends AbstractCriteria {

    private String familyName;
    private String firstName;

    private String nameCriteria;

    public PersonCriteria() {
        super();
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

    public String getNameCriteria() {
        return nameCriteria;
    }

    public void setNameCriteria(String nameCriteria) {
        this.nameCriteria = nameCriteria;
    }
}
