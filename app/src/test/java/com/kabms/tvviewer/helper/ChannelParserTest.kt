package com.kabms.tvviewer.helper

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.InputStream

class ChannelParserTest {

    private lateinit var inputStream: InputStream

    @Before
    fun setup() {
        inputStream = getInputStream(FILE_NAME)
    }

    @Test
    fun `Verify link is extracted from given string content`() {
        assertEquals("http://170.250.96.20:8110/cbs.m3u8", ChannelParser.retrieveLink(TEST_CHANNEL_ITEM_CONTENT))
    }

    @Test
    fun `Verify logo is extracted from given string content`() {
        assertEquals("https://i.imgur.com/pMhTxmf.png", ChannelParser.retrieveLogo(TEST_CHANNEL_ITEM_CONTENT))
    }

    @Test
    fun `Verify group is extracted from given string content`() {
        assertEquals("USA,CBS (WPEC) (EST)", ChannelParser.retrieveGroupTitle(TEST_CHANNEL_ITEM_CONTENT))
    }

    @Test
    fun `Parse a list of Channel from the given inputstream in a coroutinescope`() = runBlocking {
        val channels = GlobalScope.async { ChannelParser.parse(inputStream) }.await()
        println("channel count ${channels.size}")
        channels.forEach { println(it) }

        assertTrue(channels.isNotEmpty())
    }

    private fun getInputStream(fileName: String): InputStream {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)
    }

    companion object {
        private const val FILE_NAME = "streams.txt"

        private const val TEST_CHANNEL_ITEM_CONTENT =
            """#EXTINF:-1 tvg-logo="https://i.imgur.com/pMhTxmf.png" group-title="USA",CBS (WPEC) (EST) http://170.250.96.20:8110/cbs.m3u8"""
    }
}