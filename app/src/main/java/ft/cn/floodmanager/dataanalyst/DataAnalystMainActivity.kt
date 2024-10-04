package ft.cn.floodmanager.dataanalyst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import ft.cn.floodmanager.R

class DataAnalystMainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize Views
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        topAppBar = findViewById(R.id.topAppBar)

        // Set the default fragment when the activity is created
        if (savedInstanceState == null) {
            loadFragment(DashboardFragment())
            topAppBar.title = getString(R.string.nav_dashboard)
            bottomNavigationView.selectedItemId = R.id.nav_dashboard
        }

        // Handle Bottom Navigation Item Selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    loadFragment(DashboardFragment())
                    topAppBar.title = getString(R.string.nav_dashboard)
                    true
                }
                R.id.reports -> {
                    loadFragment(ReportsFragment())
                    topAppBar.title = getString(R.string.nav_reports)
                    true
                }
                R.id.sensor_data -> {
                    loadFragment(SensorFragment())
                    topAppBar.title = getString(R.string.nav_sensor_data)
                    true
                }

                else -> false
            }
        }
    }

    // Function to load fragments dynamically
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
