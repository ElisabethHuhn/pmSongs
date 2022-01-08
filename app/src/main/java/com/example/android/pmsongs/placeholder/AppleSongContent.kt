package com.example.android.pmsongs.placeholder

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class adapted from the PlaceHolder content that Android Studio Wizard provides
 */
object AppleSongContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<AppleSong> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, AppleSong> = HashMap()

    private const val COUNT = 25

    init {
        //Initially, just create some dummy content
        //But eventually, this will need to be replaced with
        // a call to the Apple Store to get some songs.

        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createAppleSong(i))
        }
    }

    private fun addItem(item: AppleSong) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }


    private fun createAppleSong(position: Int): AppleSong {
        return AppleSong(position.toString())
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Song: ").append(position)

        //A bit odd that there is one line for each position above this song in the list
        //todo make this a bit more realistic
        for (i in 0 until position) {
            builder.append("\nMore song details here.")
        }
        return builder.toString()
    }

    /**
     * A dummy song item representing a single song in the list
     */
    data class AppleSongItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}