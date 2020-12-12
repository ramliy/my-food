package id.ramli.my_food.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.ramli.my_food.R
import id.ramli.my_food.RecyclerViewClickListener
import id.ramli.my_food.model.Meal
import id.ramli.my_food.utils.Constans
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewClickListener, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: MainViewModel
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapterMeal: MealAdapter
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        setupRecyclerview()
        adapterMeal.listener = this
        refresh.isEnabled = false
        getData()
        viewModel.getStatus().observe(this, Observer { t ->
            if (t ?: true) {
                refresh.isRefreshing = true
            } else {
                list.visibility = View.VISIBLE
                refresh.isRefreshing = false
            }
        })
        viewModel.setMeealsData().observe(this, Observer { t ->
            t?.results?.let {
                Log.d(TAG, "onCreate: ${t.results.size}")
                if (t.results.isEmpty()) {
                    Toast.makeText(this, "List Popular Empty", Toast.LENGTH_SHORT).show()

                } else
                    adapterMeal.addList(it as ArrayList<Meal>)
                adapterMeal.notifyDataSetChanged()
            }
        })

    }

    private fun setupRecyclerview() {
        list.setHasFixedSize(true)
        list.layoutManager = layoutManager
        adapterMeal = MealAdapter()
        list.adapter = adapterMeal
    }

    private fun getData() {
        viewModel.getMealsData()
    }

    override fun onItemClick(view: View?, data: Any?) {
        val meal:Meal = data as Meal
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Constans.MEALS_ID, data.idMeal)
        startActivity(intent)
    }

    override fun onRefresh() {

    }
}