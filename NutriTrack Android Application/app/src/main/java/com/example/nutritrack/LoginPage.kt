package com.example.nutritrack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritrack.RegistrationPage
import com.example.nutritrack.data.patient.PatientViewModel
import com.example.nutritrack.ui.theme.NutriTrackTheme
import java.io.BufferedReader
import java.io.InputStreamReader

@ExperimentalMaterial3Api
class LoginPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val patientViewModel: PatientViewModel = ViewModelProvider(
            this, PatientViewModel.PatientViewModelFactory(this@LoginPage)
        ) [PatientViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            NutriTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        patientViewModel
                    )
                }
            }
        }
    }
}

/**
 * @param viewModel
 */

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(modifier: Modifier = Modifier, patientViewModel: PatientViewModel) {

    // context
    val context = LocalContext.current

    // userID list
    val userIdList by patientViewModel.allUserId.collectAsState(initial = emptyList())
    val expanded = remember {mutableStateOf(false)}
    val selectedId = remember {mutableStateOf("")}
    // Potentially an issue if reinputting data into an existing column and user id is a string.
    val userIds = userIdList.map { it.toString() } // mapping the user ids to strings, potentially an issue for string.

    // State variable for phone number temporary
    val patientPassword = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // Login in text at the top of screen
            Text(
                text = "Log in",
                fontSize = 24.sp,
                // Applying bold font weight to log in text
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = { expanded.value = !expanded.value }
            ) {
                OutlinedTextField(
                    value = selectedId.value,
                    onValueChange = {},
                    label = { Text("My ID") },
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 16.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    userIds.forEach { userId ->
                        DropdownMenuItem(
                            text = { Text(userId) },
                            onClick = {
                                selectedId.value = userId
                                expanded.value = false
                            }
                        )

                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Phone number input field with validation.
            OutlinedTextField(
                value = patientPassword.value,
                onValueChange = { patientPassword.value = it},
                label = { Text("Password") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Spacer (modifier = Modifier.height(24.dp))

            Text(
                text = "This app is only for pre-registered users. Please have \n" +
                "your ID and phone number handy before continuing.",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer (modifier = Modifier.height(24.dp))

            // Button with validation checks for phone number and id.
            Button(
                onClick = {
                    val userid = selectedId.value.toIntOrNull()
                    val password = patientPassword.value

                    // Validation within the selected user id field for user to
                    // select id in the field mandatory.
                    if (userid == null) {
                        Toast.makeText(context, "Please select a valid ID", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // Using viewmodel function to validate password and userid against table within the db.
                    patientViewModel.loginIsValid(userid, password) { valid ->
                        if (valid) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

                            // Save userId using SharedPreferences
                            val sharedPref = context.getSharedPreferences("NutriTrackPrefs", Context.MODE_PRIVATE)
                            with(sharedPref.edit()) {
                                putBoolean("IsLoggedIn", true)
                                putInt("LOGGED_IN_USER_ID", userid)
                                apply()
                            }

                            context.startActivity(Intent(context, FoodIntakeQuestion::class.java))
                        } else {
                            Toast.makeText(context, "Invalid user ID or password", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Continue")
            }

            // Leads to registration page.
            Button(
                onClick = { context.startActivity(Intent(context, RegistrationPage::class.java)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Register"
                )
            }
        }
    }
}
