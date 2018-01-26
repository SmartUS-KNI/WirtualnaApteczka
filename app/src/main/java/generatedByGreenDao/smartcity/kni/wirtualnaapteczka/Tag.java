package smartcity.kni.wirtualnaapteczka;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import smartcity.kni.wirtualnaapteczka.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "TAG".
 */
@Entity(active = true)
public class Tag {

    @Id(autoincrement = true)
    private Long id;
    private String name;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient TagDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "tagId")
    })
    private List<Tags_List> tagsList;

    @Generated
    public Tag() {
    }

    public Tag(Long id) {
        this.id = id;
    }

    @Generated
    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTagDao() : null;
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
    public List<smartcity.kni.wirtualnaapteczka.Tags_List> getTagsList() {
        if (tagsList == null) {
            __throwIfDetached();
            Tags_ListDao targetDao = daoSession.getTags_ListDao();
            List<Tags_List> tagsListNew = targetDao._queryTag_TagsList(id);
            synchronized (this) {
                if(tagsList == null) {
                    tagsList = tagsListNew;
                }
            }
        }
        return tagsList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetTagsList() {
        tagsList = null;
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