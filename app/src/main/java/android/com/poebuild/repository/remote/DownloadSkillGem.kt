package android.com.poebuild.repository.remote

import android.com.poebuild.repository.model.data.SkillGem
import android.com.poebuild.repository.remote.data.POEQuery
import android.com.poebuild.repository.remote.data.SkillGemsQuery
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DownloadSkillGem(val service:DownloadServiceInterface):DownloadLayer  {
    override fun download() {
        getDownload(0,500,1,null)
    }

    fun getDownload(offset: Int, limit: Int, lastQuerySize: Int, errorQuery: Throwable?){
        //(R): Review the if clause
        if(errorQuery == null && (lastQuerySize > 0 || offset == 0)){
            executeObservable(offset, limit,
                service.getSkillGems(limit.toString(), offset.toString(),"json"))
        }
    }

    fun executeObservable(offset:Int, limit:Int, observable: Single<SkillGemsQuery>){
        observable.subscribeOn(Schedulers.newThread())
            .subscribe { t1, t2 ->
                getDownload(offset+limit, limit, t1.query.size, t2)
                store(t1)
            }
    }

    override fun <T : POEQuery<T>> store(values: T) {
       val skillGems = SkillGem()
        if(values is SkillGemsQuery)
            skillGems.insert(values.query.map { wrapper -> wrapper.wrapp })
    }
}