package net.tack.art_art

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm



class ViewAdapter(private val list: ArrayList<RowModel>, private val listener: ListListener) : RecyclerView.Adapter<HomeViewHolder>(){
    lateinit var realm:Realm


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

        realm = Realm.getDefaultInstance()

//        holder.favoriteIcon.setImageResource(R.drawable.bookmarkstar)
//
        holder.favoriteIcon.setOnClickListener{
            holder.favoriteIcon2.visibility = View.VISIBLE
            holder.favoriteIcon.visibility = View.GONE

                realm.beginTransaction()
                val dataBase = realm.createObject(RowModel::class.java)
                dataBase.title = "isFavorite"
                realm.commitTransaction()

        }

        holder.favoriteIcon2.setOnClickListener{
            holder.favoriteIcon.visibility = View.VISIBLE
            holder.favoriteIcon2.visibility = View.GONE
//            listener.onClickFavoriteIcon()
        }



    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(urlOfMuseum:String)
    }


    interface favoriteIconListener{
        fun onClickFavoriteIcon(isFavorite: Boolean, data: RowModel)
    }





}




