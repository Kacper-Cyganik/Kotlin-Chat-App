package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2

    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.sent_message)
    }

    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receivedMessage = itemView.findViewById<TextView>(R.id.received_message)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // receive
        return if(viewType == 1) {
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive, parent, false)
            ReceiveViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(context).inflate(R.layout.send, parent, false)
            SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if(holder.javaClass == SentViewHolder::class.java){
            // stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        }else{
            // stuff for receiver view holder
            val viewHolder = holder as ReceiveViewHolder
            holder.receivedMessage.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        var currentMessage = messageList[position]

        return if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sender)){
            ITEM_SENT
        } else {
            ITEM_RECEIVED
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

}