package net.tack.art_art

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

//2:美術館(museum)の検索用

object APIClient2 {
    private const val BASE_URL = "https://artscape.jp/"

    private fun restClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchMuseums(pref: String,selorder: Int, search: String, btn_submit: String, f_submit: String) : Call<String> {
        val service = restClient().create(ArtScapeService2::class.java)
        return service.urlList(pref,selorder, search, btn_submit, f_submit)
    }



}