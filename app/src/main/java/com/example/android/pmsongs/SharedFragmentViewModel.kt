package com.example.android.pmsongs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.pmsongs.placeholder.AppleSong

class SharedFragmentViewModel : ViewModel() {
    val songListLiveData: LiveData<List<AppleSong>>
    init {
        songListLiveData = SongFetcher().fetchContents()
    }

    fun getSelectedSong(position: Int): AppleSong {
        return songListLiveData.value!![position]
    }
}