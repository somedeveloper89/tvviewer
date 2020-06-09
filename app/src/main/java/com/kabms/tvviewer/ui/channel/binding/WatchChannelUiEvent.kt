package com.kabms.tvviewer.ui.channel.binding

sealed class WatchChannelUiEvent {
    object OnReload : WatchChannelUiEvent()
    object OnNext : WatchChannelUiEvent()
    object OnPrevious : WatchChannelUiEvent()
}