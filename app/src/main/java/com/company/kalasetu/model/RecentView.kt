package com.company.kalasetu.model

data class RecentView (
    var customerId:String="",
    var productId:String="",
    var timestamp:Long=System.currentTimeMillis()
    )
