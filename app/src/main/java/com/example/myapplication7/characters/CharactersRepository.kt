package com.example.myapplication7.characters

import com.example.myapplication7.network.NetworkLayer
import com.example.myapplication7.network.response.GetCharacterByIdResponse
import com.example.myapplication7.network.response.GetCharactersPageResponse

class CharactersRepository {

    suspend fun getCharactersPage(pageIndex: Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful){
            return null
        }

        return request.body

    }

}