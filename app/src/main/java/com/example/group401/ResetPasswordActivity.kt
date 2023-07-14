package com.example.group401

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

private lateinit var Reset:Button
    private lateinit var Back:Button
    private lateinit var Email: EditText
private lateinit var Firebase:FirebaseAuth


class ResetPasswordActivity: AppCompatActivity() { // <!-- made by Berat Sahintürk-->
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset)
        var input:String

       Reset=findViewById(R.id.Reset_password2)
        Back=findViewById(R.id.go_menu)
       Email=findViewById(R.id.send_password)
      Firebase= FirebaseAuth.getInstance()


        Reset.setOnClickListener {
            input= Email.text.toString()

          Firebase.sendPasswordResetEmail(input).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "The Reset was Successfull, go to you Email Acc", Toast.LENGTH_SHORT).show()
                    val intent= Intent (this, FirstMainActivity::class.java)
                    startActivity(intent)


                }


                else{ Toast.makeText(this, "Please try again something went wrong ", Toast.LENGTH_SHORT).show()


                }

            }
        }
       Back.setOnClickListener {
           val intent= Intent (this, FirstMainActivity::class.java)
           startActivity(intent)

        }




    }
    }