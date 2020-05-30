package net.tack.art_art.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.tack.art_art.R
import net.tack.art_art.RowModel.RowModel
import net.tack.art_art.VIewHolder.HomeViewHolder_favoriteEx

class ViewAdapter_FavoriteEx(private val list: ArrayList<RowModel>, private val listener: ViewAdapter.ListListener) : RecyclerView.Adapter<HomeViewHolder_favoriteEx>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder_favoriteEx {
        Log.d("Adapter", "onCreateViewHolder")
        val rowView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_exhibition_list, parent, false)
        return HomeViewHolder_favoriteEx(rowView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder_favoriteEx, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.titleViewFavoriteEx.text = list[position].title
        holder.dateViewFavoriteEx.text = list[position].date
        holder.nameOfMuseumViewFavoriteEx.text = list[position].nameOfMuseum

    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }
}