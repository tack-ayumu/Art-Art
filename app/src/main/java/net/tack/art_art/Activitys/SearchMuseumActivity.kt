package net.tack.art_art.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_museum.*
import net.tack.art_art.API.APIClient2
import net.tack.art_art.R
import net.tack.art_art.RowModel.RowModel
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


    //artscapeのURLを生成する ※editTextの処理は「キーワード検索」の反映
    fun searchMuseum() {
        val apiClient2 = APIClient2
        apiClient2.searchMuseums(selected_area, 1, editText_keyword2.text.toString(), "", "on")
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

    //artscape内の情報を抽出する
    private fun document(searchResult:String?)  {
        val document = Jsoup.parse(searchResult)
        val exhiInfo = document.select("div.exhiInfo")
        val exhiInfo2 =document.select("div.mainColHeader")

        //美術館名、美術館の住所、開催中の美術展のタイトルを抽出する
        val dataList = ArrayList<RowModel>()
        for (i in exhiInfo.indices) {
            val data : RowModel = RowModel()
                .also {
                it.nameOfMuseum = exhiInfo[i].select("a").text()
                it.museumAddress = exhiInfo[i].select("p.mdbAddress").text()
                it.title = exhiInfo[i].select("span.detail").text()
                val elementsOfMuseum = exhiInfo[i].select("a")
                it.urlOfMuseum = "https://artscape.jp" + elementsOfMuseum.attr("href")
            }
            dataList.add(data)
        }

        //検索件数を抽出する
        val numberOfSearches = exhiInfo2.select("div.mainColHeading").text()

        //intentで情報を引き渡す
        val intent = Intent(this@SearchMuseumActivity,
            MuseumListActivity::class.java).apply {
                putExtra("RESULTS",dataList)
                putExtra("RESULTS2",numberOfSearches)
            }

        startActivity(intent)
    }
}






