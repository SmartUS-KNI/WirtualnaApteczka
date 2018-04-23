package smartcity.kni.wirtualnaapteczka.enums;

public enum EDayOfWeek {

    MONDAY("Poniedziałek", "pon"),
    TUESDAY("Wtorek", "wt"),
    WEDNESDAY("Środa", "śr"),
    THURSDAY("Czwartek", "czw"),
    FRIDAY("Piątek", "pt"),
    SATURDAY("Sobota", "sb"),
    SUNDAY("Niedziela", "nd");

    private String name;
    private String codeSign;

    EDayOfWeek(String name, String codeSign) {
        this.name = name;
        this.codeSign = codeSign;
    }

    public EDayOfWeek getDayOfWeekByCodeSign(String codeSign) {
        for (EDayOfWeek dayOfWeek : EDayOfWeek.values()) {
            if (dayOfWeek.getCodeSign().equals(codeSign))
                return dayOfWeek;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getCodeSign() {
        return codeSign;
    }
}
