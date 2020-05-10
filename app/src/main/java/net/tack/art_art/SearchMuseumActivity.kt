package net.tack.art_art

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_museum.*
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//2：美術館（museum) 検索  地域検索、日付検索の条件を指定「検索」ボタンを押してMuseumListActivityへ
class SearchMuseumActivity : AppCompatActivity() {

    lateinit var selected_area: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_museum)

        //2-1：地域検索(現在地・全国・10エリア+47都道府県) リスト表示(スピナー）
        val arrayAdapter_area = ArrayAdapter.createFromResource(
            this, R.array.area, android.R.layout.simple_spinner_item
        )
        spinner_area2.adapter = arrayAdapter_area

        //選択したエリアの情報を取得
        fun fun_select_area() {
            selected_area = spinner_area2.selectedItem.toString()

            //【東北】【北関東】などのエリアについた括弧を外すためインデックスを使用してsubstringの処理をする
            val idx = spinner_area2.selectedItemPosition
            when (idx) {
                0, 1 -> selected_area = ""
            }
            when (selected_area.startsWith("【")) {
                true -> selected_area =
                    spinner_area2.selectedItem.toString().substring(1, selected_area.length - 1)
            }
        }

        //2-2：五十音順のスピナーを設定する
        val arrayAdapter_gojyuon = ArrayAdapter.createFromResource(
            this, R.array.gojyu_on, android.R.layout.simple_spinner_item
        )
        spinner_gojyu_on.adapter = arrayAdapter_gojyuon


        //2-5：「美術展を検索するボタン」を押す
        linearLayout_go_search_exhibition2.setOnClickListener() {
            fun_select_area()
            searchMuseum()
        }


    }


    fun searchMuseum() {
        val apiClient2 = APIClient2
        apiClient2.searchMuseums(selected_area, 1, "", "", "on")
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("FAILURE", t.message)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    document(response.body())
                    Log.d("RESPONSE", response.body())
                    Log.d("correct", response.body())
                }
            })
    }

    private fun document(searchResult:String?)  {
        val document = Jsoup.parse(searchResult)
        val exhiInfo = document.select("div.exhiInfo")
        val exhiInfo2 =document.select("div.mainColHeader")

        val dataList = ArrayList<RowModel>()
        for (i in exhiInfo.indices) {
            val data : RowModel = RowModel().also {
                it.nameOfMuseum = exhiInfo[i].select("a").text()
                it.museumAddress = exhiInfo[i].select("p.mdbAddress").text()
                it.title = exhiInfo[i].select("span.detail").text()

            }
            dataList.add(data)
        }

        //検索件数を表示する（intentで受け渡す）
        val NumberOfSearches = exhiInfo2.select("div.mainColHeading").text()


        val intent = Intent(this@SearchMuseumActivity,MuseumListActivity::class.java).apply {
                putExtra("RESULTS",dataList)
                putExtra("RESULTS2",NumberOfSearches)
            }

        startActivity(intent)
    }
}






