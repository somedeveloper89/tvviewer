package com.kabms.tvviewer.di

import android.content.Context
import com.kabms.tvviewer.feature.ui.ChannelOverviewViewModel
import com.kabms.tvviewer.repository.ChannelRepository
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouter
import com.kabms.tvviewer.ui.channel.router.ChannelOverviewNavigationRouterImpl
import com.kabms.tvviewer.ui.channel.router.WatchChannelNavigationRouter
import com.kabms.tvviewer.ui.channel.router.WatchChannelNavigationRouterImpl
import com.kabms.tvviewer.ui.channel.viewmodel.WatchChannelViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    fun getModule() = module {

        // ChannelOverviewFragment

        single { ChannelRepository() }

        viewModel { ChannelOverviewViewModel(androidApplication(), get()) }

        factory<ChannelOverviewNavigationRouter> { (context: Context) ->
            ChannelOverviewNavigationRouterImpl(
                context
            )
        }

        // WatchChannelFragment

        viewModel { (context: Context) ->
            WatchChannelViewModel(
                context
            )
        }

        factory<WatchChannelNavigationRouter> {
            WatchChannelNavigationRouterImpl()
        }
    }
}