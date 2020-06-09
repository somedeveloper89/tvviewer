package com.kabms.tvviewer.ui.channel.binding

import android.net.Uri
import com.kabms.tvviewer.domain.Channel

sealed class ChannelOverviewUiEvent {

    sealed class Phase : ChannelOverviewUiEvent() {
        object Load : Phase()
    }

    sealed class Action : ChannelOverviewUiEvent() {
        data class OnChannelClick(val channel: Channel) : Action()
        object OnSelectFileClick : Action()
        data class OnFileUriChosen(val uri: Uri) : Action()
    }
}