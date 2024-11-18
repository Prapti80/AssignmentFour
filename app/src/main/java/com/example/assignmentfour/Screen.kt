package com.example.assignmentfour
import android.annotation.SuppressLint
import android.widget.ExpandableListView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignmentfour.ui.theme.FamilyExpandableAdapter
import com.example.assignmentfour.ui.theme.FamilyMember

@Composable
fun FamilyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController) }
        composable("custom_list") { CustomListViewScreen() }
        composable("expandable_list") { ExpandableListViewScreen() }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Family App") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("custom_list") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Custom ListView")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("expandable_list") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Expandable ListView")
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomListViewScreen() {
    val familyMembers = listOf(
        FamilyMember("Pranto", R.drawable.pranto),
        FamilyMember("Prapti", R.drawable.prapti),
        FamilyMember("Bonna", R.drawable.bonna)
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Custom ListView") })
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Custom ListView") })
            }
        ) { paddingValues -> // Use paddingValues to avoid overlapping
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply padding from Scaffold
                    .padding(16.dp) // Additional padding for aesthetics
            ) {
                items(familyMembers.size) { index ->
                    CustomListItem(familyMembers[index])
                }
            }
        }
    }}
@Composable
fun CustomListItem(member: FamilyMember) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = member.imageRes),
                contentDescription = member.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = member.name,
                fontSize = 18.sp
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableListViewScreen() {
    val familyNames = listOf("Pranto", "Prapti", "Bonna")
    val familyDetails = mapOf(
        "Pranto" to listOf("Loves painting", "Enjoys traveling"),
        "Prapti" to listOf("Great at cooking", "Enjoys gardening"),
        "Bonna" to listOf("Tech enthusiast", "Loves coding")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Expandable ListView") })
        }
    ) { paddingValues -> // Add paddingValues
        AndroidView(
            factory = { context ->
                ExpandableListView(context).apply {
                    setAdapter(FamilyExpandableAdapter(context, familyNames, familyDetails))
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Apply padding from Scaffold
        )
    }
}

