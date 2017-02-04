package domainapp.dom.student;

import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.initialformation.InitialFormation;
import domainapp.dom.mode.Mode;
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

    @Action(semantics = SemanticsOf.IDEMPOTENT)
    @MemberOrder(sequence = "3")
    public Collection<Student> findUnPromoted() {
        return studentrepository.findUnPromoted();
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT)
    @MemberOrder(sequence = "4")
    public Collection<Student> findUnEmployed() {
        return studentrepository.findUnEmployed();
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT)
    @MemberOrder(sequence = "5")
    public Collection<Student> findEmployedSortedByPromotionAscending() {
        return studentrepository.findEmployedSortedByPromotionAscending();
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "6")
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
            @ParameterLayout(named = Student.PROMOTION_LABEL) @Nullable final Promotion promotion,
            @ParameterLayout(named = Student.INITIAL_FORMATION_LABEL) final InitialFormation initialFormation,
            @ParameterLayout(named = Student.ACADEMIC_YEAR_LABEL) final AcademicYear year,
            @ParameterLayout(named = Student.MODE_LABEL) final Mode mode) {
        return studentrepository.create(fullName, gender, birthYear, address, city, country, tel, email,
                employer, employmentStatus, promotion, initialFormation, year, mode);
    }
}
