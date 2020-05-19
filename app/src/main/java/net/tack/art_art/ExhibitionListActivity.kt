package net.tack.art_art

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exhibition_list.*
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

                //ArtScape内の「ミュージアム検索」のUrlを生成する
                if (urlOfMuseum.startsWith("https://artscape.jp/mdb/")) {
                    val id = urlOfMuseum.substring(24)

                    val apiClient = APIClient3
                    apiClient.searchMuseums(id)
                        .enqueue(object : Callback<String> {
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Log.d("FAILURE", t.message)
                            }

                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                searchData(response.body())
                                Log.d("RESPONSE", response.body())
                                Log.d("correct", response.body())
                            }
                        })
                }
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



    //ArtScape内の「ミュージアム検索」のWEBSITEからデータを取得する
    private fun searchData(searchResult:String?){
        val document = Jsoup.parse(searchResult)
        val moreSearch =document.select("div.mainColHeader")

        //美術館名の取得
        dataOfMuseumName = moreSearch.select("div.mainColHeading").text()

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