package smartcity.kni.wirtualnaapteczka;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INFORMATION".
*/
public class InformationDao extends AbstractDao<Information, Long> {

    public static final String TABLENAME = "INFORMATION";

    /**
     * Properties of entity Information.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Message = new Property(1, String.class, "message", false, "MESSAGE");
        public final static Property Date = new Property(2, java.util.Date.class, "date", false, "DATE");
        public final static Property Time = new Property(3, java.util.Date.class, "time", false, "TIME");
        public final static Property Alert = new Property(4, Boolean.class, "alert", false, "ALERT");
        public final static Property Regular = new Property(5, Boolean.class, "regular", false, "REGULAR");
        public final static Property IdMedicine = new Property(6, Long.class, "idMedicine", false, "ID_MEDICINE");
    }

    private DaoSession daoSession;


    public InformationDao(DaoConfig config) {
        super(config);
    }
    
    public InformationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INFORMATION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MESSAGE\" TEXT," + // 1: message
                "\"DATE\" INTEGER," + // 2: date
                "\"TIME\" INTEGER," + // 3: time
                "\"ALERT\" INTEGER," + // 4: alert
                "\"REGULAR\" INTEGER," + // 5: regular
                "\"ID_MEDICINE\" INTEGER);"); // 6: idMedicine
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INFORMATION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Information entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String message = entity.getMessage();
        if (message != null) {
            stmt.bindString(2, message);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(3, date.getTime());
        }
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(4, time.getTime());
        }
 
        Boolean alert = entity.getAlert();
        if (alert != null) {
            stmt.bindLong(5, alert ? 1L: 0L);
        }
 
        Boolean regular = entity.getRegular();
        if (regular != null) {
            stmt.bindLong(6, regular ? 1L: 0L);
        }
 
        Long idMedicine = entity.getIdMedicine();
        if (idMedicine != null) {
            stmt.bindLong(7, idMedicine);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Information entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String message = entity.getMessage();
        if (message != null) {
            stmt.bindString(2, message);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(3, date.getTime());
        }
 
        java.util.Date time = entity.getTime();
        if (time != null) {
            stmt.bindLong(4, time.getTime());
        }
 
        Boolean alert = entity.getAlert();
        if (alert != null) {
            stmt.bindLong(5, alert ? 1L: 0L);
        }
 
        Boolean regular = entity.getRegular();
        if (regular != null) {
            stmt.bindLong(6, regular ? 1L: 0L);
        }
 
        Long idMedicine = entity.getIdMedicine();
        if (idMedicine != null) {
            stmt.bindLong(7, idMedicine);
        }
    }

    @Override
    protected final void attachEntity(Information entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Information readEntity(Cursor cursor, int offset) {
        Information entity = new Information( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // message
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // date
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // time
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // alert
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // regular
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // idMedicine
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Information entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMessage(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setTime(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setAlert(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setRegular(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setIdMedicine(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Information entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Information entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Information entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
