package android.com.poebuild.repository.model.database.daos

import android.com.poebuild.repository.model.data.SkillGem
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface SkillGemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSkillGems(skillGems:List<SkillGem>): Completable

    @Query("DELETE FROM skill_gems")
    fun deleteSkillGems()

    @Query("SELECT * FROM skill_gems ORDER BY name")
    fun getSkillGems(): DataSource.Factory<Int,SkillGem>
}