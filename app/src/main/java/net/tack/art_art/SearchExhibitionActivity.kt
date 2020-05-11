package net.tack.art_art

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_exhibition.*
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
const val EXTRA_RESULTS = "EXTRA_RESULTS"
const val EXTRA_RESULTS2 = "EXTRA_RESULTS2"

//1:美術展（exhibition) 検索  地域検索、日付検索の条件を指定「検索」ボタンを押してExhibitionListActivityへ

class SearchExhibitionActivity : AppCompatActivity() {
    lateinit var keyWord:String
    lateinit var selected_year:String
    lateinit var selected_month:String
    lateinit var selected_day:String
    lateinit var selected_area:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_exhibition)



        //1-1:地域検索(現在地・全国・10エリア+47都道府県) リスト表示(スピナー）
            val arrayAdapter_area = ArrayAdapter.createFromResource(
                this, R.array.area, android.R.layout.simple_spinner_item
            )
            spinner_area.adapter = arrayAdapter_area

            //選択したエリアの情報を取得
            fun fun_select_area() {
               selected_area = spinner_area.selectedItem.toString()

                //【東北】【北関東】などのエリアについた括弧を外すためインデックスを使用してsubstringの処理をする
                val idx = spinner_area.selectedItemPosition
                when (idx) {
                    0, 1 -> selected_area = ""
                }
                when (selected_area.startsWith("【")) {
                    true -> selected_area =
                        spinner_area.selectedItem.toString().substring(1, selected_area.length - 1)
                }
            }



       //1-2:日付検索 date-pickerを使用する
        val calender = java.util.Calendar.getInstance()
        val year = calender.get(java.util.Calendar.YEAR)
        val month = calender.get(java.util.Calendar.MONTH)
        val month_plusone = month+1
        val day = calender.get(java.util.Calendar.DAY_OF_MONTH)

        //日付設定を「本日」でデフォルト表示（月と日は一桁の場合、頭に「０」をつける設定）
          textView_year.text = year.toString()
          textView_month.text = month_plusone.toString()
          when(month_plusone){
            in 1..8 -> textView_month.text = "0"+ month_plusone.toString()
          }
          textView_day.text = day.toString()
          when(day){
              in 1..9 -> textView_day.text = "0"+ day.toString()
          }

          val dtp = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ view, y, m, d ->
              textView_year.text = "$y"
              textView_month.text = "${m+1}"
              textView_day.text = "$d"
          when(m){
              in 1..8 ->textView_month.text ="0${m+1}" }
          when(d){
              in 1..9 -> textView_day.text = "0$d"
          }}, year, month, day)

          linearLayout_date.setOnClickListener {
              dtp.show()
          }

          //選択した年月日をテキスト化
          fun fun_select_date() {
              selected_year = textView_year.text.toString()
              selected_month = textView_month.text.toString()
              selected_day = textView_day.text.toString()
          }


        //1-3：「お気に入り」ボタン編集中の旨、toast通知
        linearLayout_search_bookmark2.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }

        //1-4：「履歴検索」ボタン編集中の旨、toast通知
        linearLayout_search_history.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }


        //1-5：「美術展を検索する」を押す
        linearLayout_go_search_exhibition.setOnClickListener() {
            fun_select_area()
            fun_select_date()
            searchExhibition()
        }


    }


    //artscapeのURLを生成する ※editTextの処理は「キーワード検索」の反映
    fun searchExhibition() {
        val apiClient = APIClient
        apiClient.searchExhibition(selected_area, selected_year, selected_month, selected_day, 2, 1, editText_keyword1.text.toString(), "", "on")
            .enqueue(object: Callback<String> {
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

        //開催中の美術展のタイトル、会期、美術館名を抽出する
        val dataList = ArrayList<RowModel>()
        for (i in exhiInfo.indices) {
            val data : RowModel = RowModel().also {
                it.title = exhiInfo[i].select("h3.headH3D").text()
                it.date = exhiInfo[i].select("p.exhiDate").text()
                it.nameOfMuseum = exhiInfo[i].select("a").text()
            }
            dataList.add(data)
        }

        //検索件数を抽出する
        val numberOfSearches = exhiInfo2.select("div.mainColHeading").text()


        //intentで情報を引き渡す
        val intent =Intent(this@SearchExhibitionActivity, ExhibitionListActivity::class.java).apply {
            putExtra(EXTRA_RESULTS,dataList)
            putExtra(EXTRA_RESULTS2,numberOfSearches)
        }

        startActivity(intent)
    }


}



