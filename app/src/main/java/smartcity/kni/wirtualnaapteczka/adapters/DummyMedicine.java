package smartcity.kni.wirtualnaapteczka.adapters;

/**
 * Created by Radek on 07-Feb-18.
 */

public class DummyMedicine {

    private String mMedicineName;
    private String mMedicineTag;


    public DummyMedicine(String mMedicineName, String mMedicineTag) {
        this.mMedicineName = mMedicineName;
        this.mMedicineTag = mMedicineTag;
    }

    public String getmMedicineName() {
        return mMedicineName;
    }

    public void setmMedicineName(String mMedicineName) {
        this.mMedicineName = mMedicineName;
    }

    public String getmMedicineTag() {
        return mMedicineTag;
    }

    public void setmMedicineTag(String mMedicineTag) {
        this.mMedicineTag = mMedicineTag;
    }
}
