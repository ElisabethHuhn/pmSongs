package com.example.android.pmsongs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.pmsongs.placeholder.AppleSong
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "SongFetcher"
private const val BASE_SONG_URL = "https://itunes.apple.com/"

class SongFetcher {
    private val songApi: SongApi

    init {
        //Instantiate Retrofit
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_SONG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //use Retrofit to create our API instance
        songApi = retrofit.create(SongApi::class.java)
    }

    fun fetchContents(): MutableLiveData<List<AppleSong>> {
        val responseLiveData: MutableLiveData<List<AppleSong>> = MutableLiveData()

        //use the api instance to create the web request which will be executed later
        val appleSongRequest : Call<AppleResponse> = songApi.fetchSongs()

        //now execute the Call request
        val callbackHandler = object : Callback<AppleResponse> {
            override fun onFailure(call: Call<AppleResponse>, t: Throwable) {
                Log.e(TAG, "Failure Return from network call", t)
            }

            override fun onResponse(call: Call<AppleResponse>, response: Response<AppleResponse>) {
                Log.d(TAG, "Response Received")
                val appleResponse: AppleResponse? = response.body()
                val songList: List<AppleSong> = appleResponse?.songList ?: mutableListOf()

                responseLiveData.value = songList
            }
        }
        //enqueue the call request to Retrofit
        appleSongRequest.enqueue(callbackHandler)

        return responseLiveData
    }





}