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

lateinit var dataList:ArrayList<RowModel>

class SearchExhibitionActivity : AppCompatActivity() {
    lateinit var selected_area:String
    lateinit var selected_year:String
    lateinit var selected_month:String
    lateinit var selected_day:String
    var titleData = ""
    var dateData = ""
    var nameOfMuseumData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_exhibition)

        //地域検索(現在地・全国・10エリア+47都道府県) リスト表示
        val arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.area,
            android.R.layout.simple_spinner_item
        )
        spinner_area.adapter = arrayAdapter
        //選択したエリアの情報を取得
        selected_area = spinner_area.selectedItem.toString()

       //会期検索（日付検索）date-picker
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

        //「美術展を検索するボタン」を押す
        linearLayout_go_search_exhibition.setOnClickListener() {


            //【東北】【北関東】などのエリアについた括弧を外すためインデックスを使用してsubstringの処理をする
            val idx = spinner_area.selectedItemPosition
            when(idx){
                0,1 -> selected_area = ""
            }
            when(selected_area.startsWith("【")){
                true -> selected_area = spinner_area.selectedItem.toString().substring(1,selected_area.length-1)
            }


            //urlを生成するため、選択した年月日をテキスト化
            selected_year = textView_year.text.toString()
            selected_month = textView_month.text.toString()
            selected_day = textView_day.text.toString()

//            //固定のurlの部分
//            val urlArtscape1 = "https://artscape.jp/exhibition/schedule/exhi_schedule_result.php?pref="
//            val urlArtscape2 = "&Year="
//            val urlArtscape3 = "&Month="
//            val urlArtscape4 = "&Day="
//            val urlArtscape5 = "&period=2&selorder=1&search=&btn_submit=&f_submit=on"
//
//            //urlを生成してみる
//            textView2.text = urlArtscape1 + selected_area + urlArtscape2 + selected_year + urlArtscape3 + selected_month+urlArtscape4 + selected_day + urlArtscape5

            searchMuseums()
        }

        fun


        //「お気に入り」ボタン編集中の旨、toast通知
        linearLayout_search_bookmark2.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }

        //「履歴検索」ボタン編集中の旨、toast通知
        linearLayout_search_history.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }

    }

    fun searchMuseums() {
        val apiClient = APIClient
        apiClient.searchMuseums(selected_area, selected_year, selected_month, selected_day, 2, 1, "", "", "on")
            .enqueue(object: Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("FAILURE", t.message)
                }

                //データを取得し、intentで次画面に検索結果を表示させる
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

                dataList = ArrayList<RowModel>()
                for (i in exhiInfo.indices) {
                    val data : RowModel = RowModel().also {
                        it.title = exhiInfo[i].select("h3.headH3D").text()
                        it.date = exhiInfo[i].select("p.exhiDate").text()
                        it.nameOfMuseum = exhiInfo[i].select("a").text()
                    }
                    dataList.add(data)
                }
              val intent =Intent(this@SearchExhibitionActivity, ExhibitionListActivity::class.java).apply {
                  putExtra(EXTRA_RESULTS,dataList)
              }
            startActivity(intent)
            }
        }



