<<<<<<< HEAD
package com.vdc.tv.presentation.player
=======
package dev.jdtech.jellyfin.presentation.player
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
<<<<<<< HEAD
import com.vdc.tv.player.local.presentation.PlayerViewModel
import com.vdc.tv.presentation.theme.FindroidTheme
=======
import dev.jdtech.jellyfin.player.local.presentation.PlayerViewModel
import dev.jdtech.jellyfin.presentation.theme.FindroidTheme
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.util.UUID

@AndroidEntryPoint
class SubtitleDownloadBottomSheetFragment : BottomSheetDialogFragment() {

    private var itemId: UUID? = null
    private val activityViewModel: PlayerViewModel by activityViewModels()

    companion object {
        private const val ARG_ITEM_ID = "item_id"

        fun newInstance(itemId: UUID): SubtitleDownloadBottomSheetFragment {
            return SubtitleDownloadBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ITEM_ID, itemId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId = arguments?.getSerializable(ARG_ITEM_ID) as? UUID
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                FindroidTheme {
                    itemId?.let {
                        SubtitleDownloadBottomSheet(
                            itemId = it,
                            onSubtitleDownloaded = { url, title, language ->
                                activityViewModel.addSubtitle(url, title, language)
                            },
                            onDismissRequest = { dismiss() }
                        )
                    }
                }
            }
        }
    }
}
