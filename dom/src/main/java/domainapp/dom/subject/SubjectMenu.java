package domainapp.dom.subject;

import domainapp.dom.module.Module;
import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 12/29/2016.
 * Subject menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Subject.class)
@DomainServiceLayout(
        named = "Subject",
        menuOrder = "10")
public class SubjectMenu {

    @javax.inject.Inject
    SubjectRepository subjectRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "1")
    public Collection<Subject> listAll() {
        return subjectRepository.listAll();
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @MemberOrder(sequence = "2")
    public Subject create(@ParameterLayout(named = Subject.NAME_LABEL) final String name,
                          @ParameterLayout(named = Subject.MODULE_LABEL) final Module module) {
        return subjectRepository.create(name, module);
    }
}
