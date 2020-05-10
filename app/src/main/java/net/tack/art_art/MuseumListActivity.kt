package net.tack.art_art

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_museum_list.*


//美術館の検索結果
class MuseumListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_list)

        val catchData2 = intent.getSerializableExtra("RESULTS") as ArrayList<RowModel>

        val recyclerView = recyclerView_museumlist
        val adapter = ViewAdapter2(catchData2, object : ViewAdapter2.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                this.onClickRow(tappedView, rowModel)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //recyclerViewに区切りの横ラインを入れる
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        recyclerView.setOnClickListener {


        }

    }


    fun onClickRow(tappedView: View, rowModel:RowModel) {
        Snackbar.make(
            tappedView,
            "Replace with your own action tapped ${rowModel.title}",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null).show()
    }
}
