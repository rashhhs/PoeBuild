package android.com.poebuild.repository.model.database.daos

import android.com.poebuild.repository.model.data.Weapon
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface WeaponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeapons(armours:List<Weapon>): Completable

    @Query("DELETE FROM weapons")
    fun deleteWeapons()

    @Query("SELECT * FROM weapons ORDER BY name")
    fun getWeapons(): DataSource.Factory<Int,Weapon>
}