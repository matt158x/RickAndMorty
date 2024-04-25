package com.example.myapplication7

import com.example.myapplication7.network.NetworkLayer
import com.example.myapplication7.network.response.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)


        if(request.failed || !request.isSuccessful) {
            return null
        }

        if(!request.isSuccessful) {
            return null
        }

        return request.body

    }


}