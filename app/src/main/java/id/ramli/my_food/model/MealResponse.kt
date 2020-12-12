package id.ramli.my_food.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ramliy10 on 12/12/20.
 */
data class MealResponse (
    @SerializedName("meals")
    val results: List<Meal>? = null
)