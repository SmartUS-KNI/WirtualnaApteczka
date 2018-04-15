package smartcity.kni.wirtualnaapteczka.enums;

/**
 * Created by Aleksander on 26.03.2018.
 */

public enum ERegularDoseType {

    HOURLY("Godzinowo"),
    DAILY("Codziennie"),
    WEEKLY("Tygodniowo"),
    MONTHLY("Miesięcznie");

    private String name;
    private int id;
    private static int generatedId = 1;

    private ERegularDoseType(String name) {
        this.id = this.generateId();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    private int generateId() {
        return generatedId++;
    }

}
