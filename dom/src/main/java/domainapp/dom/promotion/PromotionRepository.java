package domainapp.dom.promotion;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;

/**
 * Created by C.R.C on 12/25/2016.
 * Promotion repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Promotion.class)
public class PromotionRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public Collection<Promotion> listAll() {
        return repositoryService.allInstances(Promotion.class);
    }

    public Promotion create(final Integer year) {
        final Promotion promotion = new Promotion();
        promotion.setYear(year);
        serviceRegistry.injectServicesInto(promotion);
        repositoryService.persist(promotion);
        return promotion;
    }
}
