package com.example.apiintegration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apiintegration.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //Auth för att firebase
    private lateinit var auth: FirebaseAuth
    //Viewbinding så jag inte behöver använda FindViewById
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //instansierar firebase
        auth = FirebaseAuth.getInstance()
        //om man klickar på knappen så händer följande kod
        binding.logInBtn.setOnClickListener{
            //hämtar mail och löser för edittext layoutsen
            val email = binding.eMail.text.toString()
            val password = binding.password.text.toString()
            //firebase kollar om det stämmer mot användarna som är sparade
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    //om det lyckas skickas man till activityn där man söker
                    if (it.isSuccessful){
                        val intent = Intent(this@MainActivity,SearchActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext,"Wrong username or password",Toast.LENGTH_SHORT).show()
                    }
                }
        }
        //Skickar till signup functionen som också görs med firebase
        binding.signUpBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


}