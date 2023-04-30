package com.example.android.pmsongs.uicontrollers

import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.android.pmsongs.viewmodel.SharedFragmentViewModel

import com.example.android.pmsongs.databinding.FragmentItemDetailBinding
import com.example.android.pmsongs.model.RelatedTopic


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
     * The character this fragment is presenting.
     */
    private var item: RelatedTopic? = null

    lateinit var itemCharacterTextView: TextView
    lateinit var itemCharacterIconTextView: TextView
    lateinit var itemCharacterDescriptionTextView:  TextView
    var position: Int = 0

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
//            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
//            val dragData = clipDataItem.text

            item = sharedFragmentViewModel.getSelectedCharacter(position)

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
    ): View {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout                    = binding.toolbarLayout
        itemCharacterTextView            = binding.itemCharacter
        itemCharacterIconTextView        = binding.itemCharacterIcon
        itemCharacterDescriptionTextView = binding.itemCharacterDescription

        item = sharedFragmentViewModel.getSelectedCharacter(position)

        updateContent()
        rootView.setOnDragListener(dragListener)

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = item?.id

        // Show the placeholder content as text in a TextView.
        item?.let {
            itemCharacterTextView.text     = it.FirstURL
            itemCharacterIconTextView.text = it.Icon.URL
            itemCharacterDescriptionTextView.text  = it.Text
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