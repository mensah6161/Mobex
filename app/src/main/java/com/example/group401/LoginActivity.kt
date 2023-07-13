package com.example.group401


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity()  {
        private lateinit var Login:Button
        private lateinit var username:EditText
        private lateinit var password:EditText
        private lateinit var Firebar:FirebaseAuth
        private lateinit var Reset_password:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login  )

        Login=findViewById(R.id.Login_System)
        username=findViewById(R.id.login_username)
        password=findViewById(R.id.login_password)
        Reset_password=findViewById(R.id.Reset)
        Firebar= FirebaseAuth.getInstance()


        Login.setOnClickListener {

            Firebar.signInWithEmailAndPassword(username.text.toString(),password.text.toString()).addOnCompleteListener{
                if(it.isSuccessful){
                    val intent= Intent(this, activity_start::class.java )
                    startActivity(intent)
                    finish()

                }else{
                    Toast.makeText(this, "You did put in the wrong Password or Username", Toast.LENGTH_SHORT).show()

                    return@addOnCompleteListener
                }


            }


        }
        Reset_password.setOnClickListener{
            val intent= Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
            finish()



        }









    }

}