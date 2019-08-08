package com.kabms.tvviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kabms.tvviewer.domain.Channel

class ChannelRepository {

    fun loadChannels(): LiveData<List<Channel>> {
        val livedata = MutableLiveData<List<Channel>>()

        // load data

        return livedata
    }
}