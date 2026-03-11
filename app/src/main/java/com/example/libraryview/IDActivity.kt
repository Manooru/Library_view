package com.example.libraryview;

import android.content.Intent
import android.os.Bundle;
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity;

class IDActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id)
        val nazwa=findViewById<EditText>(R.id.Nazwausera)
        val iDusera=findViewById<EditText>(R.id.IDusera)
        val sprawdzenie=findViewById<Button>(R.id.Sprawdzenie)
        val leaderboard=findViewById<Button>(R.id.Leaderboard)
        leaderboard.setOnClickListener {
            val intent = Intent(this, Leaderboard::class.java)
            startActivity(intent)
        }
        sprawdzenie.setOnClickListener {
            val nameText=nazwa.text.toString().trim()
            val idText=iDusera.text.toString().trim()
            if (nameText.isEmpty() || idText.isEmpty()) {
                startActivity(Intent(this, Error::class.java))
                return@setOnClickListener
            }
            val idValue = idText
            if (idValue == null) {
                startActivity(Intent(this, Error::class.java))
                return@setOnClickListener
            }
            val intent=Intent(this, Showcase::class.java)
            intent.putExtra("nazwa", nameText)
            intent.putExtra("iDusera", idValue)
            startActivity(intent)
        }
    }
}