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

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        val intent = Intent(this, LoggedActivity::class.java)

        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnLog).setOnClickListener{
            val edtEmail = findViewById<EditText>(R.id.edtEmail).text.toString()
            val edtSenha = findViewById<EditText>(R.id.edtSenha).text.toString()

            auth.signInWithEmailAndPassword(edtEmail, edtSenha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Não foi possível logar!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        findViewById<Button>(R.id.btnRegis).setOnClickListener{
            val intentCad = Intent(this, RegisterActivity::class.java)
            startActivity(intentCad)
        }

    }
}