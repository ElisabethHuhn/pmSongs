package com.example.android.pmsongs.networkapi

import com.example.android.pmsongs.dataclasses.AppleSong
import com.google.gson.annotations.SerializedName

class AppleResponse{

    @SerializedName ("results")
    lateinit var songList: List<AppleSong>
}
