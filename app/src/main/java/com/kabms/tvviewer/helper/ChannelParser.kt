package com.kabms.tvviewer.helper

import com.kabms.tvviewer.domain.Channel
import java.io.BufferedReader
import java.io.InputStream

class ChannelParser() {

    companion object {
        private const val FILE_HEADER_EXTM3U = "#EXTM3U"

        private const val EXTM3U_ITEM = "#EXTINF:-1"
        private const val ITEM_LOGO_REGEX = "tvg-logo=(.*) group"
        private const val ITEM_GROUP_TITLE_REGEX = "group-title=(.*,)"
        private const val ITEM_LINK_END_REGEX = "(.*).m3u8"

        fun parse(inputStream: InputStream): List<Channel> {
            val content = inputStream.bufferedReader().use(BufferedReader::readText)
            val channelList = mutableListOf<Channel>()
            content.split(EXTM3U_ITEM).forEach {
                channelList.add( Channel().apply {
                    link = retrieveLink(it)
                    logo = retrieveLogo(it)
                    groupTitle = retrieveGroupTitle(it)
                })
            }
            return channelList.filter { it.link != null }
        }

        private fun retrieveLink(content: String): String? {
            val pattern = ITEM_LINK_END_REGEX.toRegex()
            val matches = pattern.find(content)
            return matches?.value
        }

        private fun retrieveLogo(content: String): String? {
            val pattern = ITEM_LOGO_REGEX.toRegex()
            val matches = pattern.find(content)
            return matches?.groups?.get(1)?.value?.replace("\"", "")
        }

        private fun retrieveGroupTitle(content: String): String? {
            val pattern = ITEM_GROUP_TITLE_REGEX.toRegex()
            val matches = pattern.find(content)
            return matches?.groups?.get(1)?.value?.replace("\"", "")?.replace(",", "")
        }
    }
}