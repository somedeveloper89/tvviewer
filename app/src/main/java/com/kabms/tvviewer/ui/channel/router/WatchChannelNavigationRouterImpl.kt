package com.kabms.tvviewer.ui.channel.router

import androidx.lifecycle.MutableLiveData

class WatchChannelNavigationRouterImpl : WatchChannelNavigationRouter {

    private val navigationObservable = MutableLiveData<WatchChannelNavigationRouter.RouterInfo>()

    override fun getNavigationObservable() = navigationObservable

    override fun navigate(router: WatchChannelNavigationRouter.Router) {
        TODO("Not yet implemented")
    }
}