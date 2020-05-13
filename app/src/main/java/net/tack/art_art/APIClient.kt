package net.tack.art_art

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
const val BASE_URL = "https://artscape.jp/"

//1:美術展（exhibition) の検索用

object APIClient {
    private fun restClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchExhibition(pref: String, year: String, month: String, day: String, period: Int, selorder: Int, search: String, btn_submit: String, f_submit: String) : Call<String> {
        val service = restClient().create(ArtScapeService::class.java)
        return service.urlList(pref, year, month, day, period, selorder, search, btn_submit, f_submit)
    }
}
