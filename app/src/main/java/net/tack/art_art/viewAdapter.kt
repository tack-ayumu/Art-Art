package net.tack.art_art

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ViewAdapter(private val list: ArrayList<RowModel>, private val listener: ListListener) : RecyclerView.Adapter<HomeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
        val rowView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.exhibition_list, parent, false)
        return HomeViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.titleView.text = list[position].title
        holder.dateView.text = list[position].date
        holder.nameOfMuseumView.text = list[position].nameOfMuseum
        holder.itemView.setOnClickListener {
            listener.onClickRow(list[position].urlOfMuseum)
        }
//        holder.favoriteIcon.setImageResource(R.drawable.bookmarkstar)
//
//        holder.favoriteIcon.setOnClickListener{
//            listener.onClickFavorite()
//        }

    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(urlOfMuseum:String)
    }


    interface favoriteIconListener{
        fun onClickFavoriteIcon(sFavorite: Boolean, data: RowModel)
    }



}




