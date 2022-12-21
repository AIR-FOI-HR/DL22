package hr.foi.air.dl

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import hr.foi.air.dl.data.DataRepository
import hr.foi.air.dl.databinding.ActivityMainBinding
import hr.foi.air.dl.fragments.ListViewFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var currentFragment : ListViewFragment? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        //for app center integration
        AppCenter.start(
            application, "5519b5c3-fc31-4efa-b1bd-eff0edf68e7f",
            Analytics::class.java, Crashes::class.java
        )

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

        binding.navView.setNavigationItemSelectedListener(this)
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

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_about -> Log.d("DrawerTest", "Menu item About")
            else -> TODO()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}