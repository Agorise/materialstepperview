package cy.agorise.stepperview.demo

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem

import cy.agorise.stepperview.demo.fragment.VerticalStepperAdapterDemoFragment
import cy.agorise.stepperview.demo.fragment.VerticalStepperDemoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView

    private val mVerticalStepperDemoFragment = VerticalStepperDemoFragment()
    private val mVerticalStepperAdapterDemoFragment = VerticalStepperAdapterDemoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDrawerLayout = findViewById(R.id.drawer_layout)

        mNavigationView = findViewById(R.id.navigation_view)
        mNavigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected)

        if (savedInstanceState == null) {
            replaceFragment(mVerticalStepperDemoFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
                mDrawerLayout.closeDrawer(mNavigationView)
            } else {
                mDrawerLayout.openDrawer(mNavigationView)
            }
            true
        }
        else -> false
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        mDrawerLayout.closeDrawer(mNavigationView)
        when (item.itemId) {
            R.id.item_vertical_stepper -> {
                replaceFragment(mVerticalStepperDemoFragment)
                return true
            }
            R.id.item_vertical_stepper_adapter -> {
                replaceFragment(mVerticalStepperAdapterDemoFragment)
                return true
            }
            else -> return false
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
