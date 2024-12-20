package pe.edu.upeu.asistenciaupeujcn.data.remote

import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioDto
import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// Interfaz que define la API remota para operaciones de usuario
interface RestUsuario {
    // Endpoint para iniciar sesión, enviando los datos del usuario en el cuerpo de la solicitud
    @POST("/asis/login")
    suspend fun login(@Body user: UsuarioDto): Response<UsuarioResp>
}