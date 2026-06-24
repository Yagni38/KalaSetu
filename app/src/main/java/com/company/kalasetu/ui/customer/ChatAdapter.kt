package com.company.kalasetu.ui.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.ChatMessage
import android.view.Gravity
import android.graphics.Color

class ChatAdapter(
    private val list: ArrayList<ChatMessage>
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
            val txtSender: TextView = view.findViewById(R.id.txtSender)

        val txtMessage =
            view.findViewById<TextView>(R.id.txtMessage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_chat,
                parent,
                false
            )

        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(
        holder: ChatViewHolder,
        position: Int
    ) {

        val chat = list[position]
        holder.txtSender.text = chat.senderName
        holder.txtMessage.text = chat.message


    }
}