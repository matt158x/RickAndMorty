package com.example.myapplication7.network.response

class GetCharactersPageResponse(
    val info: Info =Info(),
    val results: List<GetCharacterByIdResponse> = emptyList()
) {
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}