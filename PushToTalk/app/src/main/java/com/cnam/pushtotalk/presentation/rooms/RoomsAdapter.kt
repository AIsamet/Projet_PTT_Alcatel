package com.cnam.pushtotalk.presentation.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.cnam.pushtotalk.R
import com.cnam.pushtotalk.domain.rooms.Room

class CanalListAdapter (private val mList: List<Room>, private val onRoomClicked: (Room) -> (Unit)) : RecyclerView.Adapter<CanalListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanalListHolder {
        val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.canal_button, parent, false)
        return CanalListHolder(view)
    }

    override fun onBindViewHolder(holder: CanalListHolder, position: Int) {
        //change attributes (text) of canal_button
        holder.btnChannel.text = mList[position].name
        holder.btnChannel.setOnClickListener {
            onRoomClicked(mList[position])
        }
    }

    override fun getItemCount(): Int =
        mList.size
}

class CanalListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    //list des vues à mettre à jour
    val btnChannel: Button = itemView.findViewById(R.id.btn_channel)
}