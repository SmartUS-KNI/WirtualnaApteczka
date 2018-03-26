package smartcity.kni.wirtualnaapteczka.enums;

/**
 * Created by Aleksander on 26.03.2018.
 */

public enum ERegularDoseType {

    HOURLY("Godzinowo"),
    DAILY("Codziennie"),
    WEEKLY("Tygodniowo"),
    MONTHLY("Miesięcznie");

    String name;


    private ERegularDoseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
