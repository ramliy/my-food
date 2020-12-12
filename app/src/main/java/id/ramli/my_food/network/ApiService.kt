package id.ramli.my_food.network

import id.ramli.my_food.model.MealDetailResponse
import id.ramli.my_food.model.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ramliy10 on 12/12/20.
 */
interface ApiService {
    @GET("api/json/v1/1/filter.php")
    fun meals(
        @Query("c") keyword: String
    ): Call<MealResponse>

    @GET("api/json/v1/1/lookup.php")
    fun detail(
        @Query("i") id: String
    ): Call<MealDetailResponse>
}