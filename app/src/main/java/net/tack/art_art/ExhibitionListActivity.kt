package net.tack.art_art

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_exhibition_list.*

//1:美術展（exhibition)の検索結果  RecyclerViewで表示する

class ExhibitionListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibition_list)

        val catchData = intent.getSerializableExtra(EXTRA_RESULTS) as ArrayList<RowModel>

        val recyclerView = recyclerView_exhibitionlist
        val adapter = ViewAdapter(catchData, object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                this.onClickRow(tappedView, rowModel)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //画面上部に検索件数の表示
        val catchData2 =intent.getStringExtra(EXTRA_RESULTS2)
        textView_NumberOfSearches1.text = catchData2

        //recyclerViewに区切りの横ラインを入れる
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        recyclerView.setOnClickListener {

        }
    }


    fun onClickRow(tappedView: View, rowModel: RowModel) {
        Snackbar.make(
            tappedView,
            "Replace with your own action tapped ${rowModel.title}",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null).show()
    }

}