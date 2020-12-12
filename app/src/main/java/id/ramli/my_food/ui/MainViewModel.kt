package id.ramli.my_food.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ramli.my_food.model.MealDetailResponse
import id.ramli.my_food.model.MealResponse
import id.ramli.my_food.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ramliy10 on 12/12/20.
 */
class MainViewModel : ViewModel(){
    private var status = MutableLiveData<Boolean>()
    private var mealsData = MutableLiveData<MealResponse>()
    private var mealsDetailData = MutableLiveData<MealDetailResponse>()

    fun getMealsData() {
        status.value = true

        NetworkConfig().api().meals("Seafood").enqueue(object :
            Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                status.value = true
                mealsData.value = null
            }

            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                status.value = false

                if(response.isSuccessful){
                    mealsData.value = response.body()
                }
                else{
                    status.value = true
                }

            }
        })

    }

    fun getMealsDetailData(id:String) {
        status.value = true

        NetworkConfig().api().detail(id).enqueue(object :
            Callback<MealDetailResponse> {
            override fun onFailure(call: Call<MealDetailResponse>, t: Throwable) {
                status.value = true
                mealsDetailData.value = null
            }

            override fun onResponse(call: Call<MealDetailResponse>, response: Response<MealDetailResponse>) {
                status.value = false

                if(response.isSuccessful){
                    mealsDetailData.value = response.body()
                }
                else{
                    status.value = true
                }

            }
        })

    }

    fun setMeealsData() : MutableLiveData<MealResponse> {
        return mealsData
    }

    fun setMeealsDetailData() : MutableLiveData<MealDetailResponse> {
        return mealsDetailData
    }

    fun getStatus():MutableLiveData<Boolean>{
        status.value = true
        return status
    }
}