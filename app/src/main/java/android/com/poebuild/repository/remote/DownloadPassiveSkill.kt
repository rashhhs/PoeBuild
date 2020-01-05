package android.com.poebuild.repository.remote

import android.com.poebuild.repository.model.data.PassiveSkill
import android.com.poebuild.repository.remote.data.POEQuery
import android.com.poebuild.repository.remote.data.PassiveSkillQuery
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DownloadPassiveSkill(val service: DownloadServiceInterface): DownloadLayer{

    override fun download(){
        getDownload(0, 500, 1,null)
    }

    fun getDownload(offset: Int, limit: Int, lastQuerySize: Int, errorQuery: Throwable?){
        //(R): Review the if clause
        if(errorQuery == null && (lastQuerySize > 0 || offset == 0)){
            executeObservable(offset, limit,
                service.getPassiveSkills("cargoquery","passive_skills","id,name,icon,is_keystone,is_notable,is_icon_only,is_jewel_socket,stat_text",limit.toString(), offset.toString(), "json"))
        }
    }

    fun executeObservable(offset:Int, limit:Int, observable: Single<PassiveSkillQuery>){
        observable.subscribeOn(Schedulers.newThread())
            .subscribe { t1, t2 ->
                getDownload(offset+limit, limit, t1.query.size, t2)
                store(t1)
            }
    }

    override fun <T : POEQuery<T>> store(values: T) {
        val passiveSkill = PassiveSkill()
        if(values is PassiveSkillQuery)
            passiveSkill.insert(values.query.map { wrapper -> wrapper.wrapp})
    }
}