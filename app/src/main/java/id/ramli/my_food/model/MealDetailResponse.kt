package id.ramli.my_food.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ramliy10 on 12/12/20.
 */
class MealDetailResponse {
    @SerializedName("meals")
    val results: List<MealDetail>? = null
}