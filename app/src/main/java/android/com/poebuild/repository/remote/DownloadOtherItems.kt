package android.com.poebuild.repository.remote

import android.com.poebuild.repository.model.data.OtherItem
import android.com.poebuild.repository.remote.data.OtherItemQuery
import android.com.poebuild.repository.remote.data.POEQuery
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DownloadOtherItems(val service:DownloadServiceInterface):DownloadLayer {
    override fun download() {
        getDownload(0,500,1,null)
    }

    fun getDownload(offset: Int, limit: Int, lastQuerySize: Int, errorQuery: Throwable?){
        //(R): Review the if clause
        if(errorQuery == null && (lastQuerySize > 0 || offset == 0)){
            executeObservable(offset, limit,
                service.getOtherItems(limit.toString(), offset.toString(),"json"))
        }
    }

    fun executeObservable(offset:Int, limit:Int, observable: Single<OtherItemQuery>){
        observable.subscribeOn(Schedulers.newThread())
            .subscribe { t1, t2 ->
                getDownload(offset+limit, limit, t1.query.size, t2)
                store(t1)
            }
    }

    override fun <T : POEQuery<T>> store(values: T) {
        val otherItem = OtherItem()
        if(values is OtherItemQuery){
            otherItem.insert(values.query.map { wrapper -> wrapper.wrapp })
        }
    }
}