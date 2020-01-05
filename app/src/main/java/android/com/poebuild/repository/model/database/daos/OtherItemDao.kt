package android.com.poebuild.repository.model.database.daos

import android.com.poebuild.repository.model.data.OtherItem
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface OtherItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(armours:List<OtherItem>): Completable

    @Query("DELETE FROM other_items")
    fun deleteItems()

    @Query("SELECT * FROM other_items ORDER BY name")
    fun getItems(): DataSource.Factory<Int,OtherItem>
}