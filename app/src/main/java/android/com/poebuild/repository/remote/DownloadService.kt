package android.com.poebuild.repository.remote

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DownloadService {
    private val baseURL = "https://pathofexile.gamepedia.com/"
    private var interface_service: DownloadServiceInterface
    private var disposable: CompositeDisposable

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        interface_service = retrofit.create(DownloadServiceInterface::class.java)
        disposable = CompositeDisposable()
    }

    fun donwloads(){
        val downloadPassiveSkills = DownloadPassiveSkill(interface_service)
        downloadPassiveSkills.download()
        val downloadArmour = DownloadArmour(interface_service)
        downloadArmour.download()
        val downloadWeapon = DownloadWeapon(interface_service)
        downloadWeapon.download()
        val downloadOtherItems = DownloadOtherItems(interface_service)
        downloadOtherItems.download()
        val downloadSkillGem = DownloadSkillGem(interface_service)
        downloadSkillGem.download()
    }

    companion object{
        val instance = DownloadService()
    }
}