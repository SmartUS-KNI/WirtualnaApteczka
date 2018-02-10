package smartcity.kni.wirtualnaapteczka.net.database;

import org.greenrobot.greendao.query.QueryBuilder;

import smartcity.kni.wirtualnaapteczka.Alergen;
import smartcity.kni.wirtualnaapteczka.DaoSession;
import smartcity.kni.wirtualnaapteczka.Dose;
import smartcity.kni.wirtualnaapteczka.Information;
import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.Tag;

/**
 * Created by Aleksander on 10.02.2018.
 */

public class QueryHelper {
    DaoSession daoSession;

    public QueryHelper(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public QueryBuilder<Medicine> getMedicineQueryBuilder() {
        return daoSession.getMedicineDao().queryBuilder();
    }

    public QueryBuilder<Alergen> getAlergenQueryBuilder() {
        return daoSession.getAlergenDao().queryBuilder();
    }

    public QueryBuilder<Dose> getDoseQueryBuilder() {
        return daoSession.getDoseDao().queryBuilder();
    }

    public QueryBuilder<Information> getInformationQueryBuilder() {
        return daoSession.getInformationDao().queryBuilder();
    }

    public QueryBuilder<Tag> getTagQueryBuilder() {
        return daoSession.getTagDao().queryBuilder();
    }
}
