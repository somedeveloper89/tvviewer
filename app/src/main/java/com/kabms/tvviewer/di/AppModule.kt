package com.kabms.tvviewer.di

import com.kabms.tvviewer.feature.ui.ChannelOverviewViewModel
import com.kabms.tvviewer.repository.ChannelRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    fun getModule() = module {

        single { ChannelRepository() }

        viewModel { ChannelOverviewViewModel(get()) }
    }
}