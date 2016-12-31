package domainapp.dom.academicyear;

import domainapp.dom.YearSequenceFilter;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;
import java.util.List;

/**
 * Created by C.R.C on 12/17/2016.
 * AcademicYear repository
 */
@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = AcademicYear.class)
public class AcademicYearRepository {

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

    public List<AcademicYear> listAll() {
        return repositoryService.allInstances(AcademicYear.class);
    }

    public AcademicYear create(final Integer startYear) {
        final AcademicYear object = new AcademicYear();
        object.setStartYear(startYear);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
    }

    public Collection<AcademicYear> findByStartYearDigitSequence(final String startYearDigitSequence) {
        return YearSequenceFilter.filterByYearDigitSequence(
                repositoryService, AcademicYear.FIND_BY_START_YEAR_QUERY,
                AcademicYear.class, AcademicYear.START_YEAR, startYearDigitSequence);
    }
}
