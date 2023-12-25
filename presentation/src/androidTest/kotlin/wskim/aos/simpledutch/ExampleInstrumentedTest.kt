package wskim.aos.simpledutch

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("wskim.aos.simpledutch", appContext.packageName)

        runBlocking {
            composeTestRule.setContent {
                val count = remember { mutableIntStateOf(0) }
                Button(onClick = { count.intValue = count.intValue + 1 }) {
                    Text(
                        text = "test${count.intValue}",
                        modifier = Modifier.testTag("test")
                    )
                }
            }
            composeTestRule.onNodeWithTag("test").performClick()
            composeTestRule.onNodeWithTag("test").performClick()
            delay(1000)
            composeTestRule.onNodeWithTag("test").assertExists()
            composeTestRule.onNodeWithTag("test").assertTextEquals("test2")
        }
    }
}