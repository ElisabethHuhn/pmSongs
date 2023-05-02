package com.example.android.pmsongs.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.pmsongs.model.CartoonCharacter
import com.example.android.pmsongs.model.RelatedTopic
import com.example.android.pmsongs.networkapi.ApiResponse
import com.example.android.pmsongs.networkapi.CharacterApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "CharacterFetcher"
private const val BASE_CHARACTER_URL = "https://api.duckduckgo.com/"

class CharacterFetcher {
    private val characterApi: CharacterApi

    init {
        //Instantiate Retrofit
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_CHARACTER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //use Retrofit to create our API instance
        characterApi = retrofit.create(CharacterApi::class.java)
    }

    fun fetchContents(): MutableLiveData<List<RelatedTopic>> {
        val responseLiveData: MutableLiveData<List<RelatedTopic>> = MutableLiveData()

        //use the api instance to create the web request which will be executed later
        val characterRequest : Call<ApiResponse> = characterApi.fetchCharacters()

        //Define the code to execute upon request return
        val callbackHandler = object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(TAG, "Failure Return from network call", t)
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                Log.d(TAG, "Response Received")
                val apiResponse: ApiResponse?
                var characters: List<CartoonCharacter> = mutableListOf()
                var characterList: List<RelatedTopic> = mutableListOf()

                try {
                    val responseCode = response.code()
                    val responseMessage = response.message()
                    val responseIsSuccessful = response.isSuccessful
                    val responseHeaders = response.headers()
                    val responseErrorBody = response.errorBody()
                    val responseDebug = "code = $responseCode, isSuccessful = $responseIsSuccessful, message = $responseMessage, headers = $responseHeaders, error = $responseErrorBody"
                    Log.i(TAG, responseDebug)
                    apiResponse = response.body()
//                    val innerResponse = response.body().getValue()
                    characters = (apiResponse?.characterHeader ?: mutableListOf())
                    characterList = characters.get(0).RelatedTopics ?: mutableListOf()
                } catch (e: Exception) {
                    Log.e(TAG, "Exception caught accessing response ${e.message}")
                }

                responseLiveData.value = characterList
            }
        }
        //enqueue the call request to Retrofit
        characterRequest.enqueue(callbackHandler)

        return responseLiveData
    }

}