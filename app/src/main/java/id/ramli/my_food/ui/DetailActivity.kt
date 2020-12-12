package id.ramli.my_food.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import id.ramli.my_food.R
import id.ramli.my_food.utils.Constans
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.refresh

class DetailActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = getString(R.string.detail)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        refresh.isEnabled = false

        var mealsId = intent.getStringExtra(Constans.MEALS_ID)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getData(mealsId!!)

        viewModel.getStatus().observe(this, Observer { t ->
            refresh.isRefreshing = t ?: true
        })
        viewModel.setMeealsDetailData().observe(this, Observer { t ->
            t?.results?.let {
                if (t.results.isEmpty()) {
                    Toast.makeText(this, "List Popular Empty", Toast.LENGTH_SHORT).show()

                } else {

                    if (t?.results[0].strMealThumb!=null){
                        Picasso.get()
                            .load(t?.results[0].strMealThumb)
                            .into(iv_banner)
                    }

                    tv_title.text = t?.results[0].strMeal
                    tv_instruction.text = t?.results[0].strInstructions
                }
            }
        })
    }

    private fun getData(id:String) {
        viewModel.getMealsDetailData(id)
    }

    override fun onRefresh() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}