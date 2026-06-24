

package com.company.kalasetu.model

data class Product(
    var id:String = "",
    var name: String = "",
    var price: String = "",
    var description: String="",
    var imageUrl: String = "",
    var category: String = "",
    var audioUrl: String = "",
    var stock: Int=0,
    var artisanId: String = ""
)