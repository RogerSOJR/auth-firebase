package com.example.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        val intent = Intent(this, LoggedActivity::class.java)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(intent)
        }



        findViewById<Button>(R.id.btnCad).setOnClickListener {
            val edtEmail = findViewById<EditText>(R.id.edtEmailReg).text.toString()
            val edtSenha = findViewById<EditText>(R.id.edtSenhaReg).text.toString()

            auth.createUserWithEmailAndPassword(edtEmail, edtSenha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intentLog = Intent(this, MainActivity::class.java)
                        startActivity(intentLog)
                    } else {
                        Toast.makeText(
                            baseContext, "Não foi possível registrar!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }
        findViewById<Button>(R.id.btnLogas).setOnClickListener {
            val intentLog = Intent(this, MainActivity::class.java)
            startActivity(intentLog)
        }
    }
}