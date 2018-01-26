package smartcity.kni.wirtualnaapteczka;

import org.greenrobot.greendao.annotation.*;

import smartcity.kni.wirtualnaapteczka.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "ALERGENS__LIST".
 */
@Entity(active = true)
public class Alergens_List {

    @Id(autoincrement = true)
    private Long id;
    private Long medicineId;
    private Long alergenId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient Alergens_ListDao myDao;

    @ToOne(joinProperty = "alergenId")
    private Alergen alergen;

    @Generated
    private transient Long alergen__resolvedKey;

    @ToOne(joinProperty = "medicineId")
    private Medicine medicine;

    @Generated
    private transient Long medicine__resolvedKey;

    @Generated
    public Alergens_List() {
    }

    public Alergens_List(Long id) {
        this.id = id;
    }

    @Generated
    public Alergens_List(Long id, Long medicineId, Long alergenId) {
        this.id = id;
        this.medicineId = medicineId;
        this.alergenId = alergenId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAlergens_ListDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getAlergenId() {
        return alergenId;
    }

    public void setAlergenId(Long alergenId) {
        this.alergenId = alergenId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public Alergen getAlergen() {
        Long __key = this.alergenId;
        if (alergen__resolvedKey == null || !alergen__resolvedKey.equals(__key)) {
            __throwIfDetached();
            AlergenDao targetDao = daoSession.getAlergenDao();
            Alergen alergenNew = targetDao.load(__key);
            synchronized (this) {
                alergen = alergenNew;
            	alergen__resolvedKey = __key;
            }
        }
        return alergen;
    }

    @Generated
    public void setAlergen(Alergen alergen) {
        synchronized (this) {
            this.alergen = alergen;
            alergenId = alergen == null ? null : alergen.getId();
            alergen__resolvedKey = alergenId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public Medicine getMedicine() {
        Long __key = this.medicineId;
        if (medicine__resolvedKey == null || !medicine__resolvedKey.equals(__key)) {
            __throwIfDetached();
            MedicineDao targetDao = daoSession.getMedicineDao();
            Medicine medicineNew = targetDao.load(__key);
            synchronized (this) {
                medicine = medicineNew;
            	medicine__resolvedKey = __key;
            }
        }
        return medicine;
    }

    @Generated
    public void setMedicine(Medicine medicine) {
        synchronized (this) {
            this.medicine = medicine;
            medicineId = medicine == null ? null : medicine.getId();
            medicine__resolvedKey = medicineId;
        }
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}