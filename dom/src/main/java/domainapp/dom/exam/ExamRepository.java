package domainapp.dom.exam;

import domainapp.dom.academicyear.AcademicYear;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.List;

/**
 * Created by C.R.C on 12/18/2016.
 * Exam repo
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Exam.class)
public class ExamRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<Exam> listAll() {
        return repositoryService.allInstances(Exam.class);
    }

    public Exam create(final Integer mark, final AcademicYear academicYear) {
        final Exam exam = new Exam();
        exam.setMark(mark);
        exam.setAcademicYear(academicYear);
        serviceRegistry.injectServicesInto(exam);
        repositoryService.persist(exam);
        return exam;
    }
}