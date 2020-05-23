package net.tack.art_art

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val titleView: TextView = itemView.findViewById(R.id.textView_title)

    val dateView: TextView = itemView.findViewById(R.id.textView_date)

    val nameOfMuseumView: TextView = itemView.findViewById(R.id.textView_nameOfMuseum)

    val favoriteIcon :ImageView = itemView.findViewById(R.id.imageView_bookMark1)

    val favoriteIcon2 :ImageView = itemView.findViewById(R.id.imageView_bookMark3)


}