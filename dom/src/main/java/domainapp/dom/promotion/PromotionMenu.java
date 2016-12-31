package domainapp.dom.promotion;

import org.apache.isis.applib.annotation.*;

import java.util.Collection;

/**
 * Created by C.R.C on 12/24/2016.
 * Promotion menu
 */
@DomainService(
        nature = NatureOfService.VIEW_MENU_ONLY,
        repositoryFor = Promotion.class
)
@DomainServiceLayout(
        named = "Promotion year",
        menuOrder = "10"
)
public class PromotionMenu {

    @javax.inject.Inject
    PromotionRepository promotionRepository;

    @Action(semantics = SemanticsOf.SAFE)
    @MemberOrder(sequence = "1")
    public Collection<Promotion> listAll() {
        return promotionRepository.listAll();
    }

    @MemberOrder(sequence = "2")
    public Promotion create(
            @ParameterLayout(named = "Promotion year")
            final Integer year) {
        return promotionRepository.create(year);
    }
}
