package hr.foi.air.dl

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import hr.foi.air.core.entities.Discount
import hr.foi.air.core.entities.Store
import hr.foi.air.dl.data.DataRepository
import hr.foi.air.dl.data.LoadDataListener
import hr.foi.air.dl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //load and display data
        displayData(binding)
    }

    fun displayData(binding: ActivityMainBinding)
    {
        val context = this
        val repository = DataRepository()
        val discountsNames : ArrayList<String> = ArrayList()

        repository.loadData(this, object : LoadDataListener {
            override fun onDataLoaded(stores: List<Store>, discounts: List<Discount>) {
                for (d in discounts) {
                    discountsNames.add(d.name)
                }

                //Prikaz podataka na zaslovnu
                //binding.lstDiscounts.adapter =
                //    ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, discountsNames)

                //hiding empty message
                if (!discounts.isEmpty())
                    binding.emptyMessage.isVisible = false
            }
        })
    }
}