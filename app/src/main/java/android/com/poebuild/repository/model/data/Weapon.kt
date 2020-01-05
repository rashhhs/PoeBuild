package android.com.poebuild.repository.model.data

import android.com.poebuild.repository.Repository
import android.com.poebuild.repository.model.DomainLayer
import android.com.poebuild.repository.model.database.Database
import android.com.poebuild.ui.data.UnitView
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.reactivex.schedulers.Schedulers


@Entity(tableName = "weapons")
class Weapon(
    @SerializedName("name")
    @PrimaryKey
    @field:ColumnInfo(name="name")
    var name:String = ""
):DomainLayer<Weapon,UnitView> {
    @SerializedName("attack speed")
    @field:ColumnInfo(name= "attack_speed")
    var attackSpeed:String = ""

    @SerializedName("chaos damage max")
    @field:ColumnInfo(name = "chaos_damage_max")
    var chaosMax: String = ""

    @SerializedName("chaos damage min")
    @field:ColumnInfo(name="chaos_damage_min")
    var chaosMin: String = ""

    @SerializedName("class id")
    @field:ColumnInfo(name="class_id")
    var classID: String = ""

    @SerializedName("cold damage max")
    @field:ColumnInfo(name = "cold_damage_max")
    var coldMax: String = ""

    @SerializedName("cold damage min")
    @field:ColumnInfo(name = "cold_damage_min")
    var coldMin: String = ""

    @SerializedName("is corrupted")
    @field:ColumnInfo(name = "is_corrupted")
    var corrupted:String = ""

    @SerializedName("critical strike chance")
    @field:ColumnInfo(name = "critical_strike_chance")
    var criticalChance:String = ""

    @SerializedName("damage avg")
    @field:ColumnInfo(name = "damage_avg")
    var damageAvg: String = ""

    @SerializedName("drop level")
    @field:ColumnInfo(name = "drop_level")
    var dropLevel:String = ""

    @SerializedName("fire damage max")
    @field:ColumnInfo(name = "fire_damage_max")
    var fireMax:String = ""

    @SerializedName("fire damage min")
    @field:ColumnInfo(name = "fire_damage_min")
    var fireMin:String = ""

    @SerializedName("flavour text")
    @field:ColumnInfo(name = "flavour_text")
    var flavourText:String = ""

    @SerializedName("inventory icon")
    @field:ColumnInfo(name = "iventory_icon")
    var inventoryIcon:String = ""

    @SerializedName("lightning damage max")
    @field:ColumnInfo(name = "lightning_damage_max")
    var lightningMax: String = ""

    @SerializedName("lightning damage min")
    @field:ColumnInfo(name = "lightning_damage_min")
    var lightningMin:String = ""

    @SerializedName("physical damage max")
    @field:ColumnInfo(name = "physical_damage_max")
    var physicalMax:String = ""

    @SerializedName("physical damage min")
    @field:ColumnInfo(name = "physical_damage_min")
    var physicalMin:String = ""

    @SerializedName("required level")
    @field:ColumnInfo(name = "required_level")
    var requiredLevel:String = ""

    @SerializedName("required dexterity")
    @field:ColumnInfo(name = "required_dexterity")
    var requiredDexterity:String = ""

    @SerializedName("required intelligence")
    @field:ColumnInfo(name = "required_intelligence")
    var requiredIntelligence:String = ""

    @SerializedName("required strength")
    @field:ColumnInfo(name = "required_strength")
    var requiredStrength:String = ""

    @SerializedName("stat text")
    @field:ColumnInfo(name = "stat_text")
    var statText:String = ""

    @SerializedName("weapon range")
    @field:ColumnInfo(name = "weapon_range")
    var weaponRange:String = ""

    override fun getDatabase(): Database {
        return Repository.instance.database
    }

    override fun insert(values: List<Weapon>) {
        getDatabase().weaponDao.insertWeapons(values)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun get(): LiveData<PagedList<UnitView>> {
        return getDatabase().weaponDao.getWeapons().mapByPage { input ->  input.map { modelToUnitView(it) } }.toLiveData(pageSize = 150)
    }

    fun modelToUnitView(value: Weapon): UnitView {
        return UnitView.Builder()
            .setTitle(value.name)
            .setDescription(value.statText)
            .build()
    }
}