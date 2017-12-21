package smartcity.kni.wirtualnaapteczka;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import smartcity.kni.wirtualnaapteczka.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "ALERGEN".
 */
@Entity(active = true)
public class Alergen {

    @Id(autoincrement = true)
    private Long id;
    private String name;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient AlergenDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "alergenId")
    })
    private List<Alergens_List> alergens_ListList;

    @Generated
    public Alergen() {
    }

    public Alergen(Long id) {
        this.id = id;
    }

    @Generated
    public Alergen(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAlergenDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Alergens_List> getAlergens_ListList() {
        if (alergens_ListList == null) {
            __throwIfDetached();
            Alergens_ListDao targetDao = daoSession.getAlergens_ListDao();
            List<Alergens_List> alergens_ListListNew = targetDao._queryAlergen_Alergens_ListList(id);
            synchronized (this) {
                if(alergens_ListList == null) {
                    alergens_ListList = alergens_ListListNew;
                }
            }
        }
        return alergens_ListList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetAlergens_ListList() {
        alergens_ListList = null;
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
