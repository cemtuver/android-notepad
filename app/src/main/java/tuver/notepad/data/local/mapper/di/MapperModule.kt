package tuver.notepad.data.local.mapper.di

import tuver.notepad.data.local.mapper.NoteMapper
import tuver.notepad.data.local.mapper.NoteSummaryMapper
import tuver.notepad.data.local.mapper.NoteUpdateMapper
import tuver.notepad.data.local.mapper.impl.NoteMapperImpl
import tuver.notepad.data.local.mapper.impl.NoteSummaryMapperImpl
import tuver.notepad.data.local.mapper.impl.NoteUpdateMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindNoteMapper(noteMapperImpl: NoteMapperImpl): NoteMapper

    @Binds
    @Singleton
    abstract fun bindNoteUpdateMapper(noteUpdateMapperImpl: NoteUpdateMapperImpl): NoteUpdateMapper

    @Binds
    @Singleton
    abstract fun bindNoteSummaryMapper(noteSummaryMapperImpl: NoteSummaryMapperImpl): NoteSummaryMapper

}