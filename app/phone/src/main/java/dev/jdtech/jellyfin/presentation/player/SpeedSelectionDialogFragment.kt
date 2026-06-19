<<<<<<< HEAD
package com.vdc.tv.presentation.player
=======
package dev.jdtech.jellyfin.presentation.player
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import android.app.Dialog
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
<<<<<<< HEAD
import com.vdc.tv.player.local.R
import com.vdc.tv.player.local.presentation.PlayerViewModel
=======
import dev.jdtech.jellyfin.player.local.R
import dev.jdtech.jellyfin.player.local.presentation.PlayerViewModel
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
import java.lang.IllegalStateException

class SpeedSelectionDialogFragment(private val viewModel: PlayerViewModel) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val speedTexts = listOf("0.5x", "0.75x", "1x", "1.25x", "1.5x", "1.75x", "2x")
        val speedNumbers = listOf(0.5f, 0.75f, 1f, 1.25f, 1.5f, 1.75f, 2f)

        return activity?.let { activity ->
            val builder = MaterialAlertDialogBuilder(activity)
            builder.setTitle(getString(R.string.select_playback_speed)).setSingleChoiceItems(
                speedTexts.toTypedArray(),
                speedNumbers.indexOf(viewModel.playbackSpeed),
            ) { dialog, which ->
                viewModel.selectSpeed(speedNumbers[which])
                dialog.dismiss()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroy() {
        super.onDestroy()
        // Fix for hiding the system bars on API < 30
        activity?.window?.let {
            WindowCompat.getInsetsController(it, it.decorView).apply {
                systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }
}
