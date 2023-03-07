package tuver.notepad.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuver.notepad.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }

}