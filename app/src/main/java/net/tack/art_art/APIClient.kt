package net.tack.art_art

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object APIClient {
    private const val BASE_URL = "https://artscape.jp/"

    private fun restClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchMuseums(pref: String, year: String, month: String, day: String, period: Int, selorder: Int, search: String, btn_submit: String, f_submit: String) : Call<String> {
        val service = restClient().create(ArtScapeService::class.java)
        return service.urlList(pref, year, month, day, period, selorder, search, btn_submit, f_submit)
    }
}
