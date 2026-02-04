package com.example.libraryview

data class OwnedGamesResponse(
    val response: GamesResponse
)

data class GamesResponse(
    val game_count: Int,
    val games: List<Game>
)

data class Game(
    val appid: Int,
    val name: String?,
    val playtime_forever: Int
)
