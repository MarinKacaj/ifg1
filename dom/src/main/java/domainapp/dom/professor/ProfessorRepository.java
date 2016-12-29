package domainapp.dom.professor;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

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

    public List<Professor> listAll() {
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
}
