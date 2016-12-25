package domainapp.dom.initialformation;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

/**
 * Created by C.R.C on 12/25/2016.
 * Initial formation repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = InitialFormation.class
)
public class InitialFormationRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<InitialFormation> listAll() {
        return repositoryService.allInstances(InitialFormation.class);
    }

    public InitialFormation create(final String name) {
        final InitialFormation initialFormation = new InitialFormation();
        initialFormation.setName(name);
        serviceRegistry.injectServicesInto(initialFormation);
        repositoryService.persist(initialFormation);
        return initialFormation;
    }
}
