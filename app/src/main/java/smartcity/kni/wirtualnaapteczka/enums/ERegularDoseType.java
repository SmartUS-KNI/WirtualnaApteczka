package smartcity.kni.wirtualnaapteczka.enums;


import android.support.v4.app.FragmentManager;

import smartcity.kni.wirtualnaapteczka.dialogs.HourlyDoseAdjustmentDialog;
import smartcity.kni.wirtualnaapteczka.dialogs.MonthlyDoseAdjustmentDialog;
import smartcity.kni.wirtualnaapteczka.dialogs.WeeklyDoseAdjustmentDialog;
import smartcity.kni.wirtualnaapteczka.interfaces.AdjustmentProcedure;

/**
 * Created by Aleksander on 26.03.2018.
 */

public enum ERegularDoseType {

    HOURLY("Godzinowo", true, (FragmentManager supportFragmentManager) -> new HourlyDoseAdjustmentDialog().show(supportFragmentManager, "hourlyAdjustment")),
    DAILY("Codziennie", false),
    WEEKLY("Tygodniowo", true, (FragmentManager supportFragmentManager) -> new WeeklyDoseAdjustmentDialog().show(supportFragmentManager, "weeklyAdjustment")),
    MONTHLY("Miesięcznie", true, (FragmentManager supportFragmentManager) -> new MonthlyDoseAdjustmentDialog().show(supportFragmentManager, "monthlyAdjustment"));

    private static long generatedId = 1;

    private String name;
    private long id;
    private boolean adjustable;
    private AdjustmentProcedure function;

    ERegularDoseType(String name, boolean adjustable) {
        this.id = this.generateId();
        this.name = name;
        this.adjustable = adjustable;
    }

    ERegularDoseType(String name, boolean adjustable, AdjustmentProcedure function) {
        this.id = this.generateId();
        this.name = name;
        this.adjustable = adjustable;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    private long generateId() {
        return generatedId++;
    }

    public boolean isAdjustable() {
        return adjustable;
    }

    public static ERegularDoseType getRegularDoseTypeById(long id) {
        for (ERegularDoseType i : values()) {
            if (i.getId() == id)
                return i;
        }

        return null;
    }

    public void adjust(FragmentManager supportFragmentManager) {
        function.operation(supportFragmentManager);
    }
}
