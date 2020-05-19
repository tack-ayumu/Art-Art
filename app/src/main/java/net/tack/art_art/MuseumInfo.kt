package net.tack.art_art

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_museum_info.*

class MuseumInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_info)

        val getDataOfMuseumName = intent.getStringExtra("detail1")
        val getDataOfAddressNumber = intent.getStringExtra("detail2")
        val getDataOfAddress = intent.getStringExtra("detail3")
        val getDataOfTelNumber = intent.getStringExtra("detail4")
        val getDataOfDataOfMuseumUrl = intent.getStringExtra("detail5")

        textViewNameOfMuseum.text =getDataOfMuseumName
        textViewAddressNumber.text=getDataOfAddressNumber
        textViewAddress.text=getDataOfAddress
        textViewTelNumber.text=getDataOfTelNumber




    }
}
