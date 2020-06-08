package com.kabms.tvviewer.ui.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kabms.tvviewer.BR
import com.kabms.tvviewer.R
import com.kabms.tvviewer.domain.Channel

class ChannelAdapter(
    private val callback: (Channel) -> Unit
) : RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder>() {

    private var channelList = emptyList<Channel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.channel_item, parent, false)
        return ChannelViewHolder(binding)
    }

    override fun getItemCount() = channelList.size

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.bind(channelList[position])
    }

    fun setAdapterData(channels: List<Channel>) {
        this.channelList = channels
        notifyDataSetChanged()
    }

    inner class ChannelViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(channel: Channel) {
            binding.setVariable(BR.channel, channel)
//            binding.root.setOnClickListener { callback.invoke(channel) }
            binding.executePendingBindings()
        }
    }
}
