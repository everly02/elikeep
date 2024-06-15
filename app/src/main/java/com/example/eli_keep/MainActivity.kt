package com.example.eli_keep


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.eli_keep.ui.NoteApp
import com.example.eli_keep.viewmodel.NoteViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    NoteApp(noteViewModel)
                }
            }
        }
    }
}