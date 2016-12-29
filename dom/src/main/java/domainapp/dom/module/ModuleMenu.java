package domainapp.dom.module;

import org.apache.isis.applib.annotation.*;

import java.util.List;

/**
 * Created by C.R.C on 12/30/2016.
 * Module menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Module.class)
@DomainServiceLayout(
        named = "Module",
        menuOrder = "10")
public class ModuleMenu {

    @javax.inject.Inject
    ModuleRepository moduleRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "1")
    public List<Module> listAll() {
        return moduleRepository.listAll();
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "2")
    public Module create(@ParameterLayout(named = Module.NAME_LABEL) final String name,
                         @ParameterLayout(named = Module.COEFFICIENT_LABEL) final Float coefficient) {
        return moduleRepository.create(name, coefficient);
    }
}
