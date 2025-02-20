package com.carlos.grabredenvelope.db

object DingDingRedEnvelopeDb {

    private val dingDingRedEnvelopeDao = GreenDaoManager.instance.session.dingDingRedEnvelopeDao

    val allData: List<DingDingRedEnvelope>
        @Synchronized get() = dingDingRedEnvelopeDao.loadAll()

    @Synchronized
    fun insertData(dingDingRedEnvelope: DingDingRedEnvelope) {
        dingDingRedEnvelopeDao.insert(dingDingRedEnvelope)
    }
}
