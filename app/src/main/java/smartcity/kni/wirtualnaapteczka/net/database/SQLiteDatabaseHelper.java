package smartcity.kni.wirtualnaapteczka.net.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.List;

import smartcity.kni.wirtualnaapteczka.Alergens_List;
import smartcity.kni.wirtualnaapteczka.DaoMaster;
import smartcity.kni.wirtualnaapteczka.DaoSession;
import smartcity.kni.wirtualnaapteczka.Dose;
import smartcity.kni.wirtualnaapteczka.DoseDao;
import smartcity.kni.wirtualnaapteczka.Information;
import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.MedicineDao;
import smartcity.kni.wirtualnaapteczka.Medicine_Count;
import smartcity.kni.wirtualnaapteczka.Medicine_CountDao;
import smartcity.kni.wirtualnaapteczka.enums.EMedicineType;

/**
 * Created by Aleksander on 04.02.2018.
 */

public class SQLiteDatabaseHelper {

    private static final SQLiteDatabaseHelper ourInstance = new SQLiteDatabaseHelper();

    private SQLiteDatabase database;
    private String databaseName;
    private Context context;

    private DaoMaster.DevOpenHelper helper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private QueryHelper queryHelper;

    public static SQLiteDatabaseHelper getInstance() {
        return ourInstance;
    }

    private SQLiteDatabaseHelper() {

    }

    public void openConnection(Context context, String databaseName) {
        this.setConfiguration(context, databaseName);

        helper = new DaoMaster.DevOpenHelper(context,databaseName);
        database = helper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();

        this.prepareQueryConfig();
    }

    private void setConfiguration(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
    }

    private void prepareQueryConfig() {
        queryHelper = new QueryHelper(daoSession);
    }

    public Medicine getMedicineById(long idMedicine) {
        Query<Medicine> query = queryHelper.getMedicineQueryBuilder()
                .where(MedicineDao.Properties.Id.eq(idMedicine))
                .limit(1)
                .build();

        return query.unique();
    }

    public List<Medicine> getMedicineByName(String name) {
        Query<Medicine> query = queryHelper.getMedicineQueryBuilder()
                .where(MedicineDao.Properties.Name.like(name))
                .build();

        return query.list();
    }

    public List<Medicine> getAllMedicine() {
        Query<Medicine> query = queryHelper.getMedicineQueryBuilder()
                .build();

        return query.list();
    }

    public long insertMedicine(Medicine medicine) {
        return daoSession.getMedicineDao().insert(medicine);
    }

    public void insertMedicineList(List<Medicine> medicineList) {
        for(Medicine i: medicineList) {
            daoSession.getMedicineDao().insert(i);
        }
    }

    public long insertMedicine_Count(Medicine_Count medicineCount) {
        return daoSession.getMedicine_CountDao().insert(medicineCount);
    }

    /**
     * @author KozMeeN
     * method update selected medicine in badabase.
     * @param medicine object which we want to update in database.
     */
    public void updateMedicine(Medicine medicine){
        daoSession.getMedicineDao().update(medicine);

    }

    public List<Medicine_Count> getMedicine_CountList() {
        Query<Medicine_Count> query = queryHelper.getMedicine_CountQueryBuilder()
                .build();
        return query.list();
    }

    public void deleteMedicineById(Long idMedicine) {
        Medicine medicine = daoSession.getMedicineDao().load(idMedicine);

        //DELETING ALL RELATIONS
        for(Alergens_List i: medicine.getAlergensList()) {
            daoSession.getAlergens_ListDao().delete(i);
        }
        for(Dose i: medicine.getDoseList()) {
            daoSession.getDoseDao().delete(i);
        }
        for(Information i: medicine.getInformationList()) {
            daoSession.getInformationDao().delete(i);
        }
        if(medicine.getMedicine_Count() != null)
            daoSession.getMedicine_CountDao().delete(medicine.getMedicine_Count());
        ////////////////////////

        daoSession.getMedicineDao().deleteByKey(idMedicine);
    }


    public long insertDose(Dose dose) {
        return daoSession.getDoseDao().insert(dose);
    }

    public void deleteDoseById(Long idDose) {
        Dose dose = daoSession.getDoseDao().load(idDose);

        //remove from medicines list of doses
        daoSession.getMedicineDao().load(idDose).getDoseList().remove(idDose);
        daoSession.getDoseDao().deleteByKey(idDose);
    }

    public void updateDose(Dose dose) {
        daoSession.getDoseDao().update(dose);
    }

    public Dose getDoseById(long idDose) {
        Query<Dose> query = queryHelper.getDoseQueryBuilder()
                .where(DoseDao.Properties.Id.eq(idDose))
                .limit(1)
                .build();

        return query.unique();
    }

    public List<Dose> getAllDosesForMedicineById(long idMedicine) {
        Query<Dose> query = queryHelper.getDoseQueryBuilder()
                .where(DoseDao.Properties.IdMedicine.eq(idMedicine))
                .build();

        return query.list();
    }

    //IF YOU WANT TO ANY OTHER QUERY YOU CAN WRITE HERE IT


}
