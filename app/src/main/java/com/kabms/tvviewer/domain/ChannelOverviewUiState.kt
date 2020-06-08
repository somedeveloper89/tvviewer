package com.kabms.tvviewer.domain

sealed class ChannelOverviewUiState {
    data class LoadFinished(val channels: List<Channel>) : ChannelOverviewUiState()
    object Loading : ChannelOverviewUiState()
    data class Error(val message: String) : ChannelOverviewUiState()
}