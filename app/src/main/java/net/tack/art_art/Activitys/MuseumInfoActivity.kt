package net.tack.art_art.Activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import kotlinx.android.synthetic.main.activity_museum_info.*
import net.tack.art_art.R

class MuseumInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_info)

        val getDataOfMuseumName = intent.getStringExtra("detail1")
        val getDataOfAddressNumber = intent.getStringExtra("detail2")
        val getDataOfAddress = intent.getStringExtra("detail3")
        val getDataOfTelNumber = intent.getStringExtra("detail4")
        val getDataOfMuseumUrl = intent.getStringExtra("detail5")
        val getDataOfImage = intent.getStringExtra("detail6")

        textViewNameOfMuseum.text =getDataOfMuseumName
        textViewAddressNumber.text=getDataOfAddressNumber
        textViewAddress.text=getDataOfAddress
        textViewTelNumber.text=getDataOfTelNumber

        //artscapeに画像がなかった場合は「NO IMAGE」の画像を設定する
        when (getDataOfImage) {
            "https://artscape.jp" -> imageViewMuseum.setImageResource(R.drawable.no_image)
            else -> imageViewMuseum.load(getDataOfImage)
        }



    }
}
