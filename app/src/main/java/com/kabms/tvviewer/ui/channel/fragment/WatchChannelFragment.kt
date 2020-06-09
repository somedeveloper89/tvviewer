package com.kabms.tvviewer.ui.channel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kabms.tvviewer.R
import com.kabms.tvviewer.databinding.FragmentWatchChannelBinding
import com.kabms.tvviewer.ui.channel.binding.WatchChannelNavigationState
import com.kabms.tvviewer.ui.channel.binding.WatchChannelUiState
import com.kabms.tvviewer.ui.channel.router.WatchChannelNavigationRouter
import com.kabms.tvviewer.ui.channel.viewmodel.WatchChannelViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchChannelFragment : Fragment() {

    private val viewModel: WatchChannelViewModel by viewModel()
    private val navigator: WatchChannelNavigationRouter by inject()

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

    }

    private fun initObservables() {
        viewModel.getViewObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is WatchChannelUiState.Loading -> showLoading()
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

    private fun showLoading() {
        
    }
}