package domainapp.dom.viewmodel.examresult;

import domainapp.dom.NamedMenu;
import domainapp.dom.student.EmploymentStatus;
import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 1/25/2017.
 * Exam result menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = NamedMenu.EXAM_MENU_NAME,
        menuOrder = "10")
public class ExamResultMenu {

    @javax.inject.Inject
    ExamResultRepository examResultRepository;

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public Collection<ExamResult> listExamResultsByAcademicYearAndEmploymentStatus(@ParameterLayout(named = "Start Year")
                                                                                   final Integer academicYearStartYear,
                                                                                   final EmploymentStatus employmentStatus) {
        return examResultRepository
                .getExamResultsByAcademicYearAndEmploymentStatus(academicYearStartYear, employmentStatus);
    }
}
