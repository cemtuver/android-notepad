package tuver.notepad.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
abstract class BaseViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("ViewModel test thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        onSetup()
    }

    open fun onSetup() {}

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        onTearDown()
    }

    open fun onTearDown() {}

}