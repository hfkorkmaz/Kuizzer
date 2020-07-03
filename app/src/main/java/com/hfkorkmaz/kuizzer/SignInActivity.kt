package com.hfkorkmaz.kuizzer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()

    }


    fun signIn(view: View) {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("signin", "signInWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(
                        applicationContext,
                        "Welcome: ${user?.displayName.toString()}",
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(
                    applicationContext,
                    exception.localizedMessage.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
    }


}