package com.kabms.tvviewer.repository

import com.kabms.tvviewer.domain.Channel

class ChannelRepository {

    fun loadChannels(): List<Channel> {
        return listOf(
            Channel("dsakfldsjf", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("2332", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("dsakf4sddsldsjf", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("dsakflsdfdsfdsjf", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("2234dxf", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("sdfxc", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("aaaaa", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("335trtfgrrgdfbv", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("dsakfldsjf", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf"),
            Channel("dfbnhgfddfg", "sdfaasdfsa", "dsfsdfadsf", "sdfdsf")
        )
    }
}