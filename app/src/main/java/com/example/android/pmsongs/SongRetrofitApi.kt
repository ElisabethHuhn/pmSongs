package com.example.android.pmsongs

import retrofit2.Call
import retrofit2.http.GET

private const val SONGS_ENDPOINT_BASE = "/"
private const val SONGS_SEARCH = "/search?"
private const val SONGS_PLUS = "&"
private const val SONGS_JJ = "term=jack+johnson"
private const val SONGS_LIMIT = "limit=25"
private const val SONGS_ENDPOINT = SONGS_SEARCH + SONGS_JJ + SONGS_PLUS + SONGS_LIMIT

interface SongApi {
    //The GET activates the Apple search
    @GET(SONGS_ENDPOINT)
    //returns a Call object that represents a single web request
    //  the Call itself can be executed
    //  deserialize the response to the call into a String
    fun fetchSongs (): Call<AppleResponse>
}