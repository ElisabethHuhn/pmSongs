package com.example.android.pmsongs.networkapi


import com.example.android.pmsongs.model.CartoonCharacter
import com.example.android.pmsongs.model.RelatedTopic
import com.google.gson.annotations.SerializedName

class ApiResponse {
    @SerializedName("results")
    lateinit var characterHeader: List<CartoonCharacter>
    lateinit var characterList: List<RelatedTopic>
}