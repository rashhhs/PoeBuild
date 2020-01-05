package android.com.poebuild.repository.model.database

import android.com.poebuild.repository.model.database.daos.*
import android.content.Context
import androidx.room.Room

class Database {
    lateinit var passiveSkillDao: PassiveSkillDao
    lateinit var skillGemDao: SkillGemDao
    lateinit var armourDao: ArmourDao
    lateinit var weaponDao: WeaponDao
    lateinit var gemDao: SkillGemDao
    lateinit var otherItemDao:OtherItemDao

    fun init(context: Context){
        val roomDB = Room.databaseBuilder(context, AppDatabase::class.java, "poe-build-instance")
            .fallbackToDestructiveMigration()
            .build()
        passiveSkillDao = roomDB.passiveSkillsDao()
        skillGemDao = roomDB.skillGemDao()
        armourDao = roomDB.armourDao()
        weaponDao = roomDB.weaponDao()
        gemDao = roomDB.skillGemDao()
        otherItemDao = roomDB.otherItemDao()
    }
}