package com.example.c37a

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.c37a.ui.theme.C37ATheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardBody() {

    val context = LocalContext.current
    val activity = context as Activity

    val email = activity.intent.getStringExtra("email")
    val password = activity.intent.getStringExtra("password")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Ecommerce")},
                navigationIcon = {
                    IconButton(onClick = {
                        activity.finish()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_visibility_24),
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_more_horiz_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("Email: $email")
            Text("Password: $password")
        }
    }
}