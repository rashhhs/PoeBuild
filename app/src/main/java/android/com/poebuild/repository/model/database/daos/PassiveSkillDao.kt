package android.com.poebuild.repository.model.database.daos

import android.com.poebuild.repository.model.data.PassiveSkill
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PassiveSkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPassiveSkills(passiveSkills:List<PassiveSkill>): Completable

    @Query("DELETE FROM passive_skills")
    fun deletePassiveSkills()

    @Query("SELECT * FROM passive_skills ORDER BY name")
    fun getPassiveSkills(): DataSource.Factory<Int,PassiveSkill>

    @Query("SELECT * FROM passive_skills")
    fun getAllPassiveSkills(): Single<List<PassiveSkill>>
}