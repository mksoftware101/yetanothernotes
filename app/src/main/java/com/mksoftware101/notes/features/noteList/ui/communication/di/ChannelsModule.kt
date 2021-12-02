package com.mksoftware101.notes.features.noteList.ui.communication.di

import com.mksoftware101.notes.features.noteList.ui.communication.noteslistitem.ErrorChannel
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
    fun providesChannel() = ErrorChannel()
}