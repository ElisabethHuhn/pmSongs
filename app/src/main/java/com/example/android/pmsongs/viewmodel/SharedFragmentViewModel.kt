package com.example.android.pmsongs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.pmsongs.dataclasses.AppleSong
import com.example.android.pmsongs.repository.SongFetcher

class SharedFragmentViewModel : ViewModel() {
    val songListLiveData: LiveData<List<AppleSong>>
    init {
        songListLiveData = SongFetcher().fetchContents()
    }

    fun getSelectedSong(position: Int): AppleSong {
        return songListLiveData.value!![position]
    }
}