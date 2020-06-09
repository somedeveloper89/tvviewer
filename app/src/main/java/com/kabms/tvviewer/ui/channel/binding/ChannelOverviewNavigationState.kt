package com.kabms.tvviewer.ui.channel.binding

import com.kabms.tvviewer.domain.Channel

sealed class ChannelOverviewNavigationState {
    object ShowFileChooser : ChannelOverviewNavigationState()
    data class OpenChannel(val channel: Channel) : ChannelOverviewNavigationState()
}