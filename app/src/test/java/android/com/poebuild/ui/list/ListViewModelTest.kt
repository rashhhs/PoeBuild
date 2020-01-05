package android.com.poebuild.ui.list

import android.com.poebuild.repository.model.data.*
import android.com.poebuild.ui.data.UnitView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer:Observer<PagedList<UnitView>>
    @Mock
    lateinit var observer1:Observer<ListViewModel.filter>

    @Mock
    lateinit var passiveSkill: PassiveSkill
    @Mock
    lateinit var armour:Armour
    @Mock
    lateinit var skillGem:SkillGem
    @Mock
    lateinit var weapon:Weapon
    @Mock
    lateinit var otherItem:OtherItem

    @Mock
    lateinit var liveData: LiveData<PagedList<UnitView>>

    lateinit var viewModel: ListViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = ListViewModel()
        viewModel.setPassive(passiveSkill)
        viewModel.setArmour(armour)
        viewModel.setWeapon(weapon)
        viewModel.setOtherItem(otherItem)
        viewModel.setKillGem(skillGem)
    }

    /*
        Every time the filter change. The data (LiveData) switch between different tables on the database, doing different callings
     */
    @Test
    fun liveData(){
        val spy = spy(viewModel)

        whenever(spy.passiveInstance).thenReturn(passiveSkill)
        whenever(spy.armourInstance).thenReturn(armour)
        whenever(passiveSkill.get()).thenReturn(liveData)
        whenever(armour.get()).thenReturn(liveData)

        spy.actualFilter.observeForever(observer1)
        spy.data.observeForever(observer)

        spy.actualFilter.value = ListViewModel.filter.ARMOURS
        spy.actualFilter.value = ListViewModel.filter.PASSIVE_SKILLS
        spy.actualFilter.value = ListViewModel.filter.WEAPONS
        spy.actualFilter.value = ListViewModel.filter.WEAPONS


        verify(passiveSkill, times(2)).get()
        verify(armour, times(1)).get()
        verify(weapon, times(2)).get()
    }
}