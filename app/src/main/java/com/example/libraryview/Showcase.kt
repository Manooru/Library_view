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
        var nazwa=intent.getStringExtra("nazwa")
        var iDusera=intent.getIntExtra("iDusera", -1)
        val Shwrocenie=findViewById<Button>(R.id.ShBack)
        val Shleaderboard=findViewById<Button>(R.id.ShLeaderboard)
        Shleaderboard.setOnClickListener{
            val intent = Intent(this, Leaderboard::class.java)
            startActivity(intent)
        }
        Shwrocenie.setOnClickListener{
            val intent = Intent(this, IDActivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.ShNazwa).text = nazwa
        findViewById<TextView>(R.id.ShNazwa).text = "Twoja ilość gier: $iDusera"
        nickname = intent.getStringExtra("nickname") ?: ""
        userId = intent.getIntExtra("user_id", -1)
        SteamService.api.resolveVanityURL(
            BuildConfig.STEAM_API_KEY,
            nickname
        ).enqueue(object : Callback<VanityResponse> {
            override fun onResponse(
                call: Call<VanityResponse>,
                response: Response<VanityResponse>
            ) {
                val steamId64 = response.body()?.response?.steamid
                if (steamId64 == null) {
                    startActivity(Intent(this@ShowcaseActivity, ErrorActivity::class.java))
                    return
                }
                loadGameCount(steamId64)
            }
            override fun onFailure(call: Call<VanityResponse>, t: Throwable) {
                startActivity(Intent(this@ShowcaseActivity, ErrorActivity::class.java))
            }
        })
        }
        private fun loadGameCount(steamId: String) {
            SteamService.api.getOwnedGames(
                BuildConfig.STEAM_API_KEY,
                steamId
            ).enqueue(object : Callback<OwnedGamesResponse> {
                override fun onResponse(
                    call: Call<OwnedGamesResponse>,
                    response: Response<OwnedGamesResponse>
                ) {
                    val gameCount = response.body()?.response?.game_count ?: 0

                    findViewById<TextView>(R.id.txtGameCount).text =
                        "Gier w bibliotece: $gameCount"
                }

                override fun onFailure(call: Call<OwnedGamesResponse>, t: Throwable) {
                    startActivity(Intent(this@ShowcaseActivity, ErrorActivity::class.java))
                }
            })
        }
        }
    }