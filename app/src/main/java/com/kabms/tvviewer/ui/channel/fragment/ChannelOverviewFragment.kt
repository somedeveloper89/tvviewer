package com.kabms.tvviewer.ui.channel.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kabms.tvviewer.R
import com.kabms.tvviewer.databinding.FragmentChannelOverviewBinding
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.feature.ui.ChannelOverviewViewModel
import com.kabms.tvviewer.helper.setGone
import com.kabms.tvviewer.helper.setVisible
import com.kabms.tvviewer.ui.channel.adapter.ChannelAdapter
import com.kabms.tvviewer.ui.channel.binding.ChannelOverviewNavigationState
import com.kabms.tvviewer.ui.channel.binding.ChannelOverviewUiEvent.Action
import com.kabms.tvviewer.ui.channel.binding.ChannelOverviewUiState
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouter
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouter.Router
import kotlinx.android.synthetic.main.fragment_channel_overview.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChannelOverviewFragment : Fragment() {

    companion object {
        private const val PICK_M3U_FILE_REQUEST = 1;
    }

    private val viewModel: ChannelOverviewViewModel by viewModel {
        parametersOf(activity)
    }
    private val navigator: ChannelOverviewNavigationRouter by inject {
        parametersOf(activity)
    }
    private lateinit var adapter: ChannelAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentChannelOverviewBinding>(
            inflater,
            R.layout.fragment_channel_overview,
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
        select_file_button.setOnClickListener { viewModel.onDispatch(Action.OnSelectFileClick) }

        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.hasFixedSize()
        adapter = ChannelAdapter(this::handleClick)
        recyclerview.adapter = adapter
    }

    private fun initObservables() {
        viewModel.getViewObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ChannelOverviewUiState.Loading -> renderLoading()
                is ChannelOverviewUiState.LoadFinished -> renderLoadingFinished(it)
            }
        })

        viewModel.getNavigationObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ChannelOverviewNavigationState.ShowFileChooser -> navigator.navigate(
                    Router.ShowFileChooser
                )
                is ChannelOverviewNavigationState.OpenChannel -> navigator.navigate(
                    Router.OpenChannel(it.channel)
                )
            }
        })

        navigator.getNavigationObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ChannelOverviewNavigationRouter.RouterInfo.ShowFileChooser -> it.intent?.let { intent ->
                    startActivityForResult(
                        intent,
                        PICK_M3U_FILE_REQUEST
                    )
                }
                is ChannelOverviewNavigationRouter.RouterInfo.OpenChannel -> it.intent?.let { intent ->
                    startActivity(intent)
                }
            }
        })
    }

    private fun renderLoading() {
        progressbar.setVisible()
    }

    private fun renderLoadingFinished(it: ChannelOverviewUiState.LoadFinished) {
        adapter.setAdapterData(it.channels)
        progressbar.setGone()
    }

    private fun handleClick(channel: Channel) {
        viewModel.onDispatch(Action.OnChannelClick(channel))
        Log.d("ChannelOverviewFragment", "${channel}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_M3U_FILE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.also {
                viewModel.onDispatch(Action.OnFileUriChosen(it))
            }
        }
    }
}