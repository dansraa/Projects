package com.example.nutritrack

import android.app.Activity
import android.app.Application
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritrack.data.foodIntake.FoodIntake
import com.example.nutritrack.data.foodIntake.FoodIntakeViewModel
import com.example.nutritrack.data.foodIntake.FoodIntakeViewModel.FoodIntakeViewModelFactory
import com.example.nutritrack.ui.theme.ui.theme.NutriTrackTheme
import java.util.Calendar

class FoodIntakeQuestion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NutriTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Questionnaire(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Questionnaire (modifier: Modifier = Modifier) {

    // Setting context
    val context = LocalContext.current
    val mContext = LocalContext.current

    // Initializing the ViewModel within the questionnaire.
    val viewModel: FoodIntakeViewModel = viewModel(
        factory = FoodIntakeViewModelFactory(context.applicationContext as Application)
    )

    // Shared preferences passing userid from login screen.
    val sharedPref = context.getSharedPreferences("NutriTrackPrefs", Context.MODE_PRIVATE)
    val savedUserId = sharedPref.getInt("LOGGED_IN_USER_ID", -1)

    // Time picker state variables
    val mTime = remember { mutableStateOf("") }
    val mTime2 = remember { mutableStateOf("") }
    val mTime3 = remember { mutableStateOf("") }
    var mTimePickerDialog = timePickerFun(mTime)
    var mTimePickerDialog2 = timePickerFun(mTime2)
    var mTimePickerDialog3 = timePickerFun(mTime3)

    // Dropdown variables
    // the boolean state for whether the clickable ID textfield is open or not.
    var expanded by remember { mutableStateOf(false) }

    // Grey text within dropdown text field for user context
    val selectedOption = remember { mutableStateOf("Select an option") }

    // Persona dropdown list options
    val personas = listOf("Health Devotee", "Mindful Eater", "Wellness Striver", "Balance Seeker", "Health Procrastinator", "Food Carefree")

    // Modal related variables
    var showDialogHd by remember { mutableStateOf(false) }
    var showDialogBs by remember { mutableStateOf(false) }
    var showDialogMe by remember { mutableStateOf(false) }
    var showDialogHp by remember { mutableStateOf(false) }
    var showDialogWs by remember { mutableStateOf(false) }
    var showDialogFc by remember { mutableStateOf(false) }


    // Checkbox state variables
    val mCheckBoxStateFruit = remember { mutableStateOf(false) }
    val mCheckBoxStateMeat = remember { mutableStateOf(false) }
    val mCheckBoxStateFish = remember { mutableStateOf(false) }
    val mCheckBoxStateVegetables = remember { mutableStateOf(false) }
    val mCheckBoxStateSeafood = remember { mutableStateOf(false) }
    val mCheckBoxStateEggs = remember { mutableStateOf(false) }
    val mCheckBoxStateGrains = remember { mutableStateOf(false) }
    val mCheckBoxStatePoultry = remember { mutableStateOf(false) }
    val mCheckBoxStateNuts = remember { mutableStateOf(false) }

    val sharedPrefQuiz =
        mContext.getSharedPreferences("foodintakesp", Context.MODE_PRIVATE)

    val loadedCheckboxFruit = sharedPrefQuiz.getBoolean("checkboxfruit", false)
    val loadedCheckBoxMeat = sharedPrefQuiz.getBoolean("checkboxmeat", false)
    val loadedCheckBoxFish = sharedPrefQuiz.getBoolean("checkboxfish", false)
    val loadedCheckBoxVege = sharedPrefQuiz.getBoolean("checkboxvege", false)
    val loadedCheckBoxSea = sharedPrefQuiz.getBoolean("checkboxsea", false)
    val loadedCheckBoxEgg = sharedPrefQuiz.getBoolean("checkboxegg", false)
    val loadedCheckBoxGrain = sharedPrefQuiz.getBoolean("checkboxgrain", false)
    val loadedCheckBoxPoultry = sharedPrefQuiz.getBoolean("checkboxpoultry", false)
    val loadedCheckBoxNuts = sharedPrefQuiz.getBoolean("checkboxnuts", false)

    val loadedTime = sharedPrefQuiz.getString("time", "12:00")
    val loadedTime2 = sharedPrefQuiz.getString("time2", "12:00")
    val loadedTime3 = sharedPrefQuiz.getString("time3", "12:00")

    selectedOption.value = sharedPrefQuiz.getString("dropdown", "Select an option") ?: "Select an option"

    mCheckBoxStateFruit.value = loadedCheckboxFruit
    mCheckBoxStateMeat.value = loadedCheckBoxMeat
    mCheckBoxStateFish.value = loadedCheckBoxFish
    mCheckBoxStateVegetables.value = loadedCheckBoxVege
    mCheckBoxStateSeafood.value = loadedCheckBoxSea
    mCheckBoxStateEggs.value = loadedCheckBoxEgg
    mCheckBoxStateGrains.value = loadedCheckBoxGrain
    mCheckBoxStatePoultry.value = loadedCheckBoxPoultry
    mCheckBoxStateNuts.value = loadedCheckBoxNuts

    mTime.value = loadedTime.toString()
    mTime2.value = loadedTime2.toString()
    mTime3.value = loadedTime3.toString()

    // Remember scroll state
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Top App Bar with back button to return to login screen and
            // title text
            CenterAlignedTopAppBar(
                title = { Text("Food Intake Questionnaire") },
                navigationIcon = {
                    IconButton(onClick = { (context as Activity).finish() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Sub title for checkbox section
            Text(
                text = "Tick all the food categories you can eat.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Row of 3 columns aligned for questionnaire
            Row(verticalAlignment = Alignment.CenterVertically) {

                // Left Checkbox Column
                Column(
                    horizontalAlignment = Alignment.Start
                ) {

                    // Fruits Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateFruit.value,
                            onCheckedChange = {mCheckBoxStateFruit.value = it})
                        Text("Fruits")
                    }

                    //horizontal spacing for side by side checkbox
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    // Red Meat Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateMeat.value,
                            onCheckedChange = {mCheckBoxStateMeat.value = it})
                        Text("Red Meat")
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    // Fish Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateFish.value,
                            onCheckedChange = {mCheckBoxStateFish.value = it})
                        Text("Fish")
                    }
                }

                // Middle Checkbox Column
                Column(
                    horizontalAlignment = Alignment.Start
                ) {

                    // Vegetables Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateVegetables.value,
                            onCheckedChange = {mCheckBoxStateVegetables.value = it})
                        Text("Vegetables")
                    }

                    //horizontal spacing for side by side checkbox
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    // Seafood Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateSeafood.value,
                            onCheckedChange = {mCheckBoxStateSeafood.value = it})
                        Text("Seafood")
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    // Eggs Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateEggs.value,
                            onCheckedChange = {mCheckBoxStateEggs.value = it})
                        Text("Eggs")
                    }
                }

                // Right Checkbox Column
                Column(
                    horizontalAlignment = Alignment.Start
                ) {

                    // Grains Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateGrains.value,
                            onCheckedChange = {mCheckBoxStateGrains.value = it})
                        Text("Grains")
                    }

                    //horizontal spacing for side by side checkbox
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    // Poultry Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStatePoultry.value,
                            onCheckedChange = {mCheckBoxStatePoultry.value = it})
                        Text("Poultry")
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    // Nuts/Seeds Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = mCheckBoxStateNuts.value,
                            onCheckedChange = {mCheckBoxStateNuts.value = it})
                        Text("Nuts & Seeds")
                    }
                }
            }

            // End of checkbox questionnaire.
            Spacer(modifier = Modifier.height(24.dp))

            // Persona subtitle text
            Text(
                text = "Your Persona",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            // Persona modal description text
            Text(
                text = "People can be broadly classified into 6 different types based on\n" +
                        "their eating preferences. Click on each button below to find out \n" +
                        "the different types and select the type that bests fits you!",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Persona modals aligned with 3 columns inside a row
            Row(verticalAlignment = Alignment.CenterVertically) {

                // Column 1
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(0.9f)
                ) {
                    // Health Devotee Modal Button
                    Button(onClick = { showDialogHd = true }) {
                        Text("Health Devotee",
                            fontSize = 9.sp)
                        if (showDialogHd) {
                            AlertDialog(
                                onDismissRequest = { showDialogHd = false },
                                title = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.health_devotee),
                                            contentDescription = "Health Icon",
                                            modifier = Modifier
                                                .size(128.dp)
                                                .padding(bottom = 8.dp)
                                        )
                                        Text(
                                            "Health Devotee",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                text = {
                                    Column {
                                        Text(
                                            text = "I'm passionate about healthy eating & health plays a big part in my life. " +
                                                    "I use social media to follow active lifestyle personalities or get new recipes/exercise ideas. " +
                                                    "I may even buy superfoods or follow a a particular type of diet. I like to think I am super healthy.",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                confirmButton = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Button(onClick = {
                                            showDialogHd = false
                                        }) {
                                            Text("Dismiss")
                                        }
                                    }
                                }
                            )
                        }
                    }

                    // Balance Seeker Modal Button
                    Button(onClick = { showDialogBs = true }) {
                        Text("Balance Seeker",
                            fontSize = 9.sp)
                        if (showDialogBs) {
                            AlertDialog(
                                onDismissRequest = { showDialogBs = false },
                                title = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.balanced_eating),
                                            contentDescription = "Health Icon",
                                            modifier = Modifier
                                                .size(128.dp)
                                                .padding(bottom = 8.dp)
                                        )
                                        Text(
                                            "Balance Seeker",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                text = {
                                    Column {
                                        Text(
                                            text = "I try and live a balanced lifestyle, and I think that " +
                                                    "all foods are okay in moderation. I shouldn’t have to feel guilty " +
                                                    "about eating a piece of cake now and again. I get all sorts of inspiration " +
                                                    "from social media like finding out about new restaurants, fun recipes and " +
                                                    "sometimes healthy eating tips.",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                confirmButton = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Button(onClick = {
                                            showDialogBs = false
                                        }) {
                                            Text("Dismiss")
                                        }
                                    }
                                }
                            )
                        }
                    }
                }

                // Column 2
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    // Mindful Eater Modal Button
                    Button(onClick = { showDialogMe = true }) {
                        Text("Mindful Eater",
                            fontSize = 9.sp)
                        if (showDialogMe) {
                            AlertDialog(
                                onDismissRequest = { showDialogMe = false },
                                title = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.mindful_eater),
                                            contentDescription = "Mindful Eater Icon",
                                            modifier = Modifier
                                                .size(128.dp)
                                                .padding(bottom = 8.dp)
                                        )
                                        Text(
                                            "Mindful Eater",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                text = {
                                    Column {
                                        Text(
                                            text = "I’m health-conscious and being healthy and eating healthy is " +
                                                    "important to me. Although health means different things to different " +
                                                    "people, I make conscious lifestyle decisions about eating based on what " +
                                                    "I believe healthy means. I look for new recipes and healthy eating " +
                                                    "information on social media.",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                confirmButton = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Button(onClick = {
                                            showDialogMe = false
                                        }) {
                                            Text("Dismiss")
                                        }
                                    }
                                }
                            )
                        }
                    }

                    // Health Procrastinator Modal Button
                    Button(onClick = { showDialogHp = true },
                        modifier = Modifier
                            .wrapContentWidth()
                    ) {
                        Text(
                            "Health Procrastinator",
                            fontSize = 9.5.sp,
                            maxLines = 1, // Keeps text on a single line
                            softWrap = false, // Prevents text from wrapping
                            textAlign = TextAlign.Center, // Ensures it is centered properly
                            modifier = Modifier.padding(horizontal = 0.dp),
                            letterSpacing = (-0.2).sp
                        )
                        if (showDialogHp) {
                            AlertDialog(
                                onDismissRequest = { showDialogHp = false },
                                title = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.health_procrastinator),
                                            contentDescription = "Health Procrastinator Icon",
                                            modifier = Modifier
                                                .size(128.dp)
                                                .padding(bottom = 8.dp)
                                        )
                                        Text(
                                            "Health Procrastinator",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                text = {
                                    Column {
                                        Text(
                                            text = "\tI’m contemplating healthy eating but it’s not a priority " +
                                                    "for me right now. I know the basics about what it means to be " +
                                                    "healthy, but it doesn’t seem relevant to me right now. I have taken a " +
                                                    "few steps to be healthier but I am not motivated to make it a high " +
                                                    "priority because I have too many other things going on in my life.",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                confirmButton = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Button(onClick = {
                                            showDialogHp = false
                                        }) {
                                            Text("Dismiss")
                                        }
                                    }
                                }
                            )
                        }
                    }
                }

                // Column 3
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    // Wellness Striver Modal Button
                    Button(onClick = { showDialogWs = true }) {
                        Text("Wellness Striver",
                            fontSize = 9.sp)
                        if (showDialogWs) {
                            AlertDialog(
                                onDismissRequest = { showDialogWs = false },
                                title = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.wellness_eating),
                                            contentDescription = "Wellness Striver Icon",
                                            modifier = Modifier
                                                .size(128.dp)
                                                .padding(bottom = 8.dp)
                                        )
                                        Text(
                                            "Wellness Striver",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                text = {
                                    Column {
                                        Text(
                                            text = "I aspire to be healthy (but struggle sometimes). " +
                                                    "Healthy eating is hard work! I’ve tried to improve my diet, " +
                                                    "but always find things that make it difficult to stick with " +
                                                    "the changes. Sometimes I notice recipe ideas or healthy eating " +
                                                    "hacks, and if it seems easy enough, I’ll give it a go.",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                confirmButton = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Button(onClick = {
                                            showDialogWs = false
                                        }) {
                                            Text("Dismiss")
                                        }
                                    }
                                }
                            )
                        }
                    }

                    // Food Carefree Modal Button
                    Button(onClick = { showDialogFc = true }) {
                        Text("Food Carefree",
                            fontSize = 9.sp)
                        if (showDialogFc) {
                            AlertDialog(
                                onDismissRequest = { showDialogFc = false },
                                title = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.food_care),
                                            contentDescription = "Food Carefree Icon",
                                            modifier = Modifier
                                                .size(128.dp)
                                                .padding(bottom = 8.dp)
                                        )
                                        Text(
                                            "Food Carefree",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                text = {
                                    Column {
                                        Text(
                                            text = "I’m not bothered about healthy eating. I don’t really " +
                                                    "see the point and I don’t think about it. I don’t really " +
                                                    "notice healthy eating tips or recipes and I don’t care what I eat.",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                confirmButton = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Button(onClick = {
                                            showDialogFc = false
                                        }) {
                                            Text("Dismiss")
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Dropdown Menu Subheading Text
            Text(
                text = "Which persona best fits you?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            // Drop down menu containing persona types to select
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //Selecting the persona type
                OutlinedTextField(
                    value = selectedOption.value,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable{ expanded = !expanded }
                )

                // When outlined text field is clicked, dropdown menu appears
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                        .zIndex(10f)

                ) { // Goes through the list of personas and displays them within the dropdown menu
                    personas.forEach { id ->
                        DropdownMenuItem(
                            text = { Text(id) },
                            onClick = {
                                selectedOption.value = id
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Timings Subheading Text
            Text(
                text = "Timings",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            // Timings row 1
            Row(modifier = Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "What time of day approx. do you \n" +
                            "normally eat your biggest meal?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                OutlinedTextField(
                    value = mTime.value,
                    onValueChange = {},
                    readOnly = true,  // Prevent manual input
                    enabled = false,
                    label = { Text("00:00") },
                    modifier = Modifier
                        .clickable { mTimePickerDialog.show() }
                        .padding(16.dp)
                )
            }

            // Timings row 2
            Row(modifier = Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "What time of day approx. do you \n" +
                            "go to sleep at night?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                OutlinedTextField(
                    value = mTime2.value,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text("00:00") },
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { mTimePickerDialog2.show() }
                )
            }

            // Timings row 3
            Row(modifier = Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "What time of day approx. do you \n" +
                            "wake up in the morning?",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                OutlinedTextField(
                    value = mTime3.value,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text("00:00") },
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { mTimePickerDialog3.show() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Test database save
            Button(onClick = {

                val sharedPref =
                    mContext.getSharedPreferences("foodintakesp", Context.MODE_PRIVATE)
                        .edit()

                // dropdown shared pref
                sharedPref.putString("dropdown", selectedOption.value)

                //checkbox shared prefs
                sharedPref.putBoolean("checkboxfruit", mCheckBoxStateFruit.value)
                sharedPref.putBoolean("checkboxmeat", mCheckBoxStateMeat.value)
                sharedPref.putBoolean("checkboxfish", mCheckBoxStateFish.value)
                sharedPref.putBoolean("checkboxvege", mCheckBoxStateVegetables.value)
                sharedPref.putBoolean("checkboxsea", mCheckBoxStateSeafood.value)
                sharedPref.putBoolean("checkboxegg", mCheckBoxStateEggs.value)
                sharedPref.putBoolean("checkboxgrain", mCheckBoxStateGrains.value)
                sharedPref.putBoolean("checkboxpoultry", mCheckBoxStatePoultry.value)
                sharedPref.putBoolean("checkboxnuts", mCheckBoxStateNuts.value)

                //time shared prefs
                sharedPref.putString("time", mTime.value)
                sharedPref.putString("time2", mTime2.value)
                sharedPref.putString("time3", mTime3.value)
                sharedPref.apply()

                // Move to next screen "Home Screen"
                context.startActivity(Intent(context, HomeScreen::class.java))

                // Saves input into foodIntakeQuiz table.
                val response = FoodIntake(
                    eatFruit = mCheckBoxStateFruit.value,
                    eatMeat = mCheckBoxStateMeat.value,
                    eatFish = mCheckBoxStateFish.value,
                    eatVege = mCheckBoxStateVegetables.value,
                    eatSeafood = mCheckBoxStateSeafood.value,
                    eatEggs = mCheckBoxStateEggs.value,
                    eatGrains = mCheckBoxStateGrains.value,
                    eatPoultry = mCheckBoxStatePoultry.value,
                    eatNutSeed = mCheckBoxStateNuts.value,
                    selectedPersona = selectedOption.value,
                    timeOne = mTime.value,
                    timeTwo = mTime2.value,
                    timeThree = mTime3.value,
                    quizUserId = savedUserId
                )
                    viewModel.insertResponse(response)
            }) {
                Text("Save")
            }
        }
    }
}

// Time picker function for timings
@Composable
fun timePickerFun(mTime: MutableState<String>) : TimePickerDialog {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()

    val mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
    val mMinute = mCalendar.get(Calendar.MINUTE)

    mCalendar.time = Calendar.getInstance().time

    return TimePickerDialog(
        mContext,
        { _, selectedHour: Int, selectedMinute: Int ->
            mTime.value = String.format("%02d:%02d", selectedHour, selectedMinute)
        },
        mHour,
        mMinute,
        true // 24-hour format
    )
}