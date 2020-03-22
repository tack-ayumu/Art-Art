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

        textView_date.setOnClickListener {
            val dtp = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ view, y, m, d ->
                textView_date.text = "$y 年 ${m+1} 月 $d 日"}, year,month,day)
            dtp.show()
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

