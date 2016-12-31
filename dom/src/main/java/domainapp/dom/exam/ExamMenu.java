package domainapp.dom.exam;

import domainapp.dom.academicyear.AcademicYear;
import domainapp.dom.simple.SimpleObjectMenu;
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
        named = "Exams",
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
            @ParameterLayout(named = "Mark")
            final Integer mark,
            @ParameterLayout(named = "Academic Year")
            final AcademicYear academicYear) {
        return examRepository.create(mark, academicYear);
    }

    public static class CreateDomainEvent extends ActionDomainEvent<SimpleObjectMenu> {
    }

}
