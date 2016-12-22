package domainapp.dom.academicyear;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<AcademicYear> findByStartYearDigitSequence(final String startYearDigitSequence) {
        StringBuilder startYearBuilder = new StringBuilder();
        startYearBuilder.append(startYearDigitSequence);
        startYearBuilder.append("0000");
        String paddedYear = startYearBuilder.substring(0, 4);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(AcademicYear.START_YEAR, Integer.parseInt(paddedYear));

        return repositoryService.allMatches(new QueryDefault<>(
                AcademicYear.class,
                AcademicYear.FIND_BY_START_YEAR_QUERY,
                parameters
        ));
    }
}
