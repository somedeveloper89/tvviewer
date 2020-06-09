package com.kabms.tvviewer.ui.channel.router

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.kabms.tvviewer.domain.Channel

interface ChannelOverviewNavigationRouter {

    sealed class Router {
        object ShowFileChooser : Router()
        data class OpenChannel(val channel: Channel) : Router()
    }

    sealed class RouterInfo {
        data class ShowFileChooser(val intent: Intent?) : RouterInfo()
        data class OpenChannel(val intent: Intent?) : RouterInfo()
    }

    fun getNavigationObservable(): MutableLiveData<RouterInfo>

    fun navigate(router: Router)
}