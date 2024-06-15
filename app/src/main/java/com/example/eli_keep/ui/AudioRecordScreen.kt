package com.example.eli_keep.ui

import android.media.MediaRecorder
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AudioRecordScreen(onSave: (String) -> Unit, onCancel: () -> Unit) {
    val context = LocalContext.current
    val mediaRecorder = remember { MediaRecorder() }
    var isRecording by remember { mutableStateOf(false) }
    val outputPath = context.getExternalFilesDir(null)?.absolutePath + "/recorded_audio.3gp"

    fun startRecording() {
        mediaRecorder.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(outputPath)
            prepare()
            start()
        }
        isRecording = true
    }

    fun stopRecording() {
        mediaRecorder.stop()
        mediaRecorder.release()
        isRecording = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (isRecording) {
            Button(onClick = { stopRecording(); onSave(outputPath) }) {
                Text("Stop and Save")
            }
        } else {
            Button(onClick = { startRecording() }) {
                Text("Start Recording")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onCancel) {
            Text("Cancel")
        }
    }
}