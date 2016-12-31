package domainapp.dom.initialformation;

import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 12/25/2016.
 * Initial formation menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = InitialFormation.class
)
@DomainServiceLayout(
        named = "Initial Formation",
        menuOrder = "10"
)
public class InitialFormationMenu {

    @javax.inject.Inject
    InitialFormationRepository initialFormationRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "1")
    public Collection<InitialFormation> listAll() {
        return initialFormationRepository.listAll();
    }

    public InitialFormation create(
            @ParameterLayout(named = "Profession")
            final String name) {
        return initialFormationRepository.create(name);
    }
}
