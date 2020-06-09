package com.kabms.tvviewer.ui.channel.router

import androidx.lifecycle.MutableLiveData

interface WatchChannelNavigationRouter {

    sealed class Router {
        object OnQuit : Router()
    }

    sealed class RouterInfo {
        object Quit : RouterInfo()
    }

    fun getNavigationObservable(): MutableLiveData<RouterInfo>

    fun navigate(router: Router)

}