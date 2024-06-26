package com.example.myapplication7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication7.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse?>()
    val characterByIdLiveData: LiveData<GetCharacterByIdResponse?> = _characterByIdLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)

            _characterByIdLiveData.postValue(response)
        }

    }

}