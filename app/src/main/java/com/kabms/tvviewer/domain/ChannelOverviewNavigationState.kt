package com.kabms.tvviewer.domain

sealed class ChannelOverviewNavigationState {
    object ShowFileChooser : ChannelOverviewNavigationState()
    data class OpenChannel(val channel: Channel) : ChannelOverviewNavigationState()
}