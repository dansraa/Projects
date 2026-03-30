package com.example.nutritrack

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritrack.data.patient.PatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminView(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val viewModel: PatientViewModel = viewModel(
        factory = PatientViewModel.PatientViewModelFactory(context.applicationContext as Application)
    )

    val avgMale by viewModel.avgMaleScore
    val avgFemale by viewModel.avgFemaleScore

    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Column{
            CenterAlignedTopAppBar(
                title = {
                    Text("Clinician Dashboard",
                        fontWeight = FontWeight.Bold
                    )
                }
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = "Average Male HEIFA Score: ${avgMale?.let { String.format("%.2f", it) } ?: "N/A"}",
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text("Average HEIFA (Male) ")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                OutlinedTextField(
                    value = "Average Female HEIFA Score: ${avgFemale?.let { String.format("%.2f", it) } ?: "N/A"}",
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text("Average HEIFA (Female) ")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }

}