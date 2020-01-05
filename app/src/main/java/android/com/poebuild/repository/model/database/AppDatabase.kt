package android.com.poebuild.repository.model.database

import android.com.poebuild.repository.model.data.*
import android.com.poebuild.repository.model.database.daos.*
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Armour::class,PassiveSkill::class,SkillGem::class,Weapon::class, OtherItem::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun passiveSkillsDao(): PassiveSkillDao
    abstract fun skillGemDao():SkillGemDao
    abstract fun weaponDao():WeaponDao
    abstract fun armourDao():ArmourDao
    abstract fun otherItemDao():OtherItemDao
}