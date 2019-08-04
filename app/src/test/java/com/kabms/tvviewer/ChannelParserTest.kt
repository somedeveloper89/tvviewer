package com.kabms.tvviewer

import com.kabms.tvviewer.helper.ChannelParser
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
    fun test() {
        val channels = ChannelParser.parse(inputStream)

        println("channel count ${channels.size}")

        assertTrue(channels.isNotEmpty())
    }

    private fun getInputStream(fileName: String): InputStream {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)
    }

    companion object {
        private const val FILE_NAME = "streams.txt"

    }
}