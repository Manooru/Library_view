package com.example.libraryview;

import android.content.Intent
import android.os.Bundle;
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Showcase : AppCompatActivity() {
    private fun loadOwnedGames(steamId: String) {
        var api="7E287B7DCB4DD30BF79629F7051AC613"
        var nazwa = intent.getStringExtra("nazwa")
        var iDusera: String? = intent.getStringExtra("iDusera",)
        RetrofitInstance.api.getOwnedGames(
            apiKey = api,
            steamId = iDusera,
            includeFreeGames = true
        ).enqueue(object : Callback<OwnedGamesResponse> {
            override fun onResponse(
                call: Call<OwnedGamesResponse>,
                response: Response<OwnedGamesResponse>
            ) {
                if (!response.isSuccessful) {
                    Error()
                    return
                }
                val games = response.body()?.response?.games ?: emptyList()
                findViewById<TextView>(R.id.ShGry).text = "Twoja ilość gier: $games"
            }
            override fun onFailure(call: Call<OwnedGamesResponse>, t: Throwable) {
                Error()
            }
        })
    }//a
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcase)
        var nazwa = intent.getStringExtra("nazwa")
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


    }

}