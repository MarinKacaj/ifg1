package domainapp.dom.letter;

import org.apache.isis.applib.annotation.*;

import java.util.List;

/**
 * Created by C.R.C on 12/29/2016.
 * Letter menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Letter.class)
@DomainServiceLayout(
        named = "Letter",
        menuOrder = "10")
public class LetterMenu {

    @javax.inject.Inject
    LetterRepository letterRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "1")
    public List<Letter> listAll() {
        return letterRepository.listAll();
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT)
    @MemberOrder(sequence = "2")
    public Letter create(@ParameterLayout(named = Letter.CONTENT_LABEL) final String content) {
        return letterRepository.create(content);
    }
}
