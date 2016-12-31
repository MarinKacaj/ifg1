package domainapp.dom;

import com.google.common.base.Strings;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.repository.RepositoryService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by C.R.C on 12/31/2016.
 * Year padder util
 */
public class YearSequenceFilter {

    private static final int START_YEAR_MAX_NUM_DIGITS = 4;
    private static final char PAD_DIGIT = '0';

    private static Integer pad(String yearDigitSequence) {
        String paddedYear = Strings.padEnd(yearDigitSequence, START_YEAR_MAX_NUM_DIGITS, PAD_DIGIT);
        return Integer.parseInt(paddedYear);
    }

    public static <T> Collection<T> filterByYearDigitSequence(RepositoryService repositoryService,
                                                              String queryName, Class<T> domain,
                                                              String yearFieldName, String yearDigitSequence) {
        Integer paddedYear = YearSequenceFilter.pad(yearDigitSequence);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(yearFieldName, paddedYear);

        return repositoryService.allMatches(new QueryDefault<T>(domain, queryName, parameters));
    }
}
