package net.tack.art_art

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//2:美術展（museum)検索用
interface ArtScapeService2 {
    @GET("mdb/mdb_result.php")
    fun urlList(@Query("pref")pref: String,@Query("selorder")selorder:Int,
                @Query("search")search:String, @Query("btn_submit")btn_submit:String,
                @Query("f_submit")f_submit:String): Call<String>
}