package com.kabms.tvviewer.domain

sealed class ChannelOverviewUiState {
    data class LoadFinished(val channels: List<Channel>) : ChannelOverviewUiState()
}