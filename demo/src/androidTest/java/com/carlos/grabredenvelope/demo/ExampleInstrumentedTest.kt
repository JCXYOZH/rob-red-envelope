package com.carlos.grabredenvelope.demo

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


/*
 * 插桩测试，将在 Android 设备上执行。
 *
 * 参见 [testing documentation]（http://d.android.com/tools/testing）
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // 待测应用的上下文
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.carlos.grabredenvelope.demo", appContext.packageName)
    }
}