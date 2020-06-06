package com.kabms.tvviewer.feature.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kabms.tvviewer.domain.ChannelOverviewEvent
import com.kabms.tvviewer.domain.ChannelOverviewEvent.Action
import com.kabms.tvviewer.domain.ChannelOverviewEvent.Phase
import com.kabms.tvviewer.domain.ChannelOverviewNavigationState
import com.kabms.tvviewer.domain.ChannelOverviewUiState
import com.kabms.tvviewer.repository.ChannelRepository

class ChannelOverviewViewModel(private val repository: ChannelRepository) : ViewModel() {

    private val viewObservable = MutableLiveData<ChannelOverviewUiState>()

    private val navigationObservable = MutableLiveData<ChannelOverviewNavigationState>()

    init {
        onDispatch(Phase.Load)
    }

    fun getViewObservable() = viewObservable

    fun getNavigationObservable() = navigationObservable

    fun onDispatch(event: ChannelOverviewEvent) {
        when (event) {
            is Phase.Load -> viewObservable.value =
                ChannelOverviewUiState.LoadFinished(repository.loadChannels())
            is Action.OnChannelClick -> navigationObservable.value =
                ChannelOverviewNavigationState.OpenChannel(event.channel)
        }
    }
}