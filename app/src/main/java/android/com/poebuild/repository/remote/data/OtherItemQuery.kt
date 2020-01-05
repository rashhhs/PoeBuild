package android.com.poebuild.repository.remote.data

import android.com.poebuild.repository.model.data.OtherItem
import com.google.gson.annotations.SerializedName

class OtherItemQuery:
    POEQuery<OtherItemQuery>() {

    @SerializedName(QUERY)
    var query:List<Wrapper> = ArrayList()

    inner class Wrapper(){
        @SerializedName(OBJECT)
        var wrapp: OtherItem =
            OtherItem()
    }

    companion object{
        private const val QUERY = "cargoquery"
        private const val OBJECT = "title"
    }
}