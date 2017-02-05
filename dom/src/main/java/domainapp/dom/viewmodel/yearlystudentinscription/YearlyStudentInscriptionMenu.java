package domainapp.dom.viewmodel.yearlystudentinscription;

import domainapp.dom.NamedMenu;
import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.initialformation.InitialFormation;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Created by C.R.C on 2/4/2017.
 * Yearly student inscription menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = NamedMenu.STUDENT_MENU_NAME,
        menuOrder = "10")
public class YearlyStudentInscriptionMenu {

    @javax.inject.Inject
    YearlyStudentInscriptionRepository yearlyStudentInscriptionRepository;

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "8")
    public Collection<YearlyStudentInscription> listYearlyStudentInscriptions(@ParameterLayout(named = Student.ACADEMIC_YEAR_LABEL)
                                                                              final AcademicYear academicYear,
                                                                              @ParameterLayout(named = "Ignore Employment Status")
                                                                              final Boolean ignoreEmploymentStatus,
                                                                              @ParameterLayout(named = Student.INITIAL_FORMATION_LABEL)
                                                                              @Nullable
                                                                              final InitialFormation initialFormation) {
        return yearlyStudentInscriptionRepository.getYearlyStudentInscriptions(academicYear, ignoreEmploymentStatus, initialFormation);
    }
}
