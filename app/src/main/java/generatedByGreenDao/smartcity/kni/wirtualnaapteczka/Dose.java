package smartcity.kni.wirtualnaapteczka;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import smartcity.kni.wirtualnaapteczka.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "DOSE".
 */
@Entity(active = true)
public class Dose {

    @Id(autoincrement = true)
    private Long id;
    private java.util.Date time;
    private Integer count;
    private Long idMedicine;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient DoseDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "idMedicine")
    })
    private List<Medicine> medicineList;

    @Generated
    public Dose() {
    }

    public Dose(Long id) {
        this.id = id;
    }

    @Generated
    public Dose(Long id, java.util.Date time, Integer count, Long idMedicine) {
        this.id = id;
        this.time = time;
        this.count = count;
        this.idMedicine = idMedicine;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDoseDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getTime() {
        return time;
    }

    public void setTime(java.util.Date time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(Long idMedicine) {
        this.idMedicine = idMedicine;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Medicine> getMedicineList() {
        if (medicineList == null) {
            __throwIfDetached();
            MedicineDao targetDao = daoSession.getMedicineDao();
            List<Medicine> medicineListNew = targetDao._queryDose_MedicineList(id);
            synchronized (this) {
                if(medicineList == null) {
                    medicineList = medicineListNew;
                }
            }
        }
        return medicineList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetMedicineList() {
        medicineList = null;
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
