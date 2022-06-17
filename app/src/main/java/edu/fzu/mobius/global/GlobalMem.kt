package edu.fzu.mobius.global

class GlobalMem {
    companion object {
        //起始页面
        const val START_NAV = "login_screen"
        // 接口根地址
        const val BASE_URL = "http://101.132.99.44:8998"
        // 匿名信数量
        const val ANON_NUM = 5
        // 可收信的时间
        val ANON_TIME_LIST = listOf("10:00","12:00","14:00","16:00","18:00","20:00","22:00","00:00","02:00")
        var ANON_TIME = "22:00"
        // 心情值
        var MOOD = 1
        //昵称
        var NICK_NAME = "黄埔牛牛 "


        //路由名称

    }
}