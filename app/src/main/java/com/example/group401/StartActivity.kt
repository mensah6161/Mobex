package com.example.group401



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FirstMainActivity : AppCompatActivity() {  // <!-- made by Berat Sahintürk-->

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start  )

        var Login_Button: Button
        var Registration_Button:Button
        var Help_Button:Button

        Login_Button=findViewById(R.id.Start_Login)
        Registration_Button=findViewById(R.id.Start_Registration)
        Help_Button=findViewById(R.id.Start_Help)

        Login_Button.setOnClickListener {
            val intent= Intent(this, LoginActivity::class.java )

            startActivity(intent)
        }
        Registration_Button.setOnClickListener {
            val intent= Intent(this, RegistrationActivity::class.java )
            startActivity(intent)
        }
        Help_Button.setOnClickListener {
            val intent= Intent(this, HelpActivity::class.java )
            startActivity(intent)
        }






    }
}