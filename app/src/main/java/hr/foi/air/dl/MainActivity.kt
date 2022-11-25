package hr.foi.air.dl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.foi.air.dl.data.DataRepository
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
        loadDataToFragment()
    }

    private fun showMainFragment()
    {
        currentFragment = ListViewFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFragment.id, currentFragment!!)
            .commit()
    }

    private fun loadDataToFragment()
    {
        DataRepository().loadData(this, currentFragment!!)
    }
}