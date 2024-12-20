package pe.edu.upeu.asistenciaupeujcn.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.* // Importación de las pantallas de la aplicación
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login.LoginScreen

/**
 * Función composable que define el sistema de navegación de la aplicación.
 *
 * @param navController Controlador de navegación que maneja las rutas.
 * @param darkMode Estado mutable que controla el modo oscuro de la aplicación.
 * @param modif PaddingValues que representa los valores de padding alrededor del contenido.
 */
@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>,
    modif: PaddingValues
) {
    // Configuración del NavHost, que define las rutas y pantallas iniciales.
    NavHost(
        navController = navController,
        startDestination = Destinations.Login.route // Define la pantalla de login como la pantalla inicial por defecto
    ) {
        // Ruta para la pantalla de login.
        composable(Destinations.Login.route) {
            // Pantalla de login que navega a la pantalla principal (Pantalla1) al iniciar sesión correctamente.
            LoginScreen(navigateToHome = {
                navController.navigate(Destinations.Pantalla1.route)
            })
        }
        // Ruta para la Pantalla1.
        composable(Destinations.Pantalla1.route) {
            Pantalla1(
                navegarPantalla2 = { newText ->
                    // Navega a Pantalla2 con un parámetro de texto.
                    navController.navigate(Destinations.Pantalla2.createRoute(newText))
                }
            )
        }
        // Ruta para Pantalla2, que recibe un argumento "newText".
        composable(
            Destinations.Pantalla2.route,
            arguments = listOf(navArgument("newText") {
                defaultValue = "Pantalla 2" // Valor por defecto del argumento "newText".
            })
        ) { navBackStackEntry ->
            // Obtiene el valor del argumento "newText" de la navegación.
            val newText = navBackStackEntry.arguments?.getString("newText")
            requireNotNull(newText) // Asegura que el argumento no sea nulo.
            Pantalla2(newText, darkMode) // Renderiza Pantalla2 con el argumento y el modo oscuro.
        }
        // Ruta para Pantalla3.
        composable(Destinations.Pantalla3.route) {
            Pantalla3()
        }
        // Ruta para Pantalla4.
        composable(Destinations.Pantalla4.route) {
            Pantalla4()
        }
        // Ruta para Pantalla5.
        composable(Destinations.Pantalla5.route) {
            Pantalla5()
        }
    }
}
