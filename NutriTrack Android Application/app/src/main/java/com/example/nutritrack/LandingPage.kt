package com.example.nutritrack

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nutritrack.data.NutriTrackDatabase
import com.example.nutritrack.data.patient.PatientViewModel
import com.example.nutritrack.ui.theme.NutriTrackTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.jvm.java

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val patientViewModel: PatientViewModel = ViewModelProvider(
            this, PatientViewModel.PatientViewModelFactory(this@MainActivity)
        ) [PatientViewModel::class.java]

        // Coroutine to run the insert all function, to insert all
        // CSV data into the database.
        lifecycleScope.launch(Dispatchers.IO) {
            patientViewModel.readCSVAndInsert(this@MainActivity)
        }

        // Check logged in save state.
        val sharedPref = getSharedPreferences("NutriTrackPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", true)
        val userId = sharedPref.getInt("LOGGED_IN_USER_ID", -1)

        if (isLoggedIn && userId != -1) {
            // Redirect straight to home page or dashboard
            startActivity(Intent(this, HomeScreen::class.java))
            finish()
        } else {
            // Stay on LoginPage
        }

        enableEdgeToEdge()
        setContent {
            NutriTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LandingPort(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun LandingPort(modifier: Modifier = Modifier) {

    val context = LocalContext.current


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(128.dp))

            // App title with bold font weight applied.
            Text(
                text = "NutriTrack",
                fontSize = 40.sp,
                //applying bold font weight to the title text.
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            // NutriTrack Logo
            Image(
                painter = painterResource(id = R.drawable.nutritrack_logo),
                contentDescription = "NutriTrack Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Landing pages user discretion text.
            Text(
                text = "This app provides general health and nutrition information for " +
                        "educational purposes only. It is not intended as medical advice, " +
                        "diagnosis, or treatment. Always consult a qualified healthcare " +
                        "professional before making any changes to your diet, exercise, or " +
                        "health regimen. " +
                        "Use this app at your own risk. " +
                        "If you’d like to an Accredited Practicing Dietitian (APD), please " +
                        "visit the Monash Nutrition/Dietetics Clinic. \n" +
                        "(discounted rates for students) \n" +
                        "https://www.monash.edu/medicine/scs/nutrition/clinics/nutrition ",
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), //Makes text stretch to its full width
                textAlign = TextAlign.Center
            )

            // Button to move to login page.
            Button(
                onClick = {
                    // Declaring intent to move to LoginPage
                    context.startActivity(Intent(context,LoginPage::class.java))
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Student id text
            Text(
                text = "Designed by Daniel Serra (334365576)",
                fontSize = 12.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}