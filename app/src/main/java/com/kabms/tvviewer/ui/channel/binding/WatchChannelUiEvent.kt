package com.kabms.tvviewer.ui.channel.binding

sealed class WatchChannelUiEvent {

    sealed class Phase {
        object OnInitialize : WatchChannelUiEvent()
    }

    sealed class LifeCycleEvent {
        object OnStart : WatchChannelUiEvent()
        object OnResume : WatchChannelUiEvent()
        object OnPause : WatchChannelUiEvent()
        object OnStop : WatchChannelUiEvent()
    }

    sealed class UserInputEvent {
        object OnPlay : WatchChannelUiEvent()
        object OnPause : WatchChannelUiEvent()
        object OnStop : WatchChannelUiEvent()
    }
}