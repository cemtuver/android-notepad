package tuver.notepad.data.local.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tuver.notepad.data.local.database.NoteDao
import tuver.notepad.data.local.database.NoteDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext applicationContext: Context): NoteDatabase {
        val databaseBuilder = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            NoteDatabase.NAME
        )

        return databaseBuilder.build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }

}