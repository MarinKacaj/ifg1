package domainapp.dom.letter;

import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;

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

    public Collection<Letter> listAll() {
        return repositoryService.allInstances(Letter.class);
    }

    public Letter create(final String content, final Student student) {
        final Letter letter = new Letter();
        letter.setContent(content);
        letter.setStudent(student);
        serviceRegistry.injectServicesInto(letter);
        repositoryService.persist(letter);
        return letter;
    }
}
