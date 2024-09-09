package com.example.myapplication5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication5.ui.theme.MyApplication5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication5Theme {
                // Aquí debes llamar a MainScreen y pasarle un NavHostController
                val navController = rememberNavController() // Crea un NavHostController
                MainScreen(navController) // Llama a MainScreen
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication5Theme {
        Greeting("Android")
    }
}

@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Perfil de Usuario", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Aquí puedes agregar una imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.semaforo),
            contentDescription = "Imagen de perfil",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nombre: Juan Pérez", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Correo: juan.perez@example.com", style = MaterialTheme.typography.bodyMedium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    // Estado para contar las presiones del botón
    val buttonPressCount = remember { mutableStateOf(0) }

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = {
            CustomFAB { buttonPressCount.value++ } // Incrementar el contador al hacer clic
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                CustomContent(innerPadding, buttonPressCount.value) // Pasar el conteo al contenido
            }
            composable("profile") {
                UserProfileScreen()
            }
            composable("build") {
                BuildScreen()
            }
            composable("menu") {
                MenuScreen()
            }
            composable("favorite") {
                FavoriteScreen()
            }
            composable("delete") {
                DeleteScreen()
            }
        }
    }
}

@Composable
fun CustomContent(padding: PaddingValues, buttonPressCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "My app content")
        Spacer(modifier = Modifier.height(16.dp))
        // Mostrar cuántas veces se ha presionado el botón
        Text(text = "Botón presionado: $buttonPressCount veces", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() } // Llama a la función pasada como parámetro
    ) {
        Text(
            fontSize = 24.sp, // Tamaño de fuente del texto del botón
            text = "+" // Texto del botón
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        title = { Text(text = "Sample Title") },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            }
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = null
                )
            }
        }
    )
}
@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar {
        IconButton(
            onClick = { navController.navigate("build") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.Build, contentDescription = "Build description")
        }
        IconButton(
            onClick = { navController.navigate("menu") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu description")
        }
        IconButton(
            onClick = { navController.navigate("favorite") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite description")
        }
        IconButton(
            onClick = { navController.navigate("delete") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete description")
        }
    }
}
@Composable
fun BuildScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Build Screen", style = MaterialTheme.typography.titleLarge)
        // Agrega más contenido aquí según sea necesario
    }
}

@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menu Screen", style = MaterialTheme.typography.titleLarge)
        // Agrega más contenido aquí según sea necesario
    }
}

@Composable
fun FavoriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorite Screen", style = MaterialTheme.typography.titleLarge)
        // Agrega más contenido aquí según sea necesario
    }
}

@Composable
fun DeleteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Delete Screen", style = MaterialTheme.typography.titleLarge)
        // Agrega más contenido aquí según sea necesario
    }
}