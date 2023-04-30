package com.example.android.pmsongs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.pmsongs.model.RelatedTopic
import com.example.android.pmsongs.repository.CharacterFetcher

class SharedFragmentViewModel : ViewModel() {
    val characterListLiveData: LiveData<List<RelatedTopic>>
    init {
        characterListLiveData = CharacterFetcher().fetchContents()
    }

    fun getSelectedCharacter(position: Int): RelatedTopic {
        return characterListLiveData.value!![position]
    }
}