package domainapp.dom.viewmodel.studentinscription;

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
        named = "Exam Result",
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
    @MemberOrder(sequence = "1")
    public Collection<StudentInscription> listStudentInscriptions() {
        return studentInscriptionRepository.getStudentInscriptions();
    }
}
