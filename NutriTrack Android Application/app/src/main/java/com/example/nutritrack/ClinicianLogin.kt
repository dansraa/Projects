package com.example.nutritrack

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicianLogin(modifier: Modifier = Modifier, navController: NavHostController) {

    val context = LocalContext.current

    var inputKey by remember { mutableStateOf("") }
    var clinicianKey by remember { mutableStateOf("dollar-entry-apples") }


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            CenterAlignedTopAppBar(
                title = {
                    Text("Clinician Login",
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
                Text(
                    "Clinician Key",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                OutlinedTextField(
                    value = inputKey,
                    onValueChange = {inputKey = it},
                    label = {
                        Text("Enter your clinician key.")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                 onClick = {
                     if (inputKey == clinicianKey) {
                         navController.navigate("adminView")
                     } else if (inputKey != clinicianKey) {
                         Toast.makeText(context, "Incorrect Key", Toast.LENGTH_SHORT).show()
                     }
                 },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "Clinician Login",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}