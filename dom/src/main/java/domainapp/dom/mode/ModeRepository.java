package domainapp.dom.mode;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by C.R.C on 12/30/2016.
 * Mode repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Mode.class)
public class ModeRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public Collection<Mode> listAll() {
        return repositoryService.allInstances(Mode.class);
    }

    public Mode create(final String name) {
        final Mode mode = new Mode();
        mode.setName(name);
        serviceRegistry.injectServicesInto(mode);
        repositoryService.persist(mode);
        return mode;
    }

    public Collection<Mode> findByName(final String nameSequence) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Mode.NAME, nameSequence);

        return repositoryService.allMatches(
                new QueryDefault<>(Mode.class, Mode.FIND_BY_NAME, parameters)
        );
    }
}
