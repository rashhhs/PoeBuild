package android.com.poebuild.repository.remote.data

import android.com.poebuild.repository.model.data.SkillGem
import com.google.gson.annotations.SerializedName

class SkillGemsQuery:
    POEQuery<SkillGemsQuery>() {
    @SerializedName(QUERY)
    var query:List<Wrapper> = ArrayList()

    inner class Wrapper(){
        @SerializedName(OBJECT)
        var wrapp: SkillGem =
            SkillGem()
    }

    companion object{
        private const val QUERY = "cargoquery"
        private const val OBJECT = "title"
    }
}