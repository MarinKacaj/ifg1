package domainapp.dom.letter;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

/**
 * Created by C.R.C on 12/29/2016.
 * Letter repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Letter.class)
public class LetterRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Letter> listAll() {
        return repositoryService.allInstances(Letter.class);
    }

    public Letter create(final String content) {
        final Letter letter = new Letter();
        letter.setContent(content);
        serviceRegistry.injectServicesInto(letter);
        repositoryService.persist(letter);
        return letter;
    }
}