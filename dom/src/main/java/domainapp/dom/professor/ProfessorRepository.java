package domainapp.dom.professor;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by C.R.C on 12/29/2016.
 * Professor repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Professor.class)
public class ProfessorRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public Collection<Professor> listAll() {
        return repositoryService.allInstances(Professor.class);
    }

    public Professor create(final String fullName, final String affiliation, final String email, final String telNum,
                            final Integer teachingStartYear, final Integer teachingEndYear) {
        final Professor professor = new Professor();
        professor.setFullName(fullName);
        professor.setAffiliation(affiliation);
        professor.setEmail(email);
        professor.setTelNum(telNum);
        professor.setTeachingStartYear(teachingStartYear);
        professor.setTeachingEndYear(teachingEndYear);
        serviceRegistry.injectServicesInto(professor);
        repositoryService.persist(professor);
        return professor;
    }

    public Collection<Professor> findByNameSequence(final String fullNameSequence) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Professor.FULL_NAME, fullNameSequence);
        return repositoryService.allMatches(new QueryDefault<>(Professor.class, Professor.FIND_BY_FULL_NAME_QUERY, parameters));
    }
}
