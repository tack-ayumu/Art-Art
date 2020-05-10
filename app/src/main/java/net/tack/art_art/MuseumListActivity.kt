package net.tack.art_art

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_museum_list.*


//2:美術館(museum)の検索結果  RecyclerViewで表示する
class MuseumListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_list)

        //検索結果（リスト表示）
        val catchData = intent.getSerializableExtra("RESULTS") as ArrayList<RowModel>
        val recyclerView = recyclerView_museumlist
        val adapter = ViewAdapter2(catchData, object : ViewAdapter2.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                this.onClickRow(tappedView, rowModel)
            }
        })

        //画面上部に検索件数の表示
        val catchData2 =intent.getStringExtra("RESULTS2")
        textView_NumberOfSearches.text = catchData2

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
