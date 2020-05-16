package net.tack.art_art

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_exhibition_list.*
import org.jsoup.Jsoup

//1:美術展（exhibition)の検索結果  RecyclerViewで表示する

class ExhibitionListActivity : AppCompatActivity() {

    lateinit var dataOfMuseumName:String
    lateinit var dataOfAddressNumber:String
    lateinit var dataOfAddress:String
    lateinit var dataOftelNumber:String
    lateinit var dataOfMuseumUrl:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibition_list)


        val catchData = intent.getSerializableExtra(EXTRA_RESULTS) as ArrayList<RowModel>

        val recyclerView = recyclerView_exhibitionlist
        val adapter = ViewAdapter(catchData, object : ViewAdapter.ListListener {
            override fun onClickRow(urlOfMuseum:String) {
                Log.d("urlOfMuseum",urlOfMuseum)
                searchData(urlOfMuseum)
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

    }


    fun onClickRow(tappedView: View, rowModel: RowModel) {
        Snackbar.make(
            tappedView,
            "Replace with your own action tapped ${rowModel.title}",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null).show()
    }

    private fun searchData(url:String){
        val document = Jsoup.connect(url).get()

        //美術館名の取得
        dataOfMuseumName = document.select("div.mainColHeading").text()

        //美術館の郵便番号の取得
        dataOfAddressNumber = document.select("p.zip").text()

        //美術館の住所の取得
        dataOfAddress = document.select("p.address").text()

        //美術館の電話番号の取得
        dataOftelNumber = document.select("p.tel").text()

        //美術館の公式ウェブサイトのurl
        val elementsOfUrlMuseum = document.select("ul.boxLinkC")
        val elementsOfUrlMuseum2 = elementsOfUrlMuseum.select("a")
        dataOfMuseumUrl = elementsOfUrlMuseum2.attr("href")

        val intent = Intent(this@ExhibitionListActivity, MuseumInfo::class.java)
        intent.putExtra("detail1", dataOfMuseumName)
        intent.putExtra("detail2", dataOfAddressNumber)
        intent.putExtra("detail3", dataOfAddress)
        intent.putExtra("detail4", dataOftelNumber)
        intent.putExtra("detail5", dataOfMuseumUrl)

        startActivity(intent)
    }


}