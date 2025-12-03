package com.example.libraryview;

import android.content.Intent
import android.os.Bundle;
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity;

class Leaderboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        val LdBack = findViewById<Button>(R.id.LdBack)
        LdBack.setOnClickListener {
            val intent = Intent(this, IDActivity::class.java)
            startActivity(intent)
        }
    }
}