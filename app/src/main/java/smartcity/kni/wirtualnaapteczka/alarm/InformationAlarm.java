package smartcity.kni.wirtualnaapteczka.alarm;

import android.app.PendingIntent;

public abstract class InformationAlarm {

    private final static String typeIdn = null;
    private String config;
    private String data;
    private Long informationId;
    private PendingIntent pendingIntent;

    public abstract void informationAlarm(information);

    protected static PendingIntent generatePendingIntent() {
        return null;
    }
}
