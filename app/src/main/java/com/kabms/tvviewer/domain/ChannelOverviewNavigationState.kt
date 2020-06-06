package com.kabms.tvviewer.domain

sealed class ChannelOverviewNavigationState {
    data class OpenChannel(val channel: Channel) : ChannelOverviewNavigationState()
}