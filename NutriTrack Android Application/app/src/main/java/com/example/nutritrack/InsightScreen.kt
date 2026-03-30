package com.example.nutritrack

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.nutritrack.data.patient.AccountInfo
import com.example.nutritrack.data.patient.PatientHeifaScores
import com.example.nutritrack.data.patient.PatientViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    // Setting context
    val context = LocalContext.current

    // Initializing the ViewModel within the questionnaire.
    val viewModel: PatientViewModel = viewModel(
        factory = PatientViewModel.PatientViewModelFactory(context.applicationContext as Application)
    )

    // Grabbing the user ID from shared preferences for reuse
    val sharedPref = context.getSharedPreferences("NutriTrackPrefs", Context.MODE_PRIVATE)
    val savedUserId = sharedPref.getInt("LOGGED_IN_USER_ID", -1)

    val accountInfo = remember { mutableStateOf<PatientHeifaScores?>(null) }

    LaunchedEffect(true) {
        val info = viewModel.getPatientHeifaScores(savedUserId)
        accountInfo.value = info
    }

    // Remember the scroll state for the page
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            // Top app bar displaying title of page
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Insights: Food Score",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.padding(12.dp))

            // Slider column containing all slides
            Column(modifier = Modifier
                .fillMaxSize()) {

                accountInfo.value?.let { info ->
                    // Vegetables Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Heading for slider row
                        Text(
                            text = "Vegetables",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        // shows the user food HEIFA score depending on gender through text
                        Text(
                            text = "${info.vegeHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        // shows the user food HEIFA score depending on gender through slider
                        Slider(
                            value = (info.vegeHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    // Fruits Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Fruits",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.fruitHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.fruitHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    // Grains & Cereals Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Grains & Cereals",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.grainCerealHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.grainCerealHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    // Whole Grains Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Whole Grains",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.wholeGrainHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.wholeGrainHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Meat & Alternatives Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Meat & Alternatives",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.meatAltHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.meatAltHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Dairy Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Dairy",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.dairyAltHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.dairyAltHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Water Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Water",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.waterHeifaScore}/5  "
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.waterHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..5f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Unsaturated Fats Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Unsaturated Fats",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.unsaturatedFatHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.unsaturatedFatHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Sodium Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sodium",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.sodiumHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.sodiumHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Sugar Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sugar",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.sugarHeifaScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.sugarHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Alcohol Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Alcohol",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.alcoholHeifaScore}/5  "
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.alcoholHeifaScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..5f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    //Discretionary Foods Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Discretionary Foods",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1.9f)
                        )

                        Text(
                            text = "${info.heifaDiscretionaryScore}/10"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.heifaDiscretionaryScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..10f,
                            modifier = Modifier
                                .weight(2.85f)
                        )
                    }

                    Spacer(modifier = Modifier.padding(24.dp))

                    // Total Food Quality Score subheading
                    Text(
                        "Total Food Quality Score",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )

                    // Total Score Slider
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${info.heifaTotalScore}/100"
                        )

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Slider(
                            value = (info.heifaTotalScore ?: 0.0).toFloat(),
                            onValueChange = {},
                            valueRange = 0f..100f
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(12.dp))

            accountInfo.value?.let { info ->
                // Share button
                Button(onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "NutriTrack Food Score: ${(info.heifaTotalScore ?: 0.0).toFloat()}/100")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share with someone!"))
                }) {
                    Text("Share with someone!")
                }
            }

            // NutriCoach Button
            Button(onClick = {
                navController.navigate("nutriCoach")
            }) {
                Text("Improve my diet!")
            }
        }
    }
}