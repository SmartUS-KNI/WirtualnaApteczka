package smartcity.kni.wirtualnaapteczka.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aleksander on 18.02.2018.
 */

public enum EMedicineType {

    MEDICINE_TYPE_PILL("Tabletka", true, "szt."),
    MEDICINE_TYPE_CAPSULE("Kapsułka", true, "szt."),
    MEDICINE_TYPE_SUPPOSITORY("Czopek", true, "szt."),
    MEDICINE_TYPE_OINTMENT("Maść", false, "szt.", "ml."),
    MEDICINE_TYPE_SYRUP("Syrop", false, "g.", "ml.");

    private static long generatedId = 1;

    private long id;
    private String name;
    private boolean countable;
    private List<String> units;

    private EMedicineType(String name, boolean countable, String... units) {
        this.id = this.generateId();
        this.name = name;
        this.countable = countable;
        this.units = Arrays.asList(units);
    }

    private long generateId() {
        return generatedId++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCountable() {
        return countable;
    }

    public List<String> getUnits() {
        return units;
    }
}
