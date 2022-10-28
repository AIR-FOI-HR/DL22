package hr.foi.air.dl

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import hr.foi.air.database.data.DataRepository
import hr.foi.air.database.data.MockData
import hr.foi.air.dl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //var btnShowData = findViewById<Button>(R.id.btn_show_data)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //defining anonymous object of anonymous type to handle the event
        //this is the full syntax
        //binding.btnShowData.setOnClickListener(
        //    object : View.OnClickListener
        //    {
        //        override fun onClick(p0: View?) {
        //            //handle the event
        //        }
        //    }
        //)

        //using the lambda with the same functionality
        binding.btnShowData.setOnClickListener {
            //handle the event
            displayData(binding)
        }
    }

    fun displayData(binding: ActivityMainBinding)
    {
        //Unos podataka u bazu, ako je potrebno
        MockData.mockData(this)

        var discounts : List<String> = DataRepository(this).getDiscountNames()

        //Prikaz podataka na zaslovnu
        binding.lstDiscounts.adapter =
            ArrayAdapter<String>(this, R.layout.simple_list_item_1, discounts)

        //Dodatne mogućnosti prikaza
        for (disc in discounts) {
            //privremeni ispis na zaslon
            //Toast.makeText(this, disc, Toast.LENGTH_LONG).show()

            //privremeni ispis na zaslon u obliku snackbara
            Snackbar.make(binding.root, disc, Snackbar.LENGTH_SHORT).show()

            //ispis u log
            Log.d("AIR", disc)
        }

        //hiding empty message
        if (!discounts.isEmpty())
            binding.emptyMessage.isVisible = false
    }
}