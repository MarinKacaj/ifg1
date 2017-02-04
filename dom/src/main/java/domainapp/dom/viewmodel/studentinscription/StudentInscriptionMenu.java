package domainapp.dom.viewmodel.studentinscription;

import domainapp.dom.NamedMenu;
import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 2/4/2017.
 * Student inscription menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = NamedMenu.STUDENT_MENU_NAME,
        menuOrder = "10")
public class StudentInscriptionMenu {

    @javax.inject.Inject
    StudentInscriptionRepository studentInscriptionRepository;

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "7")
    public Collection<StudentInscription> listStudentInscriptions() {
        return studentInscriptionRepository.getStudentInscriptions();
    }
}
