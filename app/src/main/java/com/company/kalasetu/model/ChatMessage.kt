package com.company.kalasetu.model

data class ChatMessage(

    var senderId: String = "",
    var senderName: String = "",

    var receiverId: String = "",

    var message: String = "",

    var timestamp: Long = System.currentTimeMillis()

)