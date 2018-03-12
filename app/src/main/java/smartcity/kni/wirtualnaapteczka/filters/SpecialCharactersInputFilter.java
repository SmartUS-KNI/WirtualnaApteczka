package smartcity.kni.wirtualnaapteczka.filters;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by K4masz on 2018-03-09.
 */

public class SpecialCharactersInputFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {

        StringBuilder builder = new StringBuilder(dest);

        builder.replace(dstart, dend, source.subSequence(start, end).toString());
        if (!builder.toString().matches(
                "^[0-9a-zA-ZżźćńłśąęóŻŹĆŃŁŚĄÓĘ\\.\\,\\s\\/]*$"
        )) {
            if (source.length() == 0)
                return dest.subSequence(dstart, dend);
            return "";
        }
        return null;
    }
}
