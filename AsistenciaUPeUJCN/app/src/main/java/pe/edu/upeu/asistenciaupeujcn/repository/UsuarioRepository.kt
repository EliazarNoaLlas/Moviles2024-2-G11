package pe.edu.upeu.asistenciaupeujcn.repository

import pe.edu.upeu.asistenciaupeujcn.data.remote.RestUsuario
import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioDto
import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioResp
import retrofit2.Response
import javax.inject.Inject

// Interfaz que define el repositorio para operaciones relacionadas con el usuario
interface UsuarioRepository {
    // Función para iniciar sesión, devolviendo la respuesta del servidor
    suspend fun loginUsuario(user: UsuarioDto): Response<UsuarioResp>
}

// Implementación de la interfaz de repositorio de usuario
class UsuarioRepositoryImp @Inject constructor(private val restUsuario: RestUsuario) : UsuarioRepository {
    // Llama a la función de inicio de sesión desde la API RestUsuario
    override suspend fun loginUsuario(user: UsuarioDto): Response<UsuarioResp> {
        return restUsuario.login(user)
    }
}