package com.example.libraryview;

import android.content.Intent
import android.os.Bundle;
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;

class Showcase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcase)
        var nazwa = intent.getStringExtra("nazwa")
        var iDusera = intent.getIntExtra("iDusera", -1)
        val Shwrocenie = findViewById<Button>(R.id.ShBack)
        val Shleaderboard = findViewById<Button>(R.id.ShLeaderboard)
        Shleaderboard.setOnClickListener {
            val intent = Intent(this, Leaderboard::class.java)
            startActivity(intent)
        }
        Shwrocenie.setOnClickListener {
            val intent = Intent(this, IDActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.ShNazwa).text = nazwa
        findViewById<TextView>(R.id.ShGry).text = "Twoja ilość gier: $nazwa"

    }
    private fun loadOwnedGames(steamId: String) {

        RetrofitInstance.api.getOwnedGames(
            BuildConfig.STEAM_API_KEY,
            steamId
        ).enqueue(object : Callback<OwnedGamesResponse> {

            override fun onResponse(
                call: Call<OwnedGamesResponse>,
                response: Response<OwnedGamesResponse>
            ) {
                if (!response.isSuccessful) {
                    showError()
                    return
                }

                val games = response.body()?.response?.games ?: emptyList()

                showGames(games)
            }

            override fun onFailure(call: Call<OwnedGamesResponse>, t: Throwable) {
                showError()
            }
        })
    }
}