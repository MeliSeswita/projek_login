package com.example.projek_login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projek_login.databinding.ActivityMainBinding
import com.example.projek_login.util.pref

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.navigation_dashboard) {
                val s = pref(this)
                if (s.getISlogin()) {
                    Log.d("TAG", "SUDAH LOGIN")
                    // Navigasi ke halaman navigation_akun
                    navController.navigate(R.id.navigation_dashboard)
                    true
                } else {
                    startActivity(Intent(this, Login::class.java))
                    Log.d("TAG", "Belum Login, pindah ke halaman login")
                    false
                }
            } else {
                Log.d("TAG", "onCreate yg Lain" + item.itemId)
                navController.navigate(item.itemId)
                true
            }
        }

        val button = findViewById<Button>(R.id.btnakun)
        button.setOnClickListener {
            // Mendapatkan instance dari SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

            // Mengecek apakah pengguna sudah login atau belum
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

            // Jika pengguna sudah login
            if (isLoggedIn) {
                // Membuat intent untuk ActivityKuotaCall
                val intent = Intent(this@MainActivity, activity_profil::class.java)

                // Menampilkan pesan yang membosankan
                Toast.makeText(this@MainActivity, "Mengecek status login...", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity, "Pengguna sudah login!", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity, "Menavigasikan ke halaman ActivityKuotaCall...", Toast.LENGTH_SHORT).show()

                // Menjalankan intent dan membuka ActivityKuotaCall
                startActivity(intent)

            } else { // Jika pengguna belum login
                // Membuat intent untuk ActivityLogin
                val intent = Intent(this@MainActivity, Login::class.java)

                // Menampilkan pesan yang membosankan
                Toast.makeText(this@MainActivity, "Mengecek status login...", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity, "Pengguna belum login!", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity, "Menavigasikan ke halaman ActivityLogin...", Toast.LENGTH_SHORT).show()

                // Menjalankan intent dan membuka ActivityLogin
                startActivity(intent)
            }
        }

        val btn=findViewById<Button>(R.id.btnlokasi)
        btn.setOnClickListener {
            val intent = Intent(this, activity_profil::class.java)
            startActivity(intent)
        }


    }
}