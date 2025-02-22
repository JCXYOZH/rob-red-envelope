package com.carlos.grabredenvelope

import com.carlos.grabredenvelope.dao.WechatControlVO
import kotlinx.serialization.json.Json
import org.junit.Test

class SerializableTest {

    @Test
    fun testSerializable() {

        val wechatControlVO = WechatControlVO()

        val json = Json.encodeToString(WechatControlVO.serializer(), wechatControlVO)
        println("json:$json")


        val data = Json.decodeFromString(WechatControlVO.serializer(), json)
        println("isMonitor:${data.isMonitorChat}")

    }
}
