package com.kabms.tvviewer.domain

sealed class ChannelOverviewEvent {

    sealed class Phase : ChannelOverviewEvent() {
        object Load : Phase()
    }

    sealed class Action : ChannelOverviewEvent() {
        data class OnChannelClick(val channel: Channel) : Action()
    }
}