package smartcity.kni.wirtualnaapteczka.regularConfigurator;

import android.widget.CheckBox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smartcity.kni.wirtualnaapteczka.enums.EDayOfWeek;
import smartcity.kni.wirtualnaapteczka.enums.ERegularDoseType;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;

public class RegularConfigurator {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String generateRegularConfig(ERegularDoseType type, LayoutContent contentOfDialogDoseConfiguration) {
        try {
            switch (type) {
                case MONTHLY:
                    return codeRegularConfigForMonthlyDose(contentOfDialogDoseConfiguration);
                case HOURLY:
                    return codeRegularConfigForHourlyDose(contentOfDialogDoseConfiguration);
                case WEEKLY:
                    return codeRegularConfigForWeeklyDose(contentOfDialogDoseConfiguration);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String codeRegularConfigForHourlyDose(LayoutContent contentOfDialogDoseConfiguration) throws JsonProcessingException {
        RegularConfigJSON hourlyDoseRegular = new RegularConfigJSON();


        return mapper.writeValueAsString(hourlyDoseRegular);
    }

    private static String codeRegularConfigForWeeklyDose(LayoutContent contentOfDialogDoseConfiguration) throws JsonProcessingException {
        RegularConfigJSON weeklyDoseRegular = new RegularConfigJSON();
        for (Object o : retriveValuesFromContent(contentOfDialogDoseConfiguration))
            weeklyDoseRegular.getRegularConfig().add(EDayOfWeek.getCodeSignByDayOfWeek(((CheckBox) o).getText().toString()));
        return mapper.writeValueAsString(weeklyDoseRegular);
    }

    private static String codeRegularConfigForMonthlyDose(LayoutContent contentOfDialogDoseConfiguration) throws JsonProcessingException {
        RegularConfigJSON monthlyDoseRegular = new RegularConfigJSON();
        for (Object o : retriveValuesFromContent(contentOfDialogDoseConfiguration))
            monthlyDoseRegular.getRegularConfig().add(((CheckBox) o).getText().toString());
        return mapper.writeValueAsString(monthlyDoseRegular);
    }

    private static List<CheckBox> retriveValuesFromContent(LayoutContent contentOfDialogDoseConfiguration) {
        List<CheckBox> values = new ArrayList<>();
        for (Map.Entry<Integer, Object> entry : contentOfDialogDoseConfiguration.getContentMap().entrySet()) {
            CheckBox checkbox = (CheckBox) entry.getValue();
            if (checkbox.isChecked())
                values.add(checkbox);
        }
        return values;
    }
}
