package net.tack.art_art.Interface

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//ArtScape内の「ミュージアム検索」のUrlを生成する
interface ArtScapeService3 {
    @GET("mdb/{id}")
    fun getMuseumDetail(@Path("id") id: String): Call<String>
}