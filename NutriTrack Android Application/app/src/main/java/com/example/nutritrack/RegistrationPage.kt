package com.example.nutritrack

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.nutritrack.MainActivity
import com.example.nutritrack.data.patient.PatientViewModel
import com.example.nutritrack.ui.theme.NutriTrackTheme
import kotlin.math.exp

@ExperimentalMaterial3Api
class RegistrationPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val patientViewModel: PatientViewModel = ViewModelProvider(
            this, PatientViewModel.PatientViewModelFactory(this@RegistrationPage)
        ) [PatientViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            NutriTrackTheme {
                Registration(patientViewModel)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Registration(patientViewModel: PatientViewModel) {

    // Local context
    val context = LocalContext.current

    // Textfield variables
    var patientName = remember { mutableStateOf("") }
    var phoneNo = remember { mutableStateOf("") }
    var patientPass = remember { mutableStateOf("") }
    var confirmPass = remember { mutableStateOf("") }

    // userID list
    val userIdList by patientViewModel.allUserId.collectAsState(initial = emptyList())
    val expanded = remember {mutableStateOf(false)}
    val selectedId = remember {mutableStateOf("")}

    // Potentially an issue if reinputting data into an existing column and user id is a string.
    val userIds = userIdList.map { it.toString() } // mapping the user ids to strings, potentially an issue for string.

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Account Registration",
                        modifier = Modifier.padding(horizontal = 36.dp)
                    )
                },

                navigationIcon = {
                    IconButton(onClick = {context.startActivity(Intent(context, LoginPage::class.java))}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "returnMenu"
                        )
                    }
                }
            )
        }
    ) {
        innerPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

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
                        .padding(horizontal = 32.dp)
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

            OutlinedTextField(
                value = phoneNo.value,
                onValueChange = { phoneNo.value = it },
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = patientName.value,
                onValueChange = { patientName.value = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = patientPass.value,
                onValueChange = { patientPass.value = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = confirmPass.value,
                onValueChange = { confirmPass.value = it },
                label = { Text("Confirm Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(vertical = 16.dp)
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                "This app is only for pre-registered users. Please enter your ID, " +
                "phone number and password to claim your account.",
                modifier = Modifier.padding(horizontal = 32.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // Need to fix error handling messages to accurately respond to
            // If phone number matches with user id etc.
            Button(
                onClick = {
                    val uid = selectedId.value.toIntOrNull()
                    val phone = phoneNo.value
                    val name = patientName.value
                    val password = patientPass.value
                    val confirm = confirmPass.value

                    if (confirm != password) {
                        Toast.makeText(context, "Confirm password and password do not match.", Toast.LENGTH_SHORT).show()
                    } else {
                        if (uid != null && phone.isNotBlank() && name.isNotBlank() && password.isNotBlank()) {
                            patientViewModel.updateAccount(uid, phone, name, password)
                            Toast.makeText(context, "Account successfully registered!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Fields not completed, please fill in required fields.", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    "Register"
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = { context.startActivity(Intent(context, LoginPage::class.java)) },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    "Login"
                )
            }

        }
    }
}