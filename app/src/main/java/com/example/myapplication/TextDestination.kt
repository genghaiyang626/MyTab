package com.example.myapplication

interface TextDestination {
    val route: String
}

//private val YourTextData = listOf(
//    "精选","男生","女生","出版"
//    ,"会员精选","免费","听书","漫画",
//)



val TextTabRowScreens = listOf(Chosen, Boy, Girl,Book,HuiYuan,Free,Listen,Comic)

object Comic : TextDestination {
    override val route: String
        get() = "漫画"

}

object Listen : TextDestination {
    override val route: String
        get() = "听书"

}

object Free : TextDestination {
    override val route: String
        get() = "免费"

}

object HuiYuan : TextDestination {
    override val route: String
        get() = "会员精选"

}

object Book : TextDestination {
    override val route: String
        get() = "出版"
}

object Chosen:TextDestination {
    override val route: String = "精选"

}

object Boy:TextDestination {
    override val route: String = "男生"
}

object Girl:TextDestination {
    override val route: String = "女生"

}

