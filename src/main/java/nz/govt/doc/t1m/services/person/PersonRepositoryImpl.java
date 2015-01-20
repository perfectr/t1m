package nz.govt.doc.t1m.services.person;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.person.PersonEntity;
import nz.govt.doc.t1m.domain.person.QPersonEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<PersonEntity> findByCriteria(PersonCriteria criteria) {

        String familyName = JPAUtils.appendWildcard(criteria.getFamilyName());
        String firstName = JPAUtils.appendWildcard(criteria.getFirstName());
        String nameCriteria = JPAUtils.appendWildcard(criteria.getNameCriteria());

        QPersonEntity personEntity = QPersonEntity.personEntity;
        JPAQuery query = new JPAQuery(em).from(personEntity);

        if(familyName != null) {
            query.where(personEntity.familyName.like(familyName));
        }

        if(firstName != null) {
            query.where(personEntity.firstName.like(firstName));
        }

        if(nameCriteria != null) {
            query.where(personEntity.familyName.like(nameCriteria).or(personEntity.firstName.like(nameCriteria)));
        }

        query.orderBy(personEntity.firstName.asc(), personEntity.familyName.asc());

        PagedResponse<PersonEntity> response = JPAUtils.listResults(query, criteria, personEntity);
        return response;
    }

}
