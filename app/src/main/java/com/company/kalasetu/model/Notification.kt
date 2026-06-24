package com.company.kalasetu.model

data class Notification(

    var title:String = "",

    var message:String = "",

    var userId:String = "",

    var timestamp:Long =
        System.currentTimeMillis()
)