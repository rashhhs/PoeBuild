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

@Entity(tableName = "skill_gems")
class SkillGem(
    @SerializedName("name")
    @PrimaryKey
    @field:ColumnInfo(name="name")
    var name:String = ""
):DomainLayer<SkillGem,UnitView>  {

    override fun getDatabase(): Database {
        return Repository.instance.database
    }

    override fun insert(values: List<SkillGem>) {
        getDatabase().skillGemDao.insertSkillGems(values)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun get(): LiveData<PagedList<UnitView>> {
       return getDatabase().skillGemDao.getSkillGems().mapByPage { input ->  input.map { modelToUnitView(it) } }.toLiveData(pageSize = 50)
    }

    fun modelToUnitView(value: SkillGem): UnitView {
        return UnitView.Builder()
            .setTitle(value.name)
            .setDescription(value.name)
            .build()
    }
}