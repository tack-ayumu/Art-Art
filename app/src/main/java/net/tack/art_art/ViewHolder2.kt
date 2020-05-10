package net.tack.art_art

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val nameOfMuseumView2: TextView = itemView.findViewById(R.id.textView_nameOfMuseum2)

    val museumAddresView: TextView = itemView.findViewById((R.id.textView_museumAddres))

    val titleView2: TextView = itemView.findViewById(R.id.textView_Title2)
}