package com.kabms.tvviewer.ui.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kabms.tvviewer.R
import com.kabms.tvviewer.binding.FragmentDataBindingComponent
import com.kabms.tvviewer.databinding.FragmentChannelOverviewBinding
import com.kabms.tvviewer.feature.ui.ChannelOverviewViewModel
import com.kabms.tvviewer.util.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChannelOverviewFragment : Fragment() {

    private val channelViewModel: ChannelOverviewViewModel by viewModel()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var adapter by autoCleared<ChannelAdapter>()
    private var binding by autoCleared<FragmentChannelOverviewBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentChannelOverviewBinding>(
            inflater,
            R.layout.fragment_channel_overview,
            container,
            false
        )
        binding = dataBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

//    private fun initRecyclerView(): UserAdapter {
//        recyclerview.layoutManager = LinearLayoutManager(context)
//        recyclerview.hasFixedSize()
//        val adapter = UserAdapter()
//        recyclerview.adapter = adapter
//        return adapter
//    }
}