package tuver.notepad.ui.notesummarylist.di

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import tuver.notepad.provider.ImageProvider
import tuver.notepad.provider.impl.GlideImageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object NoteSummaryListFragmentModule {

    @Provides
    @FragmentScoped
    fun provideImageProvider(fragment: Fragment): ImageProvider {
        return GlideImageProvider(Glide.with(fragment))
    }

}