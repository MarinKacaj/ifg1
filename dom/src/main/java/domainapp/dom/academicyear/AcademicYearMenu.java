package domainapp.dom.academicyear;

import domainapp.dom.simple.SimpleObjectMenu;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

import java.util.Collection;

/**
 * Created by C.R.C on 12/17/2016.
 * AcademicYear menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = AcademicYear.class
)
@DomainServiceLayout(
        named = "Academic Years",
        menuOrder = "10"
)
public class AcademicYearMenu {

    @javax.inject.Inject
    AcademicYearRepository academicYearRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    @MemberOrder(sequence = "1")
    public Collection<AcademicYear> listAll() {
        return academicYearRepository.listAll();
    }

    @Action(domainEvent = AcademicYearMenu.CreateDomainEvent.class)
    @MemberOrder(sequence = "2")
    public AcademicYear create(
            @ParameterLayout(named = "Start Year")
            final Integer startYear) {
        return academicYearRepository.create(startYear);
    }

    public static class CreateDomainEvent extends ActionDomainEvent<SimpleObjectMenu> {
    }
}
