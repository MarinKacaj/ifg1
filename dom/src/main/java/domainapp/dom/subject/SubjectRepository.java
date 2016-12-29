package domainapp.dom.subject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

/**
 * Created by C.R.C on 12/29/2016.
 * Subject repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Subject.class)
public class SubjectRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Subject> listAll() {
        return repositoryService.allInstances(Subject.class);
    }

    public Subject create(final String name) {
        final Subject subject = new Subject();
        subject.setName(name);
        serviceRegistry.injectServicesInto(subject);
        repositoryService.persist(subject);
        return subject;
    }
}
