package smartcity.kni.wirtualnaapteczka.utils;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by K4masz on 08.03.2018.
 * <p>
 * Input filter that limits the number of decimal digits that are allowed to be
 * entered.
 *
 * usage: .setFilters(new InputFilter[]{new DecimalDigitsInputFilter(maxBeforeDot, maxAfterDot)})
 */

public class DecimalDigitsInputFilter implements InputFilter {

    private int maxDigitsBeforeDecimalPoint = 4;
    private int maxDigitsAfterDecimalPoint = 2;

    public DecimalDigitsInputFilter(int maxDigitsBeforeDecimalPoint, int maxDigitsAfterDecimalPoint) {
        this.maxDigitsBeforeDecimalPoint = maxDigitsBeforeDecimalPoint;
        this.maxDigitsAfterDecimalPoint = maxDigitsAfterDecimalPoint;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        StringBuilder builder = new StringBuilder(dest);
        builder.replace(dstart, dend, source
                .subSequence(start, end).toString());
        if (!builder.toString().matches(
                "(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?"
        )) {
            if (source.length() == 0)
                return dest.subSequence(dstart, dend);
            return "";
        }
        return null;
    }
}