package com.company.kalasetu.model

data class Order(
    var orderId: String = "",
    var productId: String = "",
    var customerId:String="",
    var artisanId:String="",
    var productName: String = "",
    var customerEmail: String = "",
    var price:String="",
    var status: String = "Pending"
)