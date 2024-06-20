package com.example.eli_keep.ui

import android.Manifest
import android.media.MediaRecorder
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eli_keep.utils.RequestPermissions
import java.io.IOException

@Composable
fun AudioRecordScreen(existingAudioPath: String? = null, onSave: (String) -> Unit, onCancel: () -> Unit) {
    val context = LocalContext.current
    val mediaRecorder = remember { MediaRecorder() }
    var isRecording by remember { mutableStateOf(false) }
    var hasPermission by remember { mutableStateOf(false) }
    val outputPath = context.getExternalFilesDir(null)?.absolutePath + "/recorded_audio.3gp"

    RequestPermissions(
        permissions = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_MEDIA_AUDIO
        ),
        onPermissionsResult = { granted ->
            hasPermission = granted
        }
    )

    fun startRecording() {
        try {
            mediaRecorder.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(outputPath)
                prepare()
                start()
            }
            isRecording = true
        } catch (e: IOException) {
            Toast.makeText(context, "Recording failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun stopRecording() {
        try {
            mediaRecorder.stop()
            mediaRecorder.release()
            isRecording = false
        } catch (e: RuntimeException) {
            Toast.makeText(context, "Stopping failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    if (!hasPermission) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Permissions not granted", color = MaterialTheme.colorScheme.error)
        }
        return
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(4.dp) // Closing parenthesis added here
        ) {
            Column(modifier =Modifier.padding(16.dp)) {
                Text("Audio Recorder", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                if (existingAudioPath != null) {
                    Text("Existing Audio: $existingAudioPath")
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (isRecording) {
                    Text("Recording...", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
        if (isRecording) {
            Button(
                onClick = { stopRecording(); onSave(outputPath) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
            ) {
                Text("Stop and Save")
            }
        } else {
            Button(
                onClick = { startRecording() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Recording")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}
