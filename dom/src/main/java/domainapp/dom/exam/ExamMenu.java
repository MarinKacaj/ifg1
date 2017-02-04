package domainapp.dom.exam;

import domainapp.dom.NamedMenu;
import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.professor.Professor;
import domainapp.dom.simple.SimpleObjectMenu;
import domainapp.dom.student.Student;
import domainapp.dom.subject.Subject;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

import java.util.Collection;

/**
 * Created by C.R.C on 12/18/2016.
 * Exam menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Exam.class
)
@DomainServiceLayout(
        named = NamedMenu.EXAM_MENU_NAME,
        menuOrder = "10"
)
public class ExamMenu {

    @javax.inject.Inject
    ExamRepository examRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public Collection<Exam> listAll() {
        return examRepository.listAll();
    }

    @Action(domainEvent = SimpleObjectMenu.CreateDomainEvent.class)
    @MemberOrder(sequence = "3")
    public Exam create(
            @ParameterLayout(named = Exam.MARK_LABEL) final Integer mark,
            @ParameterLayout(named = Exam.ACADEMIC_YEAR_LABEL) final AcademicYear academicYear,
            @ParameterLayout(named = Exam.STUDENT_LABEL) final Student student,
            @ParameterLayout(named = Exam.PROFESSOR_LABEL) final Professor professor,
            @ParameterLayout(named = Exam.SUBJECT_LABEL) final Subject subject) {
        return examRepository.create(mark, academicYear, student, professor, subject);
    }

    public static class CreateDomainEvent extends ActionDomainEvent<SimpleObjectMenu> {
    }

}
