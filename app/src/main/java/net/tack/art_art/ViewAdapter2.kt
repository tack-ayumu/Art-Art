package net.tack.art_art

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ViewAdapter2(private val list: ArrayList<RowModel>, private val listener: ListListener) : RecyclerView.Adapter<HomeViewHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder2 {
        Log.d("Adapter", "onCreateViewHolder")
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.museum_list, parent, false)
        return HomeViewHolder2(rowView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder2, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.nameOfMuseumView2.text = list[position].nameOfMuseum
        holder.museumAddresView.text = list[position].museumAddress
        holder.titleView2.text = list[position].title
        holder.itemView.setOnClickListener {
            listener.onClickRow(list[position].urlOfMuseum)
        }
    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(urlOfMuseum:String)
    }
}