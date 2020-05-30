package net.tack.art_art.Activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.tack.art_art.R

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        //「お気に入り」の検索
        linearLayout_search_bookmark.setOnClickListener {
            val intent = Intent(this@FavoriteActivity, FavoriteExhibitionActivity::class.java)
            startActivity(intent)
        }



    }
}
