package com.kabms.tvviewer.ui.channel.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.kabms.tvviewer.ui.channel.binding.WatchChannelNavigationState
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiEvent
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiEvent.LifeCycleEvent
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiEvent.Phase
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiState

class WatchChannelViewModel(
    private val context: Context
) : ViewModel() {

    companion object {
        private const val TAG = "WatchChannelViewModel"
    }

    private val viewObservable = MutableLiveData<WatchChannelUiState>()

    private val navigationObservable = MutableLiveData<WatchChannelNavigationState>()

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    fun getViewObservable() = viewObservable

    fun getNavigationObservable() = navigationObservable

    init {
        onDispatch(Phase.OnInitialize)
    }

    fun onDispatch(event: WatchChannelUiEvent) {
        Log.d(TAG, "onDispatch: $event")
        when (event) {
            is Phase.OnInitialize -> createPlayer()
            is Phase.OnPlayerAttachedToView -> viewObservable.value =
                WatchChannelUiState.HideSystemUi
            is LifeCycleEvent.OnStart -> initializePlayer()
            is LifeCycleEvent.OnResume -> {
                initializePlayer()
            }
            is LifeCycleEvent.OnPause,
            LifeCycleEvent.OnStop -> releasePlayer()
        }
    }

    private fun createPlayer() {
        Log.d(TAG, "createPlayer")
        if (player == null) {
            player = SimpleExoPlayer.Builder(context).build()
        }
    }

    private fun initializePlayer() {
        Log.d(TAG, "initializePlayer")
        createPlayer()
        player?.let {
            viewObservable.value = WatchChannelUiState.InitPlayer(it)
            it.playWhenReady = playWhenReady
            it.seekTo(currentWindow, playbackPosition)
            it.prepare(createMediaSource(), false, false)
        }
    }

    private fun createMediaSource(): HlsMediaSource {
        Log.d(TAG, "createMediaSource")
        val uri =
            Uri.parse("http://a.jsrdn.com/broadcast/256ad9e679/+0000/high/c.m3u8")
        val dataSourceFactory = DefaultDataSourceFactory(context, "exoplayer-codelab")
        return HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    private fun releasePlayer() {
        Log.d(TAG, "releasePlayer")
        player?.let {
            playWhenReady = it.playWhenReady
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            it.release()
            player = null
        }
    }

}