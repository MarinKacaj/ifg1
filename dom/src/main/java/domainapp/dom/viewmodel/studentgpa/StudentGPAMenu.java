package domainapp.dom.viewmodel.studentgpa;

import domainapp.dom.NamedMenu;
import domainapp.dom.student.Student;
import org.apache.isis.applib.annotation.*;

/**
 * Created by C.R.C on 2/5/2017.
 * Student GPA menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = NamedMenu.STUDENT_MENU_NAME,
        menuOrder = "10")
public class StudentGPAMenu {

    @javax.inject.Inject
    StudentGPARepository studentGPARepository;

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public StudentGPA findStudentGPAByStudent(@ParameterLayout(named = "Student")
                                              final Student student) {
        return studentGPARepository.getStudentGPA(student);
    }
}
