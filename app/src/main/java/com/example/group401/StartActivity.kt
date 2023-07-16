package com.example.group401



import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class FirstMainActivity : AppCompatActivity() {  // <!-- made by Berat Sahintürk-->
        private lateinit var Ard:ImageView
        private lateinit var layout:LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start  )

        var Login_Button: Button
        var Registration_Button:Button
        var Help_Button:Button
        var state:Boolean=false
        state=intent.getBooleanExtra("EXTRA_STATE",false)

        Login_Button=findViewById(R.id.Start_Login)
        Registration_Button=findViewById(R.id.Start_Registration)
        Help_Button=findViewById(R.id.Start_Help)
        Ard=findViewById(R.id.Ard)
        layout=findViewById(R.id.linearLayoutstart)
        if(savedInstanceState==null&& state==false) {
            Handler().postDelayed(Runnable {
                Ard.visibility = View.INVISIBLE
                layout.visibility = View.VISIBLE
            }, 4000)
        } else{
            Ard.visibility = View.INVISIBLE
            layout.visibility = View.VISIBLE


        }
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