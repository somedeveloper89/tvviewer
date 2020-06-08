package com.kabms.tvviewer.domain

import android.net.Uri

sealed class ChannelOverviewEvent {

    sealed class Phase : ChannelOverviewEvent() {
        object Load : Phase()
    }

    sealed class Action : ChannelOverviewEvent() {
        data class OnChannelClick(val channel: Channel) : Action()
        object OnSelectFileClick : Action()
        data class OnFileUriChosen(val uri: Uri) : Action()
    }
}