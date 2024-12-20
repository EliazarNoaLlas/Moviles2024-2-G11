package pe.edu.upeu.asistenciaupeujcn.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

// Se define una clase sellada llamada 'Destinations' que representa diferentes pantallas (rutas)
// dentro de la aplicación. Al ser 'sealed', significa que sólo las subclases declaradas dentro de
// este archivo pueden heredar de 'Destinations'.
sealed class Destinations(
    val route: String,  // Define la ruta única que usará cada pantalla para la navegación
    val title: String,  // El título descriptivo de la pantalla
    val icon: ImageVector  // El ícono asociado a esa pantalla para ser mostrado en la UI
) {

    // Objeto para la pantalla de Login, que hereda de Destinations.
    // Tiene una ruta "login", un título "Login" y un ícono de configuración.
    object Login : Destinations(
        "login",  // Ruta de la pantalla de Login
        "Login",  // Título de la pantalla
        Icons.Filled.Settings  // Ícono de configuración
    )

    // Objeto para la Pantalla1, que hereda de Destinations.
    // Tiene una ruta "pantalla1", un título "Pantalla 1" y un ícono de inicio.
    object Pantalla1 : Destinations(
        "pantalla1",  // Ruta de la Pantalla 1
        "Pantalla 1",  // Título de la pantalla
        Icons.Filled.Home  // Ícono de la casa o inicio
    )

    // Objeto para Pantalla2. La diferencia aquí es que la ruta tiene un parámetro opcional llamado "newText".
    object Pantalla2 : Destinations(
        "pantalla2/?newText={newText}",  // Ruta que incluye un parámetro opcional "newText"
        "Pantalla 2",  // Título de la pantalla
        Icons.Filled.Settings  // Ícono de configuración
    ) {
        // Función que genera dinámicamente una ruta con el parámetro proporcionado "newText".
        fun createRoute(newText: String) =
            "pantalla2/?newText=$newText"  // Devuelve la ruta con el parámetro "newText"
    }

    // Objeto para la Pantalla3, con la ruta "pantalla3", título "Pantalla 3" y un ícono de favorito (corazón).
    object Pantalla3 : Destinations(
        "pantalla3",  // Ruta de la Pantalla 3
        "Pantalla 3",  // Título de la pantalla
        Icons.Filled.Favorite  // Ícono de favorito (corazón)
    )

    // Objeto para la Pantalla4, con la ruta "pantalla4", título "Pantalla 4x" y un ícono de cara.
    object Pantalla4 : Destinations(
        "pantalla4",  // Ruta de la Pantalla 4
        "Pantalla 4x",  // Título de la pantalla
        Icons.Filled.Face  // Ícono de cara (face)
    )

    // Objeto para la Pantalla5, con la ruta "pantalla5", título "Pantalla 5x" y un ícono de perfil (círculo con la cuenta).
    object Pantalla5 : Destinations(
        "pantalla5",  // Ruta de la Pantalla 5
        "Pantalla 5x",  // Título de la pantalla
        Icons.Filled.AccountCircle  // Ícono de perfil de cuenta
    )
}