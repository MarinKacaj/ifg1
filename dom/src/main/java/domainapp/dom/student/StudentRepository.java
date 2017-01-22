package domainapp.dom.student;

import com.mysema.query.jdo.JDOQuery;
import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.initialformation.InitialFormation;
import domainapp.dom.mode.Mode;
import domainapp.dom.promotion.Promotion;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;

/**
 * Created by C.R.C on 12/25/2016.
 * Student repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Student.class)
public class StudentRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;
    @javax.inject.Inject
    IsisJdoSupport isisJdoSupport;

    public Collection<Student> listAll() {
        // com.mysema.query.jdo.JDOQuery creation with DataNucleus JDO
        // the var is not used, it's just for demo for the moment
        JDOQuery query = new JDOQuery(isisJdoSupport.getJdoPersistenceManager());
        return repositoryService.allInstances(Student.class);
    }

    public Collection<Student> findByNameSequence(final String fullNameSequence) {
        return isisJdoSupport.executeQuery(Student.class, QStudent.candidate().fullName.indexOf(fullNameSequence).gteq(0));
    }

    public Student create(final String fullName, final Gender gender, final Integer birthYear, final String address,
                          final String city, final String country, final String tel, final String email,
                          final String employer, final EmploymentStatus employmentStatus, final Promotion promotion,
                          final InitialFormation initialFormation, final AcademicYear year, final Mode mode) {
        final Student student = new Student();
        student.setFullName(fullName);
        student.setGender(gender);
        student.setBirthYear(birthYear);
        student.setAddress(address);
        student.setCity(city);
        student.setCountry(country);
        student.setTel(tel);
        student.setEmail(email);
        student.setEmployer(employer);
        student.setEmploymentStatus(employmentStatus);
        student.setPromotion(promotion);
        student.setInitialFormation(initialFormation);
        student.setYear(year);
        student.setMode(mode);
        serviceRegistry.injectServicesInto(student);
        repositoryService.persist(student);
        return student;
    }
}
