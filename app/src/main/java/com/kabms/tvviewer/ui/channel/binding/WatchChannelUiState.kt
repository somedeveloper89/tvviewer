package com.kabms.tvviewer.ui.channel.binding

import com.google.android.exoplayer2.SimpleExoPlayer

sealed class WatchChannelUiState {
    object Loading : WatchChannelUiState()
    data class InitPlayer(val player: SimpleExoPlayer) : WatchChannelUiState()
    object HideSystemUi : WatchChannelUiState()
}