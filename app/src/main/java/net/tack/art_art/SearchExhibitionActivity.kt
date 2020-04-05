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
        val day = calender.get(java.util.Calendar.DAY_OF_MONTH)
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


        go_search_exhibition.setOnClickListener {
            textView2.text = " https://artscape.jp/exhibition/schedule/exhi_schedule_result.php=?" + spinner_area.selectedItem + "&Year="+
                    textView_year.text.subSequence(0, 4)+"&Month="+textView_month.text.subSequence(0,2)+"&Day="+
                    textView_day.text.subSequence(0,2)+"&period=2&selorder=1&search=&btn_submit=&f_submit=on"
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



