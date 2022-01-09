package com.example.android.pmsongs

import android.content.ClipData
import android.os.Bundle
import android.telephony.ims.ImsMmTelManager
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.android.pmsongs.placeholder.PlaceholderContent
import com.example.android.pmsongs.databinding.FragmentItemDetailBinding
import com.example.android.pmsongs.placeholder.AppleSong
import com.example.android.pmsongs.placeholder.AppleSongContent

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    //The shared ViewModel
    private val sharedFragmentViewModel : SharedFragmentViewModel by activityViewModels()

    /**
     * The song this fragment is presenting.
     */
    private var item: AppleSong? = null

    lateinit var itemArtistTextView:     TextView
    lateinit var itemCollectionTextView: TextView
    lateinit var itemTrackNameTextView:  TextView
    var position: Int = 0

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text

            item = sharedFragmentViewModel.getSelectedSong(position)

            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                val positionString = it.getString(ARG_ITEM_ID)
                position = positionString!!.toInt()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout          = binding.toolbarLayout
        itemArtistTextView     = binding.itemArtist
        itemCollectionTextView = binding.itemCollectionName!!
        itemTrackNameTextView  = binding.itemTrackName

        item = sharedFragmentViewModel.getSelectedSong(position)

        updateContent()
        rootView.setOnDragListener(dragListener)

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = item?.id

        // Show the placeholder content as text in a TextView.
        item?.let {
            itemArtistTextView.text     = it.artistName
            itemCollectionTextView.text = it.collectionName
            itemTrackNameTextView.text  = it.trackName
         }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}