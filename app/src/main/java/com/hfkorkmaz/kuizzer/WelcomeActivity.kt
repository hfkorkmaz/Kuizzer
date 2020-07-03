package com.hfkorkmaz.kuizzer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun goToSignInActivity(view: View) {
        val intent = Intent(applicationContext, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToSignUpActivity(view: View) {
        val intent = Intent(applicationContext, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}