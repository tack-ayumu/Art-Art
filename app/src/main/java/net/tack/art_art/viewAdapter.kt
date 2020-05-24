package net.tack.art_art

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ViewAdapter(private val list: ArrayList<RowModel>, private val listener: ListListener) : RecyclerView.Adapter<HomeViewHolder>(){
//    lateinit var realm:Realm


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
        if (list[position].isFavorite) {
            holder.favoriteIcon2.visibility = View.VISIBLE
            holder.favoriteIcon.visibility = View.GONE
        } else {
            holder.favoriteIcon.visibility = View.VISIBLE
            holder.favoriteIcon2.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            listener.onClickRow(list[position].urlOfMuseum)
        }

//        realm = Realm.getDefaultInstance()

//        holder.favoriteIcon.setImageResource(R.drawable.bookmarkstar)
//
        holder.favoriteIcon.setOnClickListener{
            holder.favoriteIcon2.visibility = View.VISIBLE
            holder.favoriteIcon.visibility = View.GONE
            listener.onClickFavoriteIcon(true, list[position])

//                realm.beginTransaction()
//                val dataBase = realm.createObject(RowModel::class.java)
//                dataBase.title = "isFavorite"
//                realm.commitTransaction()

        }

        holder.favoriteIcon2.setOnClickListener{
            holder.favoriteIcon.visibility = View.VISIBLE
            holder.favoriteIcon2.visibility = View.GONE
            listener.onClickFavoriteIcon(false, list[position])

//            realm.beginTransaction()
//
//            val results:RealmResults<RowModel> = realm.where(RowModel::class.java).findAll()
//            val selectDB = results.get(position)
//
//            selectDB?.deleteFromRealm()
//            realm.commitTransaction()


        }
    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(urlOfMuseum:String)
        fun onClickFavoriteIcon(isFavorite: Boolean, data: RowModel)
    }
}



