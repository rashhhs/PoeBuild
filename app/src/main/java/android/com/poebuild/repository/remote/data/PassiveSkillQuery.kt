package android.com.poebuild.repository.remote.data

import android.com.poebuild.repository.model.data.PassiveSkill
import com.google.gson.annotations.SerializedName

class PassiveSkillQuery:
    POEQuery<PassiveSkillQuery>() {

    @SerializedName(QUERY)
    var query: List<Wrapper> = ArrayList()

    inner class Wrapper {

        @SerializedName(OBJECT)
        var wrapp: PassiveSkill =
            PassiveSkill()
    }

    companion object{
        private const val QUERY = "cargoquery"
        private const val OBJECT = "title"
    }
}