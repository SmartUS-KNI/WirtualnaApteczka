package smartcity.kni.wirtualnaapteczka.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksander on 26.08.2018.
 */

public enum EMedicineForm {
    PILL("Tabletka", true),
    CAPSULE("Kapsułka", true),
    BOLUS("Drażetka", true),
    LINGWETKA("Lingwetka", true),
    POWDER("Proszek", false),
    GRANULES("Granulat" ,false),
    SUPPOSITORY("Czopek", true),
    SOAP("Mydło", false),
    OINTMENT("Maść", false),
    CREAM("Krem", false),
    PASTE("Pasta", false),
    GEL("Żel", false),
    SOLUTION("Roztwór", false),
    SYRUP("Syrop", false),
    EMULSION("Emulsja", false),
    SUSPENSION("Zawiesina", false),
    LINIMENT("Mazidło", false);

    String name;
    boolean countable;

    private EMedicineForm(String name, boolean countable) {
        this.name = name;
        this.countable = countable;
    }

    public String getName() {
        return name;
    }

    public boolean isCountable() {
        return countable;
    }

    public static List<String> getNames() {
        List<String> list = new ArrayList<>();
        for(EMedicineForm form: EMedicineForm.values()) {
            list.add(form.getName());
        }

        return list;
    }
}
