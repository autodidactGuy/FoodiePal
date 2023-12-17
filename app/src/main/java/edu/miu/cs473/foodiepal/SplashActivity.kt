package edu.miu.cs473.foodiepal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import edu.miu.cs473.foodiepal.databinding.ActivitySplashBinding

class SplashActivity : ComponentActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val myPrefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
            if (myPrefs.contains("authUser")) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        },5200)
    }

}