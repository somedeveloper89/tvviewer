package com.kabms.tvviewer.ui.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.kabms.tvviewer.AppExecutors
import com.kabms.tvviewer.R
import com.kabms.tvviewer.databinding.ChannelItemBinding
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.ui.generic.DataBoundListAdapter

class ChannelAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: (Channel) -> Unit
) : DataBoundListAdapter<Channel, ChannelItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.logo == newItem.logo &&
                    oldItem.country == newItem.country
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ChannelItemBinding {
        val binding = DataBindingUtil
            .inflate<ChannelItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.channel_item,
                parent,
                false,
                dataBindingComponent
            )
        binding.root.setOnClickListener {
            binding.channel?.let { channel ->
                callback.invoke(channel)
            }
        }
        return binding
    }

    override fun bind(binding: ChannelItemBinding, item: Channel) {
        binding.channel = item
    }
}