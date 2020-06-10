package com.kabms.tvviewer.ui.channel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import com.kabms.tvviewer.R
import com.kabms.tvviewer.databinding.FragmentWatchChannelBinding
import com.kabms.tvviewer.ui.channel.binding.WatchChannelNavigationState
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiEvent
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiState
import com.kabms.tvviewer.ui.channel.router.WatchChannelNavigationRouter
import com.kabms.tvviewer.ui.channel.viewmodel.WatchChannelViewModel
import kotlinx.android.synthetic.main.fragment_watch_channel.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WatchChannelFragment : Fragment() {

    private val viewModel: WatchChannelViewModel by viewModel {
        parametersOf(requireContext())
    }
    private val navigator: WatchChannelNavigationRouter by inject()

    private lateinit var playerView: PlayerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentWatchChannelBinding>(
            inflater,
            R.layout.fragment_watch_channel,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservables()
    }

    private fun initView() {
        playerView = video_view
    }

    private fun initObservables() {
        viewModel.getViewObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is WatchChannelUiState.Loading -> showLoading()
                is WatchChannelUiState.InitPlayer -> initPlayerToView(it.player)
                is WatchChannelUiState.HideSystemUi -> hideSystemUi()
            }
        })

        viewModel.getNavigationObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is WatchChannelNavigationState.OnQuit -> navigator.navigate(
                    WatchChannelNavigationRouter.Router.OnQuit
                )
            }
        })

        navigator.getNavigationObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is WatchChannelNavigationRouter.RouterInfo.Quit -> requireActivity().onBackPressed()
            }
        })
    }

    private fun initPlayerToView(player: SimpleExoPlayer) {
        playerView.player = player
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            viewModel.onDispatch(WatchChannelUiEvent.LifeCycleEvent.OnStart)
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24) {
            viewModel.onDispatch(WatchChannelUiEvent.LifeCycleEvent.OnResume)
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            viewModel.onDispatch(WatchChannelUiEvent.LifeCycleEvent.OnPause)
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            viewModel.onDispatch(WatchChannelUiEvent.LifeCycleEvent.OnStop)
        }
    }

    private fun showLoading() {
        // TODO: 10/06/2020
    }

    private fun hideSystemUi() {
        video_view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}
