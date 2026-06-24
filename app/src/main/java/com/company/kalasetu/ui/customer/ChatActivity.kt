package com.company.kalasetu.ui.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.company.kalasetu.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.model.ChatMessage
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class ChatActivity : AppCompatActivity() {
    private lateinit var recyclerChat: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button

    private lateinit var adapter: ChatAdapter

    private var list = ArrayList<ChatMessage>()

    private var db =
        FirebaseFirestore.getInstance()
    private var artisanId = ""
    private var customerId = ""
    private var roomId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat)
        db = FirebaseFirestore.getInstance()
        artisanId = intent.getStringExtra("artisanId")?:""
        customerId = FirebaseAuth.getInstance().currentUser?.uid?:""
        roomId = if(customerId < artisanId)
            "${customerId}_${artisanId}"
        else{
            "${artisanId}_${customerId}"
        }

        recyclerChat =
            findViewById(R.id.recyclerChat)

        etMessage =
            findViewById(R.id.etMessage)

        btnSend =
            findViewById(R.id.btnSend)

        adapter = ChatAdapter(list)

        recyclerChat.layoutManager =
            LinearLayoutManager(this)

        recyclerChat.adapter = adapter
        btnSend.setOnClickListener {

            val msg =
                etMessage.text.toString()

            if (msg.isNotEmpty()) {

                val chat = ChatMessage(

                    senderId = "customer",

                    receiverId = "artisan",

                    message = msg
                )

                db.collection("Chats")
                    .add(chat)

                etMessage.setText("")
            }
        }

        loadMessages()
        btnSend.setOnClickListener{
            sendMessage()
        }
    }

    private fun loadMessages() {

        db.collection("Chats")
            .document(roomId)
            .collection("Messages")
            .addSnapshotListener { value, error ->

                if (error != null) return@addSnapshotListener

                if (value != null) {

                    list.clear()

                    for (doc in value.documents) {

                        val chat =
                            doc.toObject(
                                ChatMessage::class.java
                            )

                        if (chat != null) {

                            list.add(chat)
                        }
                    }

                    adapter.notifyDataSetChanged()
                    if (list.isNotEmpty()) {
                        recyclerChat.scrollToPosition(list.size - 1)
                    }
                }
            }
    }
    private fun sendMessage() {

        val text = etMessage.text.toString()

        if (text.isEmpty()) {
            return
        }

        val chat = ChatMessage(

            senderId = "Customer",
            senderName ="Customer",

            message = text,

            timestamp = System.currentTimeMillis()
        )

        db.collection("Chats")
            .add(chat)

        etMessage.setText("")
    }
}