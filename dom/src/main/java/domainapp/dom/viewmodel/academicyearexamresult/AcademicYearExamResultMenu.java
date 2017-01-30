package domainapp.dom.viewmodel.academicyearexamresult;

import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 1/30/2017.
 * Academic year exam result menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = "Academic Year Exam Result",
        menuOrder = "10")
public class AcademicYearExamResultMenu {

    @javax.inject.Inject
    AcademicYearExamResultRepository academicYearExamResultRepository;

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public Collection<AcademicYearExamResult> listExamResultsByAcademicYear(@ParameterLayout(named = "Start Year")
                                                                            final Integer academicYearStartYear) {
        return academicYearExamResultRepository.getExamResultsByAcademicYear(academicYearStartYear);
    }
}
