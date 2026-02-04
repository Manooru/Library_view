package com.example.libraryview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamApiService {

    @GET("IPlayerService/GetOwnedGames/v1/")
    fun getOwnedGames(
        @Query("key") apiKey: String,
        @Query("steamid") steamId: String,
        @Query("include_played_free_games") includeFreeGames: Boolean = true
    ): Call<OwnedGamesResponse>
}
