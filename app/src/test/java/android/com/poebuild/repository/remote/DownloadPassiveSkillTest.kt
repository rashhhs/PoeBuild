package android.com.poebuild.repository.remote

import android.com.poebuild.repository.remote.data.PassiveSkillQuery
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DownloadPassiveSkillTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: DownloadServiceInterface

    lateinit var download: DownloadPassiveSkill
    var countCalls = 0

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        download = DownloadPassiveSkill(service)
    }

    fun simulateCalls():Int{
        var countQuery = 0
        when(countCalls){
            0 -> {
                countQuery = 500
            }1 -> {
                countQuery = 500
            }2 ->{
                countQuery = 500
            }else ->{ }
        }
        countCalls++
        return countQuery
    }

    /*
        Test to know if the download method do the job, downloading all the content and stopping when the response sends error or values.size = 0
     */
    @Test
    fun downloadPagination(){
        val spy = spy(download)

        val single = Single.just(PassiveSkillQuery())
        whenever(service.getPassiveSkills(any(), any(), any(), any(), any(), any())).thenReturn(single)
        doNothing().whenever(spy).executeObservable(any(), any(), any())

        spy.getDownload(0,500,simulateCalls(),null)
        spy.getDownload(500,500,simulateCalls(),null)
        spy.getDownload(1000,500,simulateCalls(),Throwable())
        spy.getDownload(1500,500,simulateCalls(),null)
        spy.getDownload(2000,500,simulateCalls(),null)
        spy.getDownload(2500,500,simulateCalls(),Throwable())

        verify(spy, times(2)).executeObservable(any(), any(), any())
    }


}