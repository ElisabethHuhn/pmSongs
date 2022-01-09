package com.example.android.pmsongs

import com.example.android.pmsongs.placeholder.AppleSong
import com.google.gson.annotations.SerializedName

class AppleResponse{

    @SerializedName ("results")
    lateinit var songList: List<AppleSong>
}
