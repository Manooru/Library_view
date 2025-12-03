package com.example.libraryview;

import android.content.Intent
import android.os.Bundle;
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity;

class Error : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        val Errorbutton = findViewById<Button>(R.id.Errorreturn)
        Errorbutton.setOnClickListener {
            val intent = Intent(this, IDActivity::class.java)
            startActivity(intent)
        }
    }
}