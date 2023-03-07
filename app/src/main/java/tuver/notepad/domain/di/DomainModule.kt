package tuver.notepad.domain.di

import tuver.notepad.domain.NoteDeleteUseCase
import tuver.notepad.domain.NoteGetNoteSummaryListPagingDataUseCase
import tuver.notepad.domain.NoteGetUseCase
import tuver.notepad.domain.NoteSaveUseCase
import tuver.notepad.domain.impl.NoteDeleteUseCaseImpl
import tuver.notepad.domain.impl.NoteGetNoteSummaryListPagingDataUseCaseImpl
import tuver.notepad.domain.impl.NoteGetUseCaseImpl
import tuver.notepad.domain.impl.NoteSaveUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindNoteGetUseCase(noteGetUseCaseImpl: NoteGetUseCaseImpl): NoteGetUseCase

    @Binds
    @Singleton
    abstract fun bindNoteSaveUseCase(noteSaveUseCaseImpl: NoteSaveUseCaseImpl): NoteSaveUseCase

    @Binds
    @Singleton
    abstract fun bindNoteDeleteUseCase(noteDeleteUseCaseImpl: NoteDeleteUseCaseImpl): NoteDeleteUseCase

    @Binds
    @Singleton
    abstract fun bindNoteGetNoteListPagingDataUseCase(noteGetNoteListPagingDataUseCaseImpl: NoteGetNoteSummaryListPagingDataUseCaseImpl): NoteGetNoteSummaryListPagingDataUseCase

}