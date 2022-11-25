package hr.foi.air.dl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.foi.air.dl.databinding.ActivityMainBinding
import hr.foi.air.dl.fragments.ListViewFragment

class MainActivity : AppCompatActivity() {
    private var currentFragment : ListViewFragment? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showMainFragment()
        //load and display data
        //displayData(binding)
    }

    private fun showMainFragment()
    {
        currentFragment = ListViewFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragment.id, currentFragment!!)
            .commit()
    }

    /*
    fun displayData(binding: ActivityMainBinding)
    {
        val context = this
        val repository = DataRepository()
        val discountsNames : ArrayList<String> = ArrayList()

        repository.loadData(this, object : LoadDataListener {
            override fun onDataLoaded(stores: List<Store>, discounts: List<Discount>) {

                val parentList : ArrayList<StoreParent> = ArrayList()
                for (s in stores)
                    parentList.add(StoreParent(s, discounts))

                //prikaz podataka
                binding.mainRecycler.adapter = StoreRecyclerAdapter(context, parentList)
                binding.mainRecycler.layoutManager = LinearLayoutManager(context)

                //hiding empty message
                if (!stores.isEmpty())
                    binding.emptyMessage.isVisible = false
            }
        })
    }
    */
}