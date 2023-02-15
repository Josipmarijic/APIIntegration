package com.example.apiintegration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apiintegration.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signUp.setOnClickListener{
            val email = binding.eMail.text.toString()
            val password = binding.password.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
        }
    }
}