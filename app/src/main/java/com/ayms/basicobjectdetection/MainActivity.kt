package com.ayms.basicobjectdetection

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ayms.basicobjectdetection.ui.theme.BasicObjectDetectionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicObjectDetectionTheme {
                val selectedImageUri = remember {
                    mutableStateOf<Uri?>(null)
                }

                val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
                    contract = PickVisualMedia(),
                    onResult = { uri ->
                        if (uri != null) {
                            Log.d("PhotoPicker", "Selected URI: $uri")
                        } else {
                            Log.d("PhotoPicker", "No media selected")
                        }
                    }
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CombinedTitleAndDetectionTypeSelection(singlePhotoPickerLauncher)
                    item {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
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
    photoDetectionButtonText: String,
    singlePhotoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>
) {
    Column {
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(liveDetectionButtonText)
        }
        ElevatedButton(onClick = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ImageOnly)
            )
        }) {
            Text(photoDetectionButtonText)
        }
    }
}

/**
 * Composable that displays the title and the detection type selection buttons.
 */
@Composable
fun CombinedTitleAndDetectionTypeSelection(
    singlePhotoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Title(title = "Object detection!")
        Spacer(modifier = Modifier.height(16.dp))
        DetectionTypeSelection(
            liveDetectionButtonText = "Live Detection",
            photoDetectionButtonText = "Photo Detection",
            singlePhotoPickerLauncher = singlePhotoPickerLauncher
        )
    }
}

fun item(function: @Composable () -> Unit) {

}

//@Preview(showBackground = true)
//@Composable
//fun TitlePreview() {
//    BasicObjectDetectionTheme {
//        CombinedTitleAndDetectionTypeSelection(
//            singlePhotoPickerLauncher =
//        )
//    }
//}