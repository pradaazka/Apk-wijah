package prada.wijah

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
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
import android.widget.RadioGroup

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
                        "profile" -> ShowProfileLayout(
                            modifier = Modifier.padding(innerPadding),
                            onBackClick = { currentScreen = "hal_2" }
                        )
                        "login" -> ShowLoginLayout(
                            modifier = Modifier.padding(innerPadding),
                            onLoginClick = { currentScreen = "hal_1" }
                        )
                        "hal_1" -> ShowHal1Layout(
                            modifier = Modifier.padding(innerPadding),
                            onStartClick = { destination ->
                                when (destination) {
                                    "Jawa Timur" -> currentScreen = "hal_2"
                                    "Jawa Tengah" -> currentScreen = "hal_jabar"
                                    "Jawa Barat" -> currentScreen = "hal_jateng"
                                }
                            }
                        )
                        "hal_2" -> ShowHal2Layout(
                            modifier = Modifier.padding(innerPadding),
                            onLocationClick = { currentScreen = "hal_3" },
                            onBackClick = { currentScreen = "hal_1" },
                            onProfileClick = { currentScreen = "profile" }
                        )
                        "hal_3" -> ShowHal3Layout(
                            modifier = Modifier.padding(innerPadding),
                            onImageClick = { currentScreen = "hal_4" },
                            onBackClick = { currentScreen = "hal_2" }
                        )
                        "hal_4" -> ShowHal4Layout(
                            modifier = Modifier.padding(innerPadding),
                            onLocationClick = { currentScreen = "hal_5" },
                            onBackClick = { currentScreen = "hal_3" }
                        )
                        "hal_jabar" -> ShowHaljatengLayout(
                            modifier = Modifier.padding(innerPadding),
                            onLocationClick = { currentScreen = "hal_3" },
                            onBackClick = { currentScreen = "hal_1" },
                            onProfileClick = { currentScreen = "profile" }
                        )
                        "hal_jateng" -> ShowHaljabarLayout(
                            modifier = Modifier.padding(innerPadding),
                            onLocationClick = { currentScreen = "hal_3" },
                            onBackClick = { currentScreen = "hal_1" },
                            onProfileClick = { currentScreen = "profile" }
                        )

                    }
                }
            }
        }
    }
}

// ShowHal3Layout now accepts an onBackClick parameter
@Composable
fun ShowHal3Layout(modifier: Modifier = Modifier, onImageClick: () -> Unit, onBackClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_3, null)
            val photo1: ImageView = view.findViewById(R.id.photo_1)
            val backButton: TextView = view.findViewById(R.id.back_button) // Find the back button

            photo1.setOnClickListener {
                onImageClick()
            }

            backButton.setOnClickListener {
                onBackClick() // Handle back click
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
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

            fun updateRegisterButtonState() {
                registerButton.isEnabled = emailField.text.isNotEmpty() &&
                        usernameField.text.isNotEmpty() &&
                        passwordField.text.isNotEmpty()
            }

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

            val loginTextView: TextView = view.findViewById(R.id.tv_login)
            loginTextView.setOnClickListener {
                onRegisterClick()
            }

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
fun ShowProfileLayout(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.profil, null)
            val backButton: TextView = view.findViewById(R.id.back_button)

            // Menangani klik tombol Kembali
            backButton.setOnClickListener {
                onBackClick() // Arahkan kembali ke hal_2
            }

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
            val usernameField: EditText = view.findViewById(R.id.username)
            val passwordField: EditText = view.findViewById(R.id.password)
            val loginButton: Button = view.findViewById(R.id.btn_login)

            // Fungsi untuk mengupdate status tombol login
            fun updateLoginButtonState() {
                loginButton.isEnabled = usernameField.text.isNotEmpty() && passwordField.text.isNotEmpty()
            }

            // Mengupdate status tombol ketika ada perubahan di kolom username
            usernameField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateLoginButtonState()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            // Mengupdate status tombol ketika ada perubahan di kolom password
            passwordField.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateLoginButtonState()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            // Menambahkan listener ke tombol login
            loginButton.setOnClickListener {
                if (usernameField.text.isNotEmpty() && passwordField.text.isNotEmpty()) {
                    onLoginClick() // Panggil onLoginClick() jika tombol dapat diklik
                }
            }

            // Inisialisasi status tombol login berdasarkan nilai input
            updateLoginButtonState()
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal1Layout(modifier: Modifier = Modifier, onStartClick: (String) -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_1, null)

            val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
            val startButton: Button = view.findViewById(R.id.btn_start)

            fun updateStartButtonState() {
                startButton.isEnabled = radioGroup.checkedRadioButtonId != -1
            }

            radioGroup.setOnCheckedChangeListener { _, _ ->
                updateStartButtonState()
            }

            startButton.setOnClickListener {
                if (radioGroup.checkedRadioButtonId != -1) {
                    val selectedRadioButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                    val destination = selectedRadioButton.text.toString() // Ambil teks dari RadioButton yang dipilih
                    onStartClick(destination) // Kirim teks ke fungsi onStartClick
                }
            }

            updateStartButtonState()
            view
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ShowHal2Layout(modifier: Modifier = Modifier, onLocationClick: () -> Unit, onBackClick: () -> Unit, onProfileClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_2, null)
            val locationButton: ImageButton = view.findViewById(R.id.btn_location_1)
            val backButton: TextView = view.findViewById(R.id.back_button)
            val profileButton: ImageButton = view.findViewById(R.id.btn_profile)  // Tombol profile

            locationButton.setOnClickListener {
                onLocationClick() // Navigate to hal_3
            }

            backButton.setOnClickListener {
                onBackClick() // Handle back click to navigate to hal_1
            }

            profileButton.setOnClickListener {
                onProfileClick() // Navigate to profile screen
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}



@Composable
fun ShowHal4Layout(
    modifier: Modifier = Modifier,
    onLocationClick: () -> Unit, // Hanya tambahkan parameter ini untuk Lihat Lokasi
    onBackClick: () -> Unit
) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_4, null)
            val backButton: TextView = view.findViewById(R.id.back_button)
            val btnLihatLokasi: Button = view.findViewById(R.id.btn_lihat_lokasi) // Ambil tombol Lihat Lokasi

            backButton.setOnClickListener {
                onBackClick()
            }

            btnLihatLokasi.setOnClickListener {
                onLocationClick() // Navigasi ke hal_5 saat tombol Lihat Lokasi ditekan
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}
@Composable
fun ShowHaljatengLayout(modifier: Modifier = Modifier, onLocationClick: () -> Unit, onBackClick: () -> Unit, onProfileClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_jateng, null)
            val locationButton: ImageButton = view.findViewById(R.id.btn_location_1)
            val backButton: TextView = view.findViewById(R.id.back_button)
            val profileButton: ImageButton = view.findViewById(R.id.btn_profile)  // Tombol profile

            locationButton.setOnClickListener {
                onLocationClick() // Navigate to hal_3
            }

            backButton.setOnClickListener {
                onBackClick() // Handle back click to navigate to hal_1
            }

            profileButton.setOnClickListener {
                onProfileClick() // Navigate to profile screen
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}
@Composable
fun ShowHaljabarLayout(modifier: Modifier = Modifier, onLocationClick: () -> Unit, onBackClick: () -> Unit, onProfileClick: () -> Unit) {
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.hal_jabar, null)
            val locationButton: ImageButton = view.findViewById(R.id.btn_location_1)
            val backButton: TextView = view.findViewById(R.id.back_button)
            val profileButton: ImageButton = view.findViewById(R.id.btn_profile)  // Tombol profile

            locationButton.setOnClickListener {
                onLocationClick() // Navigate to hal_3
            }

            backButton.setOnClickListener {
                onBackClick() // Handle back click to navigate to hal_1
            }

            profileButton.setOnClickListener {
                onProfileClick() // Navigate to profile screen
            }

            view
        },
        modifier = modifier.fillMaxSize()
    )
}

