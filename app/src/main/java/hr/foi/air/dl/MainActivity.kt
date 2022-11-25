package hr.foi.air.dl

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
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

        initializeLayout()
        showMainFragment()
        loadDataToFragment()
    }

    private fun initializeLayout()
    {
        setSupportActionBar(binding.layoutMain.toolbar)
        var drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.layoutMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun showMainFragment()
    {
        currentFragment = ListViewFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.layoutMain.contentMain.mainFragment.id, currentFragment!!)
            .commit()
    }

    private fun loadDataToFragment()
    {
        DataRepository().loadData(this, currentFragment!!)
    }
}