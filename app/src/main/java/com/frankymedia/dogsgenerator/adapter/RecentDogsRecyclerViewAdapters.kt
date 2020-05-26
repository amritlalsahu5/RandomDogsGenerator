package com.frankymedia.dogsgenerator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.frankymedia.dogsgenerator.databinding.RecentDogsItemLayoutBinding
import com.frankymedia.dogsgenerator.devicedatabase.RoomDogsInfo

/*
    Adapter to display All the recent dogs generated
 */
class RecentDogsRecyclerViewAdapters : RecyclerView.Adapter<RecentDogsRecyclerViewAdapters.UsersViewHolder>(){


    private var dogsList: List<RoomDogsInfo>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.usersViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(dogsList!![position])

    }

    fun setDogsList(dogsList: List<RoomDogsInfo>) {
        this.dogsList = dogsList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int =
         dogsList?.size ?: 0



    //creating users viewholder class for the recycler view
    class UsersViewHolder (private val itemBinding : RecentDogsItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: RoomDogsInfo?) {
            itemBinding.dogInfo = item
            itemBinding.executePendingBindings()
        }

        companion object {
            fun usersViewHolder(parent: ViewGroup): UsersViewHolder {
                val binding =
                    RecentDogsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return UsersViewHolder(
                    binding
                )
            }
        }

    }

}