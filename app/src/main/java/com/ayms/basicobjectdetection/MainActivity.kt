package com.ayms.basicobjectdetection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayms.basicobjectdetection.ui.theme.BasicObjectDetectionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicObjectDetectionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CombinedTitleAndDetectionTypeSelection()
                }
            }
        }
    }
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier
    )
}

@Composable
fun DetectionTypeSelection(
    liveDetectionButtonText: String,
    photoDetectionButtonText: String
) {
    Column {
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(liveDetectionButtonText)
        }
        ElevatedButton(onClick = { /* TODO */ }) {
            Text(photoDetectionButtonText)
        }
    }
}

/**
 * Composable that displays the title and the detection type selection buttons.
 */
@Composable
fun CombinedTitleAndDetectionTypeSelection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Title(title = "Object detection!")
        Spacer(modifier = Modifier.height(16.dp))
        DetectionTypeSelection(
            liveDetectionButtonText = "Live Detection",
            photoDetectionButtonText = "Photo Detection"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    BasicObjectDetectionTheme {
        CombinedTitleAndDetectionTypeSelection()
    }
}