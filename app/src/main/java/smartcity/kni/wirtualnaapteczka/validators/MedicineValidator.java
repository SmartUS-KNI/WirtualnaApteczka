package smartcity.kni.wirtualnaapteczka.validators;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import smartcity.kni.wirtualnaapteczka.R;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;

/**
 * Created by Aleksander on 12.06.2018.
 */

public class MedicineValidator implements FormValidator {

    private Context context;
    Map<String, Object> params;

    public MedicineValidator(Context context) {
        this.context = context;
        params = new HashMap<>();
    }

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public void getParamsFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();

        addParam("name", contentMap.get(R.id.name_Of_Medicine_From_New_Medicine_EditText));
        addParam("type", contentMap.get(R.id.medicine_type));
        addParam("typeUnit", contentMap.get(R.id.medicine_type_unit));
    }

    @Override
    public ValidationResponse isValid() {
        if(params.get("name").toString().replaceAll("\\s", "").isEmpty()) {
            return new ValidationResponse(false, context.getString(R.string.medicine_validation_empty_name));
        }

        if((((int)params.get("type")) > 0) && (((int)params.get("typeUnit")) == 0)) {
            return new ValidationResponse(false, context.getString(R.string.medicine_validation_empty_type_unit));
        }

        return new ValidationResponse(true, "");
    }
}
