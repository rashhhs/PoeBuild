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

@Entity(tableName = "armours")
data class Armour(
    @SerializedName("name")
    @PrimaryKey
    @field:ColumnInfo(name="name")
    var name:String = ""
):DomainLayer<Armour,UnitView> {
    @SerializedName("armour")
    @field:ColumnInfo(name = "armour")
    var armour:String = ""

    @SerializedName("class id")
    @field:ColumnInfo(name="class_id")
    var classID: String = ""

    @SerializedName("drop level")
    @field:ColumnInfo(name = "drop_level")
    var dropLevel:String = ""

    @SerializedName("energy shield")
    @field:ColumnInfo(name = "energy_shield")
    var energyShield:String = ""

    @SerializedName("evasion")
    @field:ColumnInfo(name = "evasion")
    var evasion:String = ""

    @SerializedName("inventory icon")
    @field:ColumnInfo(name = "iventory_icon")
    var inventoryIcon:String = ""

    @SerializedName("movement speed")
    @field:ColumnInfo(name = "movement_speed")
    var movementSpeed:String = ""

    @SerializedName("stat text")
    @field:ColumnInfo(name = "stat_text")
    var statText:String = ""

    override fun getDatabase(): Database {
        return Repository.instance.database
    }

    override fun insert(values: List<Armour>) {
        getDatabase().armourDao.insertArmours(values)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun get(): LiveData<PagedList<UnitView>> {
        return getDatabase().armourDao.getArmours().mapByPage { input ->  input.map { modelToUnitView(it) } }.toLiveData(pageSize = 50)
    }

    fun modelToUnitView(value: Armour): UnitView {
        return UnitView.Builder()
            .setTitle(value.name)
            .setDescription(value.statText)
            .build()
    }
}