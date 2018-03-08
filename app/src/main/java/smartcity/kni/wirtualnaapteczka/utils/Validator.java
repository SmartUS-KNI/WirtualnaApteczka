package smartcity.kni.wirtualnaapteczka.utils;

import android.view.View;

/**
 * Created by K4masz on 08.03.2018.
 */

public class Validator {

    private static boolean containsNonAlpahanumericCharacters(String input){
        if(input.matches("^[a-zA-Z0-9]*$"))
            return true;
        return false;
    }


}
