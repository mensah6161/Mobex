package com.example.group401


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity: AppCompatActivity()  {
        private lateinit var Login:Button
        private lateinit var username:EditText
        private lateinit var password:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login  )

        Login=findViewById(R.id.Login_System)
        username=findViewById(R.id.login_username)
        password=findViewById(R.id.login_password)


        Login.setOnClickListener {

            val intent= Intent(this, activity_start::class.java )
            startActivity(intent)

        }









    }

}