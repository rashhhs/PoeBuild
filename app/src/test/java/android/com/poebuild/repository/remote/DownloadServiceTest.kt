package android.com.poebuild.repository.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

class DownloadServiceTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }
}