package com.kabms.tvviewer.ui.channel.binding

import com.kabms.tvviewer.domain.Channel

sealed class ChannelOverviewUiState {
    data class LoadFinished(val channels: List<Channel>) : ChannelOverviewUiState()
    object Loading : ChannelOverviewUiState()
    data class Error(val message: String) : ChannelOverviewUiState()
}