package com.example.myapplication

interface TextDestination {
    val route: String
    val ordinal: Int

    companion object {
         var old_route:Int = 0
    }
}

//private val YourTextData = listOf(
//    "精选","男生","女生","出版"
//    ,"会员精选","免费","听书","漫画",
//)



val TextTabRowScreens = listOf(Chosen, Boy, Girl,Book,HuiYuan,Free,Listen,Comic)


object Chosen:TextDestination {
    override val route: String = "精选"
    override val ordinal: Int
        get() = 0

}

object Boy:TextDestination {
    override val route: String = "男生"
    override val ordinal: Int
        get() = 1
}

object Girl:TextDestination {
    override val route: String = "女生"
    override val ordinal: Int
        get() = 2

}

object Book : TextDestination {
    override val route: String
        get() = "出版"
    override val ordinal: Int
        get() = 3
}

object HuiYuan : TextDestination {
    override val route: String
        get() = "会员精选"
    override val ordinal: Int
        get() = 4

}

object Free : TextDestination {
    override val route: String
        get() = "免费"
    override val ordinal: Int
        get() = 5

}

object Listen : TextDestination {
    override val route: String
        get() = "听书"
    override val ordinal: Int
        get() = 6

}

object Comic : TextDestination {
    override val route: String
        get() = "漫画"
    override val ordinal: Int
        get() = 7

}









