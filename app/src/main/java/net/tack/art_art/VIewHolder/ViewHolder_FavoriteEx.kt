package net.tack.art_art.VIewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.tack.art_art.R

class HomeViewHolder_favoriteEx(itemView: View) : RecyclerView.ViewHolder(itemView){

    val titleViewFavoriteEx: TextView = itemView.findViewById(R.id.textView_titleOfFavoriteEx)

    val dateViewFavoriteEx: TextView = itemView.findViewById(R.id.textView_dateFavoriteEx)

    val nameOfMuseumViewFavoriteEx: TextView = itemView.findViewById(R.id.textView_nameOfMuseumFavoriteEx)

}