package android.com.poebuild.repository.model.data

import android.com.poebuild.repository.model.DomainLayer
import android.com.poebuild.repository.Repository
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

@Entity(tableName = "passive_skills")
data class PassiveSkill(
    @SerializedName("id")
    @PrimaryKey
    @field:ColumnInfo(name = "id")
    var id: String = ""
): DomainLayer<PassiveSkill,UnitView> {
    @SerializedName("name")
    @field:ColumnInfo(name = "name")
    var name: String = ""

    @SerializedName("icon")
    @field:ColumnInfo(name = "icon")
    var icon: String = ""

    @SerializedName("is keystone")
    @field:ColumnInfo(name = "keystone")
    var keystone: String = ""

    @SerializedName("is notable")
    @field:ColumnInfo(name = "notable")
    var notable: String = ""

    @SerializedName("is icon only")
    @field:ColumnInfo(name = "icon_only")
    var iconOnly: String = ""

    @SerializedName("is jewel socket")
    @field:ColumnInfo(name = "jewel_socket")
    var jewelSocket: String = ""

    @SerializedName("stat text")
    @field:ColumnInfo(name = "stat_text")
    var text: String = ""

    override fun getDatabase(): Database {
        return Repository.instance.database
    }

    override fun insert(values:List<PassiveSkill>) {
        getDatabase().passiveSkillDao.insertPassiveSkills(values)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun get(): LiveData<PagedList<UnitView>> {
        return getDatabase().passiveSkillDao.getPassiveSkills().mapByPage { input ->  input.map { modelToUnitView(it) } }.toLiveData(pageSize = 50)
    }

    fun modelToUnitView(value: PassiveSkill): UnitView {
        return UnitView.Builder()
            .setTitle(value.name)
            .setDescription(value.text)
            .build()
    }
}