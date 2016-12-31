package domainapp.dom.student;

import domainapp.dom.promotion.Promotion;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nullable;
import java.util.Collection;


@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = "Student",
        menuOrder = "10"
)
public class StudentMenu {

    @javax.inject.Inject
    StudentRepository studentrepository;

    @Action(
            semantics = SemanticsOf.SAFE,
            restrictTo = RestrictTo.PROTOTYPING
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public Collection<Student> listAll() {
        return studentrepository.listAll();
    }

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public Collection<Student> findByFullName(final String fullName) {
        return studentrepository.findByNameSequence(fullName);
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "3")
    public Student create(
            @ParameterLayout(named = Student.FULL_NAME_LABEL) final String fullName,
            @ParameterLayout(named = Student.GENDER_LABEL) final Gender gender,
            @ParameterLayout(named = Student.BIRTH_YEAR_LABEL) final Integer birthYear,
            @ParameterLayout(named = Student.ADDRESS_LABEL) final String address,
            @ParameterLayout(named = Student.CITY_LABEL) final String city,
            @ParameterLayout(named = Student.COUNTRY_LABEL) final String country,
            @ParameterLayout(named = Student.TEL_LABEL) final String tel,
            @ParameterLayout(named = Student.EMAIL_LABEL) final String email,
            @ParameterLayout(named = Student.EMPLOYER_LABEL) final String employer,
            @ParameterLayout(named = Student.EMPLOYMENT_STATUS_LABEL) final EmploymentStatus employmentStatus,
            @ParameterLayout(named = Student.PROMOTION_LABEL) @Nullable final Promotion promotion) {
        return studentrepository.create(fullName, gender, birthYear, address, city, country, tel, email,
                employer, employmentStatus, promotion);
    }
}
