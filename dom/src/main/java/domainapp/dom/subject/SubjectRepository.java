package domainapp.dom.subject;

import domainapp.dom.module.Module;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;

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
    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public Collection<Subject> listAll() {
        return repositoryService.allInstances(Subject.class);
    }

    public Subject create(final String name, final Module module) {
        final Subject subject = new Subject();
        subject.setName(name);
        subject.setModule(module);
        serviceRegistry.injectServicesInto(subject);
        repositoryService.persist(subject);
        return subject;
    }

    public Collection<Subject> findByNameSequence(final String fullNameSequence) {
        return isisJdoSupport.executeQuery(Subject.class, QSubject.candidate().name.indexOf(fullNameSequence).gteq(0));
    }
}
