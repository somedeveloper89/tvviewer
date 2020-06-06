package com.kabms.tvviewer.ui.channel

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
import com.kabms.tvviewer.domain.ChannelOverviewEvent.Action
import com.kabms.tvviewer.domain.ChannelOverviewUiState
import com.kabms.tvviewer.feature.ui.ChannelOverviewViewModel
import kotlinx.android.synthetic.main.fragment_channel_overview.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChannelOverviewFragment : Fragment() {

    private val viewModel: ChannelOverviewViewModel by viewModel()
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
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.hasFixedSize()
        adapter = ChannelAdapter(this::handleClick)
        recyclerview.adapter = adapter
    }

    private fun initObservables() {
        viewModel.getViewObservable().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ChannelOverviewUiState.LoadFinished -> adapter.setAdapterData(it.channels)
            }
        })
    }

    private fun handleClick(channel: Channel) {
        viewModel.onDispatch(Action.OnChannelClick(channel))
        Log.d("ChannelOverviewFragment", "${channel}")
    }

}