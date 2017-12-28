package smartcity.kni.wirtualnaapteczka;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TAGS__LIST".
*/
public class Tags_ListDao extends AbstractDao<Tags_List, Long> {

    public static final String TABLENAME = "TAGS__LIST";

    /**
     * Properties of entity Tags_List.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MedicineId = new Property(1, Long.class, "medicineId", false, "MEDICINE_ID");
        public final static Property TagId = new Property(2, Long.class, "tagId", false, "TAG_ID");
    }

    private DaoSession daoSession;

    private Query<Tags_List> medicine_TagsListQuery;
    private Query<Tags_List> tag_TagsListQuery;

    public Tags_ListDao(DaoConfig config) {
        super(config);
    }
    
    public Tags_ListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TAGS__LIST\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MEDICINE_ID\" INTEGER," + // 1: medicineId
                "\"TAG_ID\" INTEGER);"); // 2: tagId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TAGS__LIST\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Tags_List entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long medicineId = entity.getMedicineId();
        if (medicineId != null) {
            stmt.bindLong(2, medicineId);
        }
 
        Long tagId = entity.getTagId();
        if (tagId != null) {
            stmt.bindLong(3, tagId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Tags_List entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long medicineId = entity.getMedicineId();
        if (medicineId != null) {
            stmt.bindLong(2, medicineId);
        }
 
        Long tagId = entity.getTagId();
        if (tagId != null) {
            stmt.bindLong(3, tagId);
        }
    }

    @Override
    protected final void attachEntity(Tags_List entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Tags_List readEntity(Cursor cursor, int offset) {
        Tags_List entity = new Tags_List( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // medicineId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // tagId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Tags_List entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMedicineId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTagId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Tags_List entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Tags_List entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Tags_List entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "tagsList" to-many relationship of Medicine. */
    public List<Tags_List> _queryMedicine_TagsList(Long medicineId) {
        synchronized (this) {
            if (medicine_TagsListQuery == null) {
                QueryBuilder<Tags_List> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.MedicineId.eq(null));
                medicine_TagsListQuery = queryBuilder.build();
            }
        }
        Query<Tags_List> query = medicine_TagsListQuery.forCurrentThread();
        query.setParameter(0, medicineId);
        return query.list();
    }

    /** Internal query to resolve the "tagsList" to-many relationship of Tag. */
    public List<Tags_List> _queryTag_TagsList(Long tagId) {
        synchronized (this) {
            if (tag_TagsListQuery == null) {
                QueryBuilder<Tags_List> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TagId.eq(null));
                tag_TagsListQuery = queryBuilder.build();
            }
        }
        Query<Tags_List> query = tag_TagsListQuery.forCurrentThread();
        query.setParameter(0, tagId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getMedicineDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getTagDao().getAllColumns());
            builder.append(" FROM TAGS__LIST T");
            builder.append(" LEFT JOIN MEDICINE T0 ON T.\"MEDICINE_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN TAG T1 ON T.\"TAG_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Tags_List loadCurrentDeep(Cursor cursor, boolean lock) {
        Tags_List entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Medicine medicine = loadCurrentOther(daoSession.getMedicineDao(), cursor, offset);
        entity.setMedicine(medicine);
        offset += daoSession.getMedicineDao().getAllColumns().length;

        Tag tag = loadCurrentOther(daoSession.getTagDao(), cursor, offset);
        entity.setTag(tag);

        return entity;    
    }

    public Tags_List loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Tags_List> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Tags_List> list = new ArrayList<Tags_List>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Tags_List> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Tags_List> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
