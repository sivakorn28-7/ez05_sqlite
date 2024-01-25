package com.example.ez05_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ez05_sqlite.databinding.ActivityLoginBinding
import com.example.loginsignupsql.DatabaseHelper

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logbutton.setOnClickListener {
            val logname = binding.logname.text.toString()
            val logpassword = binding.logpassword.text.toString()
            loginDatabase(logname, logpassword)
        }

        binding.signupRedirectText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        databaseHelper = DatabaseHelper(this)

    }

    private fun loginDatabase(username: String, password: String){
        val userExists = databaseHelper.readUser(username, password)
        if (userExists){
            Toast.makeText(this, "login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}