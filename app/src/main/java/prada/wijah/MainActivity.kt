package prada.wijah

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import prada.wijah.ui.theme.WijahTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WijahTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentScreen by remember { mutableStateOf("register") }

                    when (currentScreen) {
                        "register" -> ShowRegisterLayout(
                            modifier = Modifier.padding(innerPadding),
                            onRegisterClick = { currentScreen = "login" }
                        )
                        "login" -> ShowLoginLayout(
                            modifier = Modifier.padding(innerPadding),
                            onLoginClick = { currentScreen = "hal_1" }
                        )
                        "hal_1" -> ShowHal1Layout(
                            modifier = Modifier.padding(innerPadding),
                            onStartClick = { currentScreen = "hal_2" }
                        )
                        "hal_2" -> ShowHal2Layout(
                            modifier = Modifier.padding(innerPadding),
                            onLocationClick = { currentScreen = "hal_3" }
                        )
                        "hal_3" -> ShowHal3Layout(
                            modifier = Modifier.padding(innerPadding),
                            onImageClick = { currentScreen = "hal_4" }
                        )
                        "hal_4" -> ShowHal4Layout(
                            modifier = Modifier.padding(innerPadding),
                            onTopImageClick = { currentScreen = "hal_5" }
                        )
                        "hal_5" -> ShowHal5Layout(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun ShowRegisterLayout(modifier: Modifier = Modifier, onRegisterClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.register, null)
            val emailField: EditText = view.findViewById(R.id.et_email)
            val usernameField: EditText = view.findViewById(R.id.et_username)
            val passwordField: EditText = view.findViewById(R.id.et_password)
            val registerButton: Button = view.findViewById(R.id.btn_register)

            // Enable register button only when all fields are filled
            fun updateRegisterButtonState() {
                registerButton.isEnabled = emailField.text.isNotEmpty() &&
                        usernameField.text.isNotEmpty() &&
                        passwordField.text.isNotEmpty()
            }

            // Add listeners to fields
            emailField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateRegisterButtonState()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            usernameField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateRegisterButtonState()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            passwordField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateRegisterButtonState()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            // Login text click listener
            val loginTextView: TextView = view.findViewById(R.id.tv_login)
            loginTextView.setOnClickListener {
                onRegisterClick()
            }

            // Register button click listener
            registerButton.setOnClickListener {
                onRegisterClick()
            }

            updateRegisterButtonState() // Initial state
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowLoginLayout(modifier: Modifier = Modifier, onLoginClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.login, null)
            val loginButton: Button = view.findViewById(R.id.btn_login)
            loginButton.setOnClickListener {
                onLoginClick()
            }
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal1Layout(modifier: Modifier = Modifier, onStartClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_1, null)
            val startButton: Button = view.findViewById(R.id.btn_start)
            startButton.setOnClickListener {
                onStartClick()
            }
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal2Layout(modifier: Modifier = Modifier, onLocationClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_2, null)
            val locationButton: ImageButton = view.findViewById(R.id.btn_location_1)
            locationButton.setOnClickListener {
                onLocationClick()
            }
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal3Layout(modifier: Modifier = Modifier, onImageClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_3, null)
            val photo1: ImageView = view.findViewById(R.id.photo_1)
            photo1.setOnClickListener {
                onImageClick()
            }
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal4Layout(modifier: Modifier = Modifier, onTopImageClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_4, null)
            val topImage: ImageView = view.findViewById(R.id.top_image)
            topImage.setOnClickListener {
                onTopImageClick()
            }
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal5Layout(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.hal_5, null)
        },
        modifier = modifier.fillMaxSize()
    )
}
