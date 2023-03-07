package tuver.notepad.data.di

import tuver.notepad.data.NoteRepository
import tuver.notepad.data.NoteSummaryRepository
import tuver.notepad.data.local.LocalNoteRepository
import tuver.notepad.data.local.LocalNoteSummaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(localNoteRepository: LocalNoteRepository): NoteRepository

    @Binds
    @Singleton
    abstract fun bindNoteSummaryRepository(localNoteSummaryRepository: LocalNoteSummaryRepository): NoteSummaryRepository

}