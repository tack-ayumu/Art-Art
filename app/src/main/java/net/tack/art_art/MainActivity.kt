package net.tack.art_art

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //美術展の検索
        linearLayout_search_exhibition.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchExhibitionActivity::class.java)
            startActivity(intent)
        }

        //美術館の検索
        linearLayout_search_museum.setOnClickListener{
            val intent = Intent(this@MainActivity, SearchMuseumActivity::class.java)
            startActivity(intent)


        }

        //「お気に入り」ボタン編集中の旨、toast通知
        linearLayout_search_bookmark.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }

        //「note」ボタン編集中の旨、toast通知
        linearLayout_search_note.setOnClickListener {
            Toast.makeText(this, "編集中です",Toast.LENGTH_SHORT).show()
        }





    }
}
