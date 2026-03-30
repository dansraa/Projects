package com.example.nutritrack

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nutritrack.data.patient.AccountInfo
import com.example.nutritrack.data.patient.Patient
import com.example.nutritrack.data.patient.PatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(modifier:Modifier = Modifier, navController: NavHostController) {

    val context = LocalContext.current

    val sharedPref = context.getSharedPreferences("NutriTrackPrefs", Context.MODE_PRIVATE)
    val savedUserId = sharedPref.getInt("LOGGED_IN_USER_ID", -1)

    // Initializing the ViewModel within the questionnaire.
    val viewModel: PatientViewModel = viewModel(
        factory = PatientViewModel.PatientViewModelFactory(context.applicationContext as Application)
    )

    val accountInfo = remember { mutableStateOf<AccountInfo?>(null) }

    LaunchedEffect(true) {
        val info = viewModel.getAccountInformation(savedUserId)
        accountInfo.value = info
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Settings",
                        fontSize = 24.sp,
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
                    "ACCOUNT",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                // Patient name row
                accountInfo.value?.let { info ->
                    // Patient Name Row
                    Row {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Name")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Name: ${info.name}", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Phone Number Row
                    Row {
                        Icon(Icons.Default.Phone, contentDescription = "Phone")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Phone: ${info.phoneNo}", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // User ID Row
                    Row {
                        Icon(Icons.Default.Info, contentDescription = "User ID")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("User ID: ${info.userId}", fontSize = 18.sp)
                    }

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp
                    )

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                    Text(
                        "OTHER SETTINGS",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray
                    )

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                    // Logout row
                    Row(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    val sharedPrefQuiz = context.getSharedPreferences(
                                        "foodintakesp",
                                        Context.MODE_PRIVATE
                                    )
                                    val sharedPref = context.getSharedPreferences(
                                        "NutriTrackPrefs",
                                        Context.MODE_PRIVATE
                                    )
                                    sharedPrefQuiz.edit().clear().apply()
                                    sharedPref.edit().clear().apply()
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                }
                            )
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Logout")

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Text(
                            "Logout",
                            fontSize = 18.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // Clinician login
                    Row(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                     navController.navigate("clinicianLogin")
                                }
                            )
                    ) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Clinician Login")

                        Spacer(
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Text(
                            "Clinician Login",
                            fontSize = 18.sp
                        )
                    }

                }

            }
        }
    }
}