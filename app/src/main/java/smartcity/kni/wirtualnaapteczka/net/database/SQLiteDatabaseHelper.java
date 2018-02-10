package smartcity.kni.wirtualnaapteczka.net.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import smartcity.kni.wirtualnaapteczka.DaoMaster;
import smartcity.kni.wirtualnaapteczka.DaoSession;
import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.MedicineDao;

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

    public Medicine getMedicineByName(String name) {
        Query<Medicine> query = queryHelper.getMedicineQueryBuilder()
                .where(MedicineDao.Properties.Name.eq(name))
                .limit(1)
                .build();

        return query.unique();
    }

    public List<Medicine> getAllMedicine() {
        Query<Medicine> query = queryHelper.getMedicineQueryBuilder()
                .build();

        return query.list();
    }

    public void insertMedicine(Medicine medicine) {
        daoSession.getMedicineDao().insert(medicine);
    }

    public void insertMedicineList(List<Medicine> medicineList) {
        for(Medicine i: medicineList) {
            daoSession.getMedicineDao().insert(i);
        }
    }

    //IF YOU WANT TO ANY OTHER QUERY YOU CAN WRITE HERE IT
}
