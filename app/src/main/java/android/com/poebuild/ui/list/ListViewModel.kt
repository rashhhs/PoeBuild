package android.com.poebuild.ui.list

import android.com.poebuild.repository.model.data.*
import android.com.poebuild.ui.data.UnitView
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class ListViewModel : ViewModel() {

    enum class filter {ALL,PASSIVE_SKILLS, SKILL_GEMS, WEAPONS, ARMOURS, OTHER_ITEMS}
    var actualFilter: MutableLiveData<filter> = MutableLiveData()
    var data : LiveData<PagedList<UnitView>>

    var passiveInstance: PassiveSkill = PassiveSkill()
    var armourInstance: Armour = Armour()
    var otherItemInstance:OtherItem = OtherItem()
    var weaponInstance:Weapon = Weapon()
    var skillGemInstance:SkillGem = SkillGem()

    init {
        actualFilter.value = filter.ALL
        data = Transformations.switchMap(
           actualFilter
        ) {
           when(actualFilter.value){
                filter.ARMOURS -> armourInstance.get()
                filter.WEAPONS -> weaponInstance.get()
                filter.OTHER_ITEMS -> otherItemInstance.get()
                filter.SKILL_GEMS -> skillGemInstance.get()
                filter.PASSIVE_SKILLS -> passiveInstance.get()
                else ->passiveInstance.get()
            }
        }
    }

    fun setArmour(armour:Armour){
        armourInstance = armour
    }

    fun setOtherItem(otherItem: OtherItem){
        otherItemInstance = otherItem
    }

    fun setKillGem(skillGem: SkillGem){
        skillGemInstance = skillGem
    }

    fun setPassive(passiveSkill: PassiveSkill){
       passiveInstance = passiveSkill
    }

    fun setWeapon(weapon:Weapon){
        weaponInstance = weapon
    }
}

