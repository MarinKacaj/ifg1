package domainapp.dom.viewmodel.studentfinalmark;

import domainapp.dom.NamedMenu;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 2/5/2017.
 * Student final mark menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = NamedMenu.EXAM_MENU_NAME,
        menuOrder = "10")
public class StudentFinalMarkMenu {

    @javax.inject.Inject
    StudentFinalMarkRepository studentFinalMarkRepository;

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public Collection<StudentFinalMark> findFinalMarksByStudent(@ParameterLayout(named = "Student") final Student student) {
        return studentFinalMarkRepository.getStudentFinalMarks(student);
    }
}
