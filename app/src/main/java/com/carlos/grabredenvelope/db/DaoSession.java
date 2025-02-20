package com.carlos.grabredenvelope.db;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dingDingRedEnvelopeDaoConfig;
    private final DaoConfig wechatRedEnvelopeDaoConfig;

    private final DingDingRedEnvelopeDao dingDingRedEnvelopeDao;
    private final WechatRedEnvelopeDao wechatRedEnvelopeDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dingDingRedEnvelopeDaoConfig = daoConfigMap.get(DingDingRedEnvelopeDao.class).clone();
        dingDingRedEnvelopeDaoConfig.initIdentityScope(type);

        wechatRedEnvelopeDaoConfig = daoConfigMap.get(WechatRedEnvelopeDao.class).clone();
        wechatRedEnvelopeDaoConfig.initIdentityScope(type);

        dingDingRedEnvelopeDao = new DingDingRedEnvelopeDao(dingDingRedEnvelopeDaoConfig, this);
        wechatRedEnvelopeDao = new WechatRedEnvelopeDao(wechatRedEnvelopeDaoConfig, this);

        registerDao(DingDingRedEnvelope.class, dingDingRedEnvelopeDao);
        registerDao(WechatRedEnvelope.class, wechatRedEnvelopeDao);
    }
    
    public void clear() {
        dingDingRedEnvelopeDaoConfig.clearIdentityScope();
        wechatRedEnvelopeDaoConfig.clearIdentityScope();
    }

    public DingDingRedEnvelopeDao getDingDingRedEnvelopeDao() {
        return dingDingRedEnvelopeDao;
    }

    public WechatRedEnvelopeDao getWechatRedEnvelopeDao() {
        return wechatRedEnvelopeDao;
    }

}
