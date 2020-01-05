package android.com.poebuild.ui.data

data class UnitView(
    var id:String = ""
){
    var title: String = ""
    var description: String = ""

    open class Builder{
        var title: String = ""
        var description:String = ""

        fun setTitle(title:String) = apply { this.title = title }
        fun setDescription(description:String) = apply { this.description = description }

        fun build(): UnitView {
            val unitView = UnitView(title)
            unitView.title = title
            unitView.description = description
            return unitView
        }
    }
}