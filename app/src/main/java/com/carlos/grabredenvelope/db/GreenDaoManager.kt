package com.carlos.grabredenvelope.db

import com.carlos.grabredenvelope.MyApplication

class GreenDaoManager {
    var master: DaoMaster  // 以一定的模式管理Dao类的数据库对象
    var session: DaoSession  // 管理制定模式下的所有可用Dao对象
    var newSession: DaoSession? = null  // 不能删除
        get() {
            session = master.newSession()
            return session
        }

    init {
        val devOpenHelper = UpgradeHelper(
            MyApplication.instance.applicationContext,
            "grabredenvelope",
            null,
            WechatRedEnvelopeDao::class.java,
            DingDingRedEnvelopeDao::class.java
        )
//        val devOpenHelper = DaoMaster.DevOpenHelper(MyApplication.instance.applicationContext, "grabredenvelope", null)
        master = DaoMaster(devOpenHelper.writableDatabase)
        session = master.newSession()
    }

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            GreenDaoManager()
        }
    }
}
