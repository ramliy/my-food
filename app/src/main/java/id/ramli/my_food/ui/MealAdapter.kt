package id.ramli.my_food.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.ramli.my_food.R
import id.ramli.my_food.RecyclerViewClickListener
import id.ramli.my_food.model.Meal
import kotlinx.android.synthetic.main.item_meal.view.*

/**
 * Created by ramliy10 on 12/12/20.
 */
class MealAdapter : RecyclerView.Adapter<MealAdapter.MyHolder>() {
    private var data = ArrayList<Meal>()

    var listener: RecyclerViewClickListener? = null

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(get: Meal) {


            itemView.tv_title.text = get.strMeal
            if (get.strMealThumb != null) {
                Picasso.get()
                    .load(get.strMealThumb)
                    .into(itemView.iv_photo)
            }

        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = MyHolder(
        LayoutInflater.from(p0.context).inflate(R.layout.item_meal, p0, false)
    )

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        p0.bindView(data.get(p1))
        p0.itemView.setOnClickListener {
            listener?.onItemClick(it, data[p1])
        }

    }

    fun addList(items: ArrayList<Meal>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }
}
