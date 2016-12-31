package domainapp.dom.mode;

import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 12/30/2016.
 * Mode menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Mode.class)
@DomainServiceLayout(
        named = "Mode",
        menuOrder = "10")
public class ModeMenu {

    @javax.inject.Inject
    ModeRepository modeRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "1")
    public Collection<Mode> listAll() {
        return modeRepository.listAll();
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "2")
    public Mode create(@ParameterLayout(named = Mode.NAME_LABEL) final String name) {
        return modeRepository.create(name);
    }
}
