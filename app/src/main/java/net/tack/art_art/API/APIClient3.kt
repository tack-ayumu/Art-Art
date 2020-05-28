package net.tack.art_art.API

import net.tack.art_art.Interface.ArtScapeService3
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

//ArtScape内の「ミュージアム検索」のUrlを生成する
object APIClient3 {
    private fun restClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://artscape.jp/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchMuseums(urlMuseum:String) : Call<String> {
        val service = restClient()
            .create(ArtScapeService3::class.java)
        return service.getMuseumDetail(urlMuseum)
    }


}

