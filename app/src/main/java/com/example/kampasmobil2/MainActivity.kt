package com.example.kampasmobil2

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kampasmobil2.databinding.ActivityMainBinding
import com.example.kampasmobil2.databinding.FragmentMapClienteBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var googleMap: GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerMain
        val navView: NavigationView = binding.navView
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as? NavHostFragment
        val navController = navHost?.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.blank1, R.id.blank2, R.id.blank3
            ), drawerLayout
        )
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
    }
    internal fun  update(){
        supportFragmentManager.beginTransaction()
    }
}