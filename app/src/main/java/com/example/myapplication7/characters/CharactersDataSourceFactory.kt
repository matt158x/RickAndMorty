package com.example.myapplication7.characters

import androidx.paging.DataSource
import com.example.myapplication7.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(


    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository

): DataSource.Factory<Int, GetCharacterByIdResponse>() {

    override fun create(): DataSource<Int, GetCharacterByIdResponse> {
        return CharactersDataSource(coroutineScope, repository)
    }


}
