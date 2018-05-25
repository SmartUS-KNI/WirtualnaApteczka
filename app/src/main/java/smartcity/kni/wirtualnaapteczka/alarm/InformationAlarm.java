package smartcity.kni.wirtualnaapteczka.alarm;

import android.app.PendingIntent;

import smartcity.kni.wirtualnaapteczka.Information;

public abstract class InformationAlarm {

    private final static String typeIdn = null;
    private String config;
    private String data;
    private Long informationId;
    private PendingIntent pendingIntent;

    public InformationAlarm(Information information) {
    }

    protected abstract PendingIntent generatePendingIntent();
}
