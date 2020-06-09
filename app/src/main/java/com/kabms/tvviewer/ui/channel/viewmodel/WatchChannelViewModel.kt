package com.kabms.tvviewer.ui.channel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.ui.channel.binding.WatchChannelNavigationState
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiEvent
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiState

class WatchChannelViewModel(private val channel: Channel) : ViewModel() {

    private val viewObservable = MutableLiveData<WatchChannelUiState>()

    private val navigationObservable = MutableLiveData<WatchChannelNavigationState>()

    fun getViewObservable() = viewObservable

    fun getNavigationObservable() = navigationObservable

    fun onDispatch(event: WatchChannelUiEvent) {
        when (event) {

        }
    }

}