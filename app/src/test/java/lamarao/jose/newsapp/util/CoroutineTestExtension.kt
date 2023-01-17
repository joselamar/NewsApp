package lamarao.jose.newsapp.util

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestInstancePostProcessor

class CoroutineTestExtension : TestInstancePostProcessor {

   private val testScope = TestScope(UnconfinedTestDispatcher())

   override fun postProcessTestInstance(
           testInstance: Any?, 
           context: ExtensionContext?
   ) {
       (testInstance as? CoroutineTest)?.let { it.testScope = testScope }
   }
}

@ExtendWith(CoroutineTestExtension::class)
interface CoroutineTest {
    var testScope: TestScope
}