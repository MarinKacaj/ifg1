package domainapp.dom.module;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

/**
 * Created by C.R.C on 12/30/2016.
 * Module repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Module.class)
public class ModuleRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Module> listAll() {
        return repositoryService.allInstances(Module.class);
    }

    public Module create(final String name, final Float coefficient) {
        final Module module = new Module();
        module.setName(name);
        module.setCoefficient(coefficient);
        serviceRegistry.injectServicesInto(module);
        repositoryService.persist(module);
        return module;
    }
}
