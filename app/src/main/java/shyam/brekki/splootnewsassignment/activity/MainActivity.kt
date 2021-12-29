package shyam.brekki.splootnewsassignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import shyam.brekki.splootnewsassignment.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,
            R.id.nav_host_fragment_container)
            .navigateUp() || super.onSupportNavigateUp()
    }
}