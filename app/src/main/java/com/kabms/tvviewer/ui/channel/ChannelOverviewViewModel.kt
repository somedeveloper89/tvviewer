package com.kabms.tvviewer.feature.ui

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.domain.ChannelOverviewEvent
import com.kabms.tvviewer.domain.ChannelOverviewEvent.Action
import com.kabms.tvviewer.domain.ChannelOverviewNavigationState
import com.kabms.tvviewer.domain.ChannelOverviewUiState
import com.kabms.tvviewer.helper.ChannelParser
import com.kabms.tvviewer.repository.ChannelRepository

class ChannelOverviewViewModel(
    private val context: Context,
    private val repository: ChannelRepository
) : ViewModel() {

    private val viewObservable = MutableLiveData<ChannelOverviewUiState>()

    private val navigationObservable = MutableLiveData<ChannelOverviewNavigationState>()

    private var channels = emptyList<Channel>()

    fun getViewObservable() = viewObservable

    fun getNavigationObservable() = navigationObservable

    fun onDispatch(event: ChannelOverviewEvent) {
        when (event) {
            is Action.OnSelectFileClick -> navigationObservable.value =
                ChannelOverviewNavigationState.ShowFileChooser
            is Action.OnChannelClick -> navigationObservable.value =
                ChannelOverviewNavigationState.OpenChannel(event.channel)
            is Action.OnFileUriChosen -> parseChannels(event.uri)
        }
    }

    private fun parseChannels(uri: Uri) {
        viewObservable.value = ChannelOverviewUiState.Loading
        context.contentResolver.let {
            it?.openInputStream(uri)?.use { inputStream ->
                channels = ChannelParser.parse(inputStream)
                viewObservable.value = ChannelOverviewUiState.LoadFinished(channels)
            }
        }
    }
}