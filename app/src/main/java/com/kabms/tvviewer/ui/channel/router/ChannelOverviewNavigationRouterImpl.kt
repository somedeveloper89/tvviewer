package com.kabms.tvviewer.ui.channel.router

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouter.Router
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouter.Router.OpenChannel
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouter.Router.ShowFileChooser
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouter.RouterInfo

class ChannelOverviewNavigationRouterImpl(val context: Context) :
    ChannelOverviewNavigationRouter {

    private val navigationObservable = MutableLiveData<RouterInfo>()

    override fun getNavigationObservable() = navigationObservable

    override fun navigate(router: Router) {
        navigationObservable.value = when (router) {
            is OpenChannel -> RouterInfo.OpenChannel(getOpenChannelIntent(router.channel))
            is ShowFileChooser -> RouterInfo.ShowFileChooser(getFileChooserIntent())
        }
    }

    private fun getFileChooserIntent() = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        setType("*/*") // todo further specify mime type
    }

    private fun getOpenChannelIntent(channel: Channel) = Intent()
}