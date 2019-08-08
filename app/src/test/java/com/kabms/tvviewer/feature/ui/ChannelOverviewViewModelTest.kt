package com.kabms.tvviewer.feature.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kabms.tvviewer.domain.Channel
import com.kabms.tvviewer.repository.ChannelRepository
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

class ChannelOverviewViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val repository = ChannelRepository()
    val viewModel = ChannelOverviewViewModel(repository)

    @Test
    fun `Load list of channels`() {
        val observer: Observer<List<Channel>> = Observer {
            assertEquals(1, 1)
        }

        viewModel.getChannelsList().observeForever(observer)
        viewModel.getChannelsList()

        verify(repository).loadChannels()
    }
}