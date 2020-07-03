package com.hfkorkmaz.kuizzer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
    }

    fun signUp(view: View) {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()
        val passwordAgain = passwordAgainInput.text.toString()
        val username = usernameInput.text.toString()
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()) {
            Toast.makeText(applicationContext, "Please fill the text fields.", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (password != passwordAgain) {
            Toast.makeText(applicationContext, "Password fields do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                //TODO: update profile with username

                val user = auth.currentUser;

                val profileUpdates = userProfileChangeRequest {
                    displayName = username
                }

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("profile", "User profile updated.")
                        }
                    }


                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            if (exception != null) {
                Toast.makeText(
                    applicationContext,
                    exception.localizedMessage.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}