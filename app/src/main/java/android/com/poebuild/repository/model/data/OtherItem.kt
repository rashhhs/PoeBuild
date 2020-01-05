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

@Entity(tableName = "other_items")
data class OtherItem(@SerializedName("name")
                     @PrimaryKey
                     @field:ColumnInfo(name="name")
                     var name:String = ""
): DomainLayer<OtherItem,UnitView> {
    @SerializedName("class id")
    @field:ColumnInfo(name="class_id")
    var classID: String = ""

    @SerializedName("drop level")
    @field:ColumnInfo(name = "drop_level")
    var dropLevel:String = ""

    @SerializedName("inventory icon")
    @field:ColumnInfo(name = "iventory_icon")
    var inventoryIcon:String = ""

    @SerializedName("stat text")
    @field:ColumnInfo(name = "stat_text")
    var statText:String = ""

    override fun getDatabase(): Database {
        return Repository.instance.database
    }

    override fun insert(values: List<OtherItem>) {
        getDatabase().otherItemDao.insertItems(values)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun get(): LiveData<PagedList<UnitView>> {
        return getDatabase().otherItemDao.getItems().mapByPage { input ->  input.map { modelToUnitView(it) } }.toLiveData(pageSize = 150)
    }

    fun modelToUnitView(value: OtherItem): UnitView {
        return UnitView.Builder()
            .setTitle(value.name)
            .setDescription(value.statText)
            .build()
    }
}