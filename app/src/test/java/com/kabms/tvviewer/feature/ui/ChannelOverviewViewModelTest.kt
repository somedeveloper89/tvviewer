package com.kabms.tvviewer.feature.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.repository.ChannelRepository
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch

class ChannelOverviewViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val countDownLatch = CountDownLatch(1)

    val repository: ChannelRepository = ChannelRepository()
    val viewModel = ChannelOverviewViewModel(repository)

    @Test
    fun `Load list of channels`() {
        val observer: Observer<List<Channel>> = Observer {
            assertEquals(1, 1)
            println("onChanged")
            countDownLatch.countDown()
        }

        viewModel.getChannelsList().observeForever(observer)
        viewModel.getChannelsList()
        countDownLatch.await()
    }
}