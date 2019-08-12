package com.kabms.tvviewer.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.repository.ChannelRepository

class ChannelOverviewViewModel(private val repository: ChannelRepository) : ViewModel() {

    fun getChannelsList(): LiveData<List<Channel>> {
        return repository.loadChannels()
    }
}