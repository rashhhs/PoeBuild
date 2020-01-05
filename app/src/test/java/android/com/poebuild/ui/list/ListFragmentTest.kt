package android.com.poebuild.ui.list

import android.widget.ToggleButton
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ListFragmentTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var filterButton: ToggleButton

    @Mock
    lateinit var filterButton1: ToggleButton

    val fragment: ListFragment = ListFragment()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    /*
        If the user clicks on a filter, already checked. It should be called a second time pulling back the click
     */
    @Test
    fun clickOnCheckedFilter(){
        val spy = spy(fragment)
        spy.lastFilterClicked = filterButton

        whenever(spy.lastFilterClicked).thenReturn(filterButton)
        doNothing().whenever(spy).setActualFilter(any())
        whenever(spy.unDoLastClick(eq(filterButton))).thenAnswer{ spy.onClickFilter(true,filterButton,ListViewModel.filter.PASSIVE_SKILLS) }

        spy.onClickFilter(false,filterButton, ListViewModel.filter.PASSIVE_SKILLS)

        verify(spy, after(200)!!.times(2)).unDoLastClick(any())
    }

    /*
        Scenario where a user clicks on two filters consecutively. The recyclerview should shows the values depending the filter clicked
        Only one filter should be checked at the same time
     */
    @Test
    fun clicksOnFilters(){
        val spy = spy(fragment)
        spy.lastFilterClicked = filterButton

        doNothing().whenever(spy).setActualFilter(any())

        spy.onClickFilter(true, filterButton1,ListViewModel.filter.ARMOURS)

        Assert.assertEquals(spy.lastFilterClicked, filterButton1)

        spy.onClickFilter(true, filterButton, ListViewModel.filter.PASSIVE_SKILLS)

        Assert.assertEquals(spy.lastFilterClicked, filterButton)
    }
}