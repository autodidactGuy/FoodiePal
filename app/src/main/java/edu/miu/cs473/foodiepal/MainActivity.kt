package edu.miu.cs473.foodiepal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.gson.Gson
import edu.miu.cs473.foodiepal.databinding.ActivityMainBinding
import edu.miu.cs473.foodiepal.model.User


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myPrefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        if (myPrefs.contains("authUser")) {
            val authUserJson = myPrefs.getString("authUser", "")
            val authUser = Gson().fromJson(authUserJson, User::class.java)
            if (authUser != null) {
                startHomeActivity()
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val users = listOf(
            User("Hassan Raza","hassanraza","hassanraza632@gmail.com","123"),
            User("John Doe","johndoe","johndoe@gmail.com","abc"))
        binding.loginBtn.setOnClickListener {
            if (binding.usernameInput.text.isNotEmpty() && binding.passwordInput.text.isNotEmpty()) {
                val authenticatedUser = users.find { it.username == binding.usernameInput.text.toString() && it.password == binding.passwordInput.text.toString() }
                if (authenticatedUser != null) {
                    //Save Authenticated User
                    val editor = myPrefs.edit()
                    editor.putString("authUser", Gson().toJson(authenticatedUser))
                    editor.apply()
                    //startHomeActivity
                    startHomeActivity()
                } else {
                    Toast.makeText(this, "Username or Password is invalid!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter username and password!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}