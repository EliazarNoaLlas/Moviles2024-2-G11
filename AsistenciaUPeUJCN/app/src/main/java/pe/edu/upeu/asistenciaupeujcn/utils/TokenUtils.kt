package pe.edu.upeu.asistenciaupeujcn.utils

import android.content.Context

object TokenUtils {
    // Aquí se almacenará el token JWT recibido tras el login exitoso
    var TOKEN_CONTENT: String = ""

    // URL del servidor API, ajustada para que apunte a la ruta correcta
    const val API_URL = "http://10.0.2.2:8080/"

    // Contexto global de la aplicación
    lateinit var CONTEXTO_APPX: Context

    // Almacena el correo del usuario logueado
    var USER_LOGIN: String = ""
}