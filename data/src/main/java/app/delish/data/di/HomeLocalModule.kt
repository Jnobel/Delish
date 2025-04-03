package app.delish.data.home.di

import app.delish.data.home.local.HomeLocalDataSource
import app.delish.data.home.local.HomeLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeLocalModule {

    @Binds
    @Singleton
    abstract fun bindHomeLocalDataSource(
        homeLocalDataSourceImpl: HomeLocalDataSourceImpl
    ): HomeLocalDataSource
}