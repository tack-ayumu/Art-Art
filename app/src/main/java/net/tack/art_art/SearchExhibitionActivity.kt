package net.tack.art_art

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_exhibition.*

class SearchExhibitionActivity : AppCompatActivity() {

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

        fun selected_year(){

            val selected_year = textView_year.text.toString().substring(0,4)

        }



        go_search_exhibition.setOnClickListener() {
            var selected_area = spinner_area.selectedItem.toString()
            val idx = spinner_area.selectedItemPosition
            when(idx){
              3 -> selected_area = spinner_area.selectedItem.toString().substring(1,3)
            }


            val selected_year = textView_year.text.toString().substring(0,4)
            val selected_month = textView_month.text.toString().substring(0,2)
            val selected_day = textView_day.text.toString().substring(0,2)
            val urlArtscape1 = "https://artscape.jp/exhibition/schedule/exhi_schedule_result.php?pref="
            val urlArtscape2 = "&Year="
            val urlArtscape3 = "&Month="
            val urlArtscape4 = "&Day="
            val urlArtscape5 = "&period=2&selorder=1&search=&btn_submit=&f_submit=on"

            textView2.text = urlArtscape1 + selected_area + urlArtscape2 + selected_year + urlArtscape3 + selected_month+urlArtscape4 + selected_day + urlArtscape5
        }




        //「お気に入り」ボタン編集中の旨、toast通知
        linearLayout_search_bookmark2.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }

        //「履歴検索」ボタン編集中の旨、toast通知
        linearLayout_search_history.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }







    }


    }



