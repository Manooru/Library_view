package com.example.libraryview

class Retrofit {
    import retrofit2.Call
    import retrofit2.http.GET
    import retrofit2.http.Query

    interface SteamAPI {
        @GET("ISteamUser/ResolveVanityURL/v1/")
        fun resolveVanityURL(
            @Query("key") apiKey: String,
            @Query("vanityurl") vanityUrl: String
        ): Call<VanityResponse>
        @GET("IPlayerService/GetOwnedGames/v1/")
        fun getOwnedGames(
            @Query("key") apiKey: String,
            @Query("steamid") steamId: String,
            @Query("include_played_free_games") includeFree: Boolean = true
        ): Call<OwnedGamesResponse>
    }
    data class VanityResponse(
        val response: VanityResponseData
    )
    data class VanityResponseData(
        val success: Int,
        val steamid: String?
    )
    data class OwnedGamesResponse(
        val response: OwnedGamesData
    )
    data class OwnedGamesData(
        val game_count: Int
    )
    object SteamService {
        private const val BASE_URL = "https://api.steampowered.com/"

        val api: SteamAPI by lazy {
            retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
                .create(SteamAPI::class.java)
        }
    }
}