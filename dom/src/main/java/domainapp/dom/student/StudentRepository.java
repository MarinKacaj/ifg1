package domainapp.dom.student;

import domainapp.dom.initialformation.InitialFormation;
import domainapp.dom.promotion.Promotion;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public Collection<Student> listAll() {
        return repositoryService.allInstances(Student.class);
    }

    public Collection<Student> findByNameSequence(final String fullNameSequence) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Student.FULL_NAME, fullNameSequence);
        return repositoryService.allMatches(new QueryDefault<>(Student.class, Student.FIND_BY_FULL_NAME_QUERY, parameters));
    }

    public Student create(final String fullName, final Gender gender, final Integer birthYear, final String address,
                          final String city, final String country, final String tel, final String email,
                          final String employer, final EmploymentStatus employmentStatus, final Promotion promotion,
                          final InitialFormation initialFormation) {
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
        serviceRegistry.injectServicesInto(student);
        repositoryService.persist(student);
        return student;
    }
}
