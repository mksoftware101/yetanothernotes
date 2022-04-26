package com.mksoftware101.notes.legacy.features.noteList.ui.communication.di

import com.mksoftware101.notes.legacy.features.noteList.ui.communication.noteslistitem.Channels
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChannelsModule {

    @Provides
    @Singleton
    fun providesChannels() = Channels()
}