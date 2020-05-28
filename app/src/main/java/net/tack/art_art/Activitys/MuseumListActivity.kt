package net.tack.art_art.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_museum_list.*
import net.tack.art_art.API.APIClient3
import net.tack.art_art.R
import net.tack.art_art.RowModel.RowModel
import net.tack.art_art.Adapter.ViewAdapter2
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//2:美術館(museum)の検索結果  RecyclerViewで表示する
class MuseumListActivity : AppCompatActivity() {

    lateinit var dataOfMuseumName:String
    lateinit var dataOfAddressNumber:String
    lateinit var dataOfAddress:String
    lateinit var dataOftelNumber:String
    lateinit var dataOfMuseumUrl:String
    lateinit var dataOfImage:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_list)

        //検索結果（リスト表示）
        val catchData = intent.getSerializableExtra("RESULTS") as ArrayList<RowModel>
        val recyclerView = recyclerView_museumlist
        val adapter = ViewAdapter2(
            catchData,
            object : ViewAdapter2.ListListener {
                override fun onClickRow(urlOfMuseum: String) {
                    Log.d("urlOfMuseum", urlOfMuseum)

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

        //画面上部に検索件数の表示
        val catchData2 =intent.getStringExtra("RESULTS2")
        textView_NumberOfSearches2.text = catchData2

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //recyclerViewに区切りの横ラインを入れる
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)


    }


    //ArtScape内の「ミュージアム検索」のWEBSITEからデータを取得する
    private fun searchData(searchResult:String?){
        val document = Jsoup.parse(searchResult)
        val moreSearch =document.select("div.mainColHeader")
        val imageSearch =document.select("div.imageArea")

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

        //美術館の画像
        dataOfImage = "https://artscape.jp" + imageSearch.select("img").attr("src")

        val intent = Intent(this@MuseumListActivity, MuseumInfoActivity::class.java)
        intent.putExtra("detail1", dataOfMuseumName)
        intent.putExtra("detail2", dataOfAddressNumber)
        intent.putExtra("detail3", dataOfAddress)
        intent.putExtra("detail4", dataOftelNumber)
        intent.putExtra("detail5", dataOfMuseumUrl)
        intent.putExtra("detail6",dataOfImage)

        startActivity(intent)
    }
}

