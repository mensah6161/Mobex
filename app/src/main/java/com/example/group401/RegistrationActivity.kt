package com.example.group401


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class RegistrationActivity: AppCompatActivity()  {
    private  lateinit var Reg_next:Button
    private lateinit var password1:EditText
    private lateinit var password2:EditText
    private lateinit var username:EditText
    private lateinit var email:EditText
    private lateinit var Name:EditText
    private lateinit var Surename:EditText
    private lateinit var Reg_now:Button
    private lateinit var Serie:Button
    private lateinit var Nachrichten:Button
    private lateinit var Doku:Button
    private lateinit var Film:Button
    private lateinit var Show:Button
    private lateinit var Layout1: LinearLayout
    private lateinit var Layout2:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration  )
        var password_API:String
        var  Username_API:String
        var  Email_API:String
        var Name_API:String
        var Surename_API:String
        var good_password= Regex("^(?=.*[0-9])(?=.*[!@#\$%^&*])(?=\\S+\$).{8,}\$") //https://stackoverflow.com/questions/23214434/regular-expression-in-android-for-password-field
        var Genre1: Boolean=false
        var Genre2:Boolean=false
        var Genre3:Boolean=false
        var Genre4:Boolean=false
        var Genre5:Boolean=false


        Reg_next=findViewById(R.id.Registration_next)
        password1=findViewById(R.id.Reg_password1)
        password2=findViewById(R.id.Reg_password2)
        username=findViewById(R.id.Username)
        email=findViewById(R.id.Email)
        Name=findViewById(R.id.Last_name)
        Surename=findViewById(R.id.Surename)
        Reg_now=findViewById(R.id.Register)
        Serie=findViewById(R.id.serie)
        Nachrichten=findViewById(R.id.nachrichten)
        Doku=findViewById(R.id.dokumentation)
        Film=findViewById(R.id.film)
        Show=findViewById(R.id.show)
        Layout1=findViewById(R.id.Form)
        Layout2=findViewById(R.id.Genres)




        Reg_next.setOnClickListener {
            if(password1.text.toString()!=password2.text.toString()){
                Toast.makeText(this, "You passwords are different", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else if(false==good_password.matches(password1.text.toString())){
                Toast.makeText(this, "Please choose a password, which at least has 8 Digit," +
                        "one special character, and at least 1 number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener



            }
            else if( password1.text.toString()==""||password2.text.toString()==""||
                email.text.toString()==""||username.text.toString()==""||Name.text.toString()==""||
                    Surename.text.toString()==""){
                Toast.makeText(this, "Please fill out all the given fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener


            }
            password_API =password1.text.toString()    //normally this should have been send to the Database
            Username_API=username.text.toString()
            Email_API=email.text.toString()
            Name_API=Name.text.toString()
            Surename_API=Surename.text.toString()

            Layout1.visibility= View.INVISIBLE
            Layout2.visibility=View.VISIBLE



        }
        Reg_now.setOnClickListener {


                val intent= Intent(this, activity_start::class.java )
                startActivity(intent)



        }
        Serie.setOnClickListener {
            if (Genre1==false){
                Genre1=true
                Serie.setBackgroundColor(Color.DKGRAY)

            }else {
                Genre1=false
                Serie.setBackgroundColor(Color.BLACK)

            }

        }
        Nachrichten.setOnClickListener {
            if (Genre2==false){
                Genre2=true
                Nachrichten.setBackgroundColor(Color.DKGRAY)

            }else {
                Genre2=false
                Nachrichten.setBackgroundColor(Color.BLACK)

            }

        }
        Doku.setOnClickListener {
            if (Genre3==false){
                Genre3=true
                Doku.setBackgroundColor(Color.DKGRAY)

            }else {
                Genre3=false
                Doku.setBackgroundColor(Color.BLACK)

            }

        }
        Film.setOnClickListener {
            if (Genre4==false){
                Genre4=true
                Film.setBackgroundColor(Color.DKGRAY)

            }else {
                Genre4=false
                Film.setBackgroundColor(Color.BLACK)

            }

        }
        Show.setOnClickListener {
            if (Genre5==false){
                Genre5=true
                Show.setBackgroundColor(Color.DKGRAY)

            }else {
                Genre5=false
                Show.setBackgroundColor(Color.BLACK)

            }

        }









    }

}