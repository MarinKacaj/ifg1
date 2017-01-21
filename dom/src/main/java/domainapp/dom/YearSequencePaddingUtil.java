package domainapp.dom;

import com.google.common.base.Strings;

/**
 * Created by C.R.C on 12/31/2016.
 * Year padder util
 */
public class YearSequencePaddingUtil {

    private static final int START_YEAR_MAX_NUM_DIGITS = 4;
    private static final char PAD_DIGIT = '0';

    public static Integer pad(String yearDigitSequence) {
        String paddedYear = Strings.padEnd(yearDigitSequence, START_YEAR_MAX_NUM_DIGITS, PAD_DIGIT);
        return Integer.parseInt(paddedYear);
    }
}
