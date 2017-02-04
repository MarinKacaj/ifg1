package domainapp.dom.viewmodel.yearlystudentinscription;

import domainapp.dom.NamedMenu;
import domainapp.dom.academicyear.AcademicYear;
import org.apache.isis.applib.annotation.*;

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
    public Collection<YearlyStudentInscription> listYearlyStudentInscriptions(@ParameterLayout(named = "Academic Year")
                                                                              final AcademicYear academicYear) {
        return yearlyStudentInscriptionRepository.getYearlyStudentInscriptions(academicYear);
    }
}
