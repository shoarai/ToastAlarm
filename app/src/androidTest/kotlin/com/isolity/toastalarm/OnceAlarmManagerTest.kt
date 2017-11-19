package com.isolity.toastalarm

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.isolity.toastalarm.alarm.OnceAlarmManager
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class OnceAlarmManagerTest {
    private val delayTime = 1000

//    @Before
//    fun setUp() {
//
//    }

    @Test
//    @Throws(Exception::class)
    fun startAlarm() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MILLISECOND, delayTime)

        OnceAlarmManager.startAlarm(appContext, calendar)


//        assertEquals("com.isolity.toastalarm", appContext.packageName)
    }
}
