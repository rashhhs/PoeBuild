package android.com.poebuild.repository.remote.data

import android.com.poebuild.repository.model.data.Armour
import com.google.gson.annotations.SerializedName


class ArmourQuery:
    POEQuery<ArmourQuery>() {

    @SerializedName(QUERY)
    var query:List<Wrapper> = ArrayList()

    inner class Wrapper{
        @SerializedName(OBJECT)
        var wrapp: Armour =
            Armour()
    }

    companion object{
        private const val QUERY = "cargoquery"
        private const val OBJECT = "title"
    }
}