package com.carlos.grabredenvelope.db

object WechatRedEnvelopeDb {

    private val sQQRedEnvelopeDao = GreenDaoManager.instance.session.wechatRedEnvelopeDao

    val allData: List<WechatRedEnvelope>
        @Synchronized get() = sQQRedEnvelopeDao.loadAll()

    @Synchronized
    fun insertData(qqRedEnvelope: WechatRedEnvelope) {
        sQQRedEnvelopeDao.insert(qqRedEnvelope)
    }
}
