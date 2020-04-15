package net.tack.art_art

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtScapeService {
    @GET("exhibition/schedule/exhi_schedule_result.php")
    fun urlList(@Query("pref")pref: String, @Query("Year")Year:String,
                @Query("Month")Month:String, @Query("Day")Day:String,
                @Query("period")period:Int, @Query("selorder")selorder:Int,
                @Query("search")search:String, @Query("btn_submit")btn_submit:String,
                @Query("f_submit")f_submit:String): Call<String>
}