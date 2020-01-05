package android.com.poebuild.repository.remote.data

import android.com.poebuild.repository.model.data.Weapon
import com.google.gson.annotations.SerializedName

class WeaponQuery: POEQuery<WeaponQuery>() {
    @SerializedName(QUERY)
    var query:List<Wrapper> = ArrayList()

    inner class Wrapper(){
        @SerializedName(OBJECT)
        var wrapp: Weapon =
            Weapon()
    }

    companion object{
        private const val QUERY = "cargoquery"
        private const val OBJECT = "title"
    }
}