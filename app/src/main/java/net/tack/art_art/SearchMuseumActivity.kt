package net.tack.art_art

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_museum.*

class SearchMuseumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_museum)

        //地域検索(現在地・全国・10エリア+47都道府県) リスト表示(スピナー）
        arrayAdapter_area = ArrayAdapter.createFromResource(
            this, R.array.area, android.R.layout.simple_spinner_item
        )
        spinner_area.adapter = arrayAdapter_area

        //選択したエリアの情報を取得
        fun fun_select_area (){
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

            //五十音順のスピナーを設定する
            val arrayAdapter_gojyuon = ArrayAdapter.createFromResource(
                this, R.array.gojyu_on, android.R.layout.simple_spinner_item
            )
            spinner_gojyu_on.adapter = arrayAdapter_gojyuon



        }



    }
}
