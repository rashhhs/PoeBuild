package android.com.poebuild.repository.model.database.daos

import android.com.poebuild.repository.model.data.Armour
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface ArmourDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArmours(armours:List<Armour>):Completable

    @Query("DELETE FROM armours")
    fun deleteArmours()

    @Query("SELECT * FROM armours ORDER BY name")
    fun getArmours(): DataSource.Factory<Int,Armour>
}