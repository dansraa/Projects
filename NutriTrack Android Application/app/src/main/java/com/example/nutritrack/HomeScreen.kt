package com.example.nutritrack

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritrack.ui.theme.NutriTrackTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutritrack.data.foodIntake.FoodIntakeViewModel
import com.example.nutritrack.data.foodIntake.FoodIntakeViewModel.FoodIntakeViewModelFactory
import com.example.nutritrack.data.patient.PatientViewModel
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriTrackTheme {
                val navController: NavHostController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MyBottomBar(navController)
                    }
                )
                { innerPadding ->
                    // use column to place my nav host correctly within the scaffold.
                    // the padding is applied to ensure the content does not overlap with other ui elements.
                    // the inner padding parameter provides the necessary padding values.
                    Column(modifier = Modifier) {
                        // Calls my nav host composable to define the navigation graph.
                        MyNavHost(innerPadding, navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(modifier: Modifier = Modifier, navController: NavHostController) {

    // Setting Context
    val context = LocalContext.current

    // Initializing the ViewModel within the questionnaire.
    val viewModel: PatientViewModel = viewModel(
        factory = PatientViewModel.PatientViewModelFactory(context.applicationContext as Application)
    )

    // Passing login screen shared preferences into home screen for user id.
    val sharedPref = context.getSharedPreferences("NutriTrackPrefs", Context.MODE_PRIVATE)
    val savedUserId = sharedPref.getInt("LOGGED_IN_USER_ID", -1)


    LaunchedEffect(savedUserId) {
        if (savedUserId != -1) {
            viewModel.getTotalScore(savedUserId)
        }
    }

    // Setting the viewmodels query to a value for later use.
    val totalScore = viewModel.totalScore

    // Remembering the scroll state
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Top app bar title text
            TopAppBar(
                title = { Text("Hello, \n" + "User ID: $savedUserId",
                    textAlign = TextAlign.Start) }
            )

            // Edit button and edit description row
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){

                // Edit description and context
                Text(text = "You've already filled in your Food Intake \n" +
                "Questionnaire, but you can change details here:",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )

                // Button to return to quiz and edit selections
                Button(onClick = {
                    context.startActivity(Intent(context, FoodIntakeQuestion::class.java))
                }) {
                    Text(text = "Edit")
                }
            }

            // Healthy eating image
            Image(
                painter = painterResource(id = R.drawable.healthy_food),
                contentDescription = "NutriTrack Logo",
                modifier = Modifier.size(400.dp)
            )

            // My Score sub heading text row
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){

                Text(text = "My Score",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(10.dp),
                    fontWeight = FontWeight.Bold
                )

                TextButton(
                    onClick = { navController.navigate("insights") }
                ) {
                    Text(
                        "See all scores"
                    )
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = "seeScore"
                    )
                }
            }

            // Food quality score row
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Up Arrow",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )

                // Food quality score text
                Text(
                    text = "Your Food Quality score",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )


                // Display food scores based on viewmodel function.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.End
                ) {

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$totalScore/100",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Food Quality Score description
            Column(modifier = Modifier) {

                // Food quality score subheading
                Text(
                    text = "What is the Food Quality Score?",
                    modifier = Modifier
                        .padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                // Food quality description text
                Text(
                    text = "Your Food Quality Score provides a snapshot of how well your \n" +
                            "eating patterns align with established food guidelines, helping \n" +
                            "you identify both strengths and opportunities for improvement in your diet.",
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "This personalized measurement considers various food groups \n" +
                            "including vegetables, fruits, whole grains, and proteins to give \n" +
                            "you practical insights for making healthier food choices",
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 12.sp
                )
            }

        }
    }
}

// Nav host composable, responsible for navigation across the app.
@Composable
fun MyNavHost(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("home") { HomeScreenContent(modifier = Modifier, navController = navController) }
        composable("insights") { InsightScreen(modifier = Modifier, navController = navController) }
        composable("nutriCoach") { NutriCoach(modifier = Modifier) }
        composable("settings") { SettingScreen(modifier = Modifier, navController = navController) }
        composable("clinicianLogin") { ClinicianLogin(modifier = Modifier, navController = navController) }
        composable("adminView") { AdminView(modifier = Modifier) }
    }
}

@Composable
fun MyBottomBar(navController: NavHostController) {
    // state to track the currently selected item in the bottom nav bar
    var selectedItem by remember { mutableStateOf(0) }
    // list of navigation items
    var items = listOf(
        "Home",
        "Insights",
        "NutriCoach",
        "Settings"
    )
    // navigation bar composable to define the bottom navigation bar.
    NavigationBar {
        // iterate through each item in the list.
        items.forEachIndexed { index, item ->
            //define the icon based on the item's name
            NavigationBarItem(
                icon = {
                    when (item) {
                        // if the item is "home", display the home icon
                        "Home" -> Icon(Icons.Filled.Home, contentDescription = "Home")
                        // if the item is "reports", display email icon
                        "Insights" -> Icon(Icons.Filled.List, contentDescription = "Insights")
                        // if the item is "reports", display email icon
                        "NutriCoach" -> Icon(Icons.Filled.Email, contentDescription = "NutriCoach")
                        // if the item is "settings", display settings icon
                        "Settings" -> Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                },
                //display the items name as the label
                label = { Text(item) },


                //determine if the item is selected
                selected = selectedItem == index,
                //actions perform when the item is clicked
                onClick = {
                    selectedItem = index

                    navController.navigate(item)
                }
            )
        }
    }
}