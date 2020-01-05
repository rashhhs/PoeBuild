package android.com.poebuild.repository.remote

import android.com.poebuild.repository.remote.data.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

//(R): For the queries with joins and where, it seems Retrofit doesn't know how to handle it. And creates the field incorrectly.But, if you add this query inside the url it works (Weapons, Armours...)
interface DownloadServiceInterface {
    // example passive skills = https://pathofexile.gamepedia.com/api.php?action=cargoquery&tables=passive_skills&fields=id,name,icon,is_keystone,is_notable,is_icon_only,is_jewel_socket,stat_text&limit=500&offset=0&format=json
    // example skill gems = https://pathofexile.gamepedia.com/api.php?action=cargoquery&tables=items&fields=class_id,drop_level,name,stat_text&where=class_id=%22Active%20Skill%20Gem%22%20OR%20class_id=%22Support%20Skill%20Gem%22&limit=500&offset=0&format=json
    // example armours = https://pathofexile.gamepedia.com/api.php?action=cargoquery&tables=armours,items&join%20on=armours._pageID=items._pageID&fields=armour,class_id,drop_level,energy_shield,evasion,inventory_icon,movement_speed,name,stat_text&where=class_id=%22Body%20Armour%22%20OR%20class_id=%22Boots%22%20OR%20class_id=%22Gloves%22%20OR%20class_id=%22Helmet%22%20OR%20class_id=%22Shield%22&limit=500&offset=0
    // example weapons = https://pathofexile.gamepedia.com/api.php?action=cargoquery&tables=weapons,items&join%20on=weapons._pageID=items._pageID&fields=attack_speed,chaos_damage_max,chaos_damage_min,class_id,cold_damage_max,cold_damage_min,critical_strike_chance,damage_avg,dps_range_average,drop_level,fire_damage_max,fire_damage_min,flavour_text,inventory_icon,is_corrupted,lightning_damage_max,lightning_damage_min,name,physical_damage_max,physical_damage_min,required_level,required_dexterity,required_intelligence,required_strength,stat_text,weapon_range&where=class_id=%22Claw%22&limit=500&offset=0
    // example other = https://pathofexile.gamepedia.com/api.php?action=cargoquery&tables=items&fields=class_id,drop_level,name,stat_text&where=class_id=%22Amulet%22%20OR%20class_id=%22Belt%22%20OR%20class_id=%22Flask%22%20OR%20class_id=%22Jewel%22%20OR%20class_id=%22Quiver%22%20OR%20class_id=%22Ring%22%20OR%20class_id=%22UtilityFlask%22&limit=500&offset=0&format=json

    @GET("api.php")
    fun getPassiveSkills(@Query(ACTION_QUERY)action:String, @Query(TABLE_QUERY)table:String, @Query(FIELDS_QUERY)fields:String,
                         @Query(LIMIT_QUERY)limit:String, @Query(OFFSET_QUERY)offset:String, @Query(FORMAT)format:String): Single<PassiveSkillQuery>

    @GET("""api.php?action=cargoquery&tables=items&fields=class_id,drop_level,name,stat_text&where=class_id=%22Active%20Skill%20Gem%22%20OR%20class_id=%22Support%20Skill%20Gem%22""")
    fun getSkillGems(@Query(LIMIT_QUERY)limit:String, @Query(OFFSET_QUERY)offset:String, @Query(FORMAT)format:String):Single<SkillGemsQuery>

    @GET("api.php?action=cargoquery&tables=armours,items&join%20on=armours._pageID=items._pageID&fields=armour,class_id,drop_level,energy_shield,evasion,inventory_icon,movement_speed,name,stat_text&where=class_id=%22Body%20Armour%22%20OR%20class_id=%22Boots%22%20OR%20class_id=%22Gloves%22%20OR%20class_id=%22Helmet%22%20OR%20class_id=%22Shield%22")
    fun getArmours(@Query(LIMIT_QUERY)limit:String, @Query(OFFSET_QUERY)offset:String, @Query(FORMAT)format:String):Single<ArmourQuery>

    @GET("api.php?action=cargoquery&tables=weapons,items&join%20on=weapons._pageID=items._pageID&fields=attack_speed,chaos_damage_max,chaos_damage_min,class_id,cold_damage_max,cold_damage_min,critical_strike_chance,damage_avg,dps_range_average,drop_level,fire_damage_max,fire_damage_min,flavour_text,inventory_icon,is_corrupted,lightning_damage_max,lightning_damage_min,name,physical_damage_max,physical_damage_min,required_level,required_dexterity,required_intelligence,required_strength,stat_text,weapon_range&where=class_id=%22Bow%22%20OR%20class_id=%22Claw%22%20OR%20class_id=%22Dagger%22%20OR%20class_id=%22One%20Hand%20Axe%22%20OR%20class_id=%22One%20Hand%Sword%22%20OR%20class_id=%22One%20Hand%20Mace%22%20OR%20class_id=%22Rune%20Dagger%22%20OR%20class_id=%22Sceptre%22%20OR%20class_id=%22Stave%22%20OR%20class_id=%22Thrusting%20One%20Hand%20Sword%22%20OR%20class_id=%22Two%20Hand%20Axe%22%20OR%20class_id=%22Two%20Hand%20Mace%22%20OR%20class_id=%22Two%20Hand%20Sword%22%20OR%20class_id=%22Wand%22")
    fun getWeapons(@Query(LIMIT_QUERY)limit:String, @Query(OFFSET_QUERY)offset:String, @Query(FORMAT)format:String):Single<WeaponQuery>

    @GET("api.php?action=cargoquery&tables=items&fields=class_id,drop_level,inventory_icon,name,stat_text&where=class_id=%22Amulet%22%20OR%20class_id=%22Belt%22%20OR%20class_id=%22Flask%22%20OR%20class_id=%22Jewel%22%20OR%20class_id=%22Quiver%22%20OR%20class_id=%22Ring%22%20OR%20class_id=%22UtilityFlask%22")
    fun getOtherItems(@Query(LIMIT_QUERY)limit:String, @Query(OFFSET_QUERY)offset:String, @Query(FORMAT)format:String):Single<OtherItemQuery>

    companion object{
        private const val ACTION_QUERY = "action"
        private const val TABLE_QUERY = "tables"
        private const val JOIN_QUERY = "join%20on"
        private const val FIELDS_QUERY = "fields"
        private const val WHERE_QUERY = "where"
        private const val LIMIT_QUERY = "limit"
        private const val OFFSET_QUERY = "offset"
        private const val FORMAT = "format"
    }
}