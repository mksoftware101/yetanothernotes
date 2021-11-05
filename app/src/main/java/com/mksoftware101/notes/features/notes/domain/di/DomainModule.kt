package com.mksoftware101.notes.features.notes.domain.di

import com.mksoftware101.notes.core.repository.NotesRepository
import com.mksoftware101.notes.features.notes.domain.FetchNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun prividesFetchNotesUseCase(repository: NotesRepository) = FetchNotesUseCase(repository)
}