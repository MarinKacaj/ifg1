package domainapp.dom.viewmodel.studentletter;

import domainapp.dom.NamedMenu;
import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 2/5/2017.
 * Student letter menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY
)
@DomainServiceLayout(
        named = NamedMenu.LETTER_MENU_NAME,
        menuOrder = "10")
public class StudentLetterMenu {

    @javax.inject.Inject
    StudentLetterRepository studentLetterRepository;

    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public Collection<StudentLetter> listLettersByStudents() {
        return studentLetterRepository.getStudentLetters();
    }
}
