package com.kabms.tvviewer.di

import android.content.Context
import com.kabms.tvviewer.feature.ui.ChannelOverviewViewModel
import com.kabms.tvviewer.repository.ChannelRepository
import com.kabms.tvviewer.ui.channel.ChannelOverviewNavigationRouter
import com.kabms.tvviewer.ui.channel.ChannelOverviewNavigationRouterImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    fun getModule() = module {

        single { ChannelRepository() }

        viewModel { ChannelOverviewViewModel(androidApplication(), get()) }

        factory<ChannelOverviewNavigationRouter> { (context: Context) ->
            ChannelOverviewNavigationRouterImpl(context)
        }
    }
}