package pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioDto
import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioResp
import pe.edu.upeu.asistenciaupeujcn.repository.UsuarioRepository
import pe.edu.upeu.asistenciaupeujcn.utils.TokenUtils

import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: UsuarioRepository
) : ViewModel() {

    // LiveData que rastrea si se debe mostrar el indicador de carga
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData que rastrea si el usuario ha iniciado sesión
    private val _islogin: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val islogin: LiveData<Boolean> get() = _islogin

    // LiveData que rastrea si hay un error durante el inicio de sesión
    val isError = MutableLiveData<Boolean>(false)

    // LiveData que contiene la respuesta del usuario al iniciar sesión
    val listUser = MutableLiveData<UsuarioResp>()

    // Función que maneja el proceso de inicio de sesión usando las credenciales del usuario
    fun loginSys(toData: UsuarioDto) {
        Log.i("LOGIN", toData.correo)

        // Inicia una corrutina en el dispatcher IO para operaciones de red
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)  // Muestra el indicador de carga
            _islogin.postValue(false)   // Restablece el estado de inicio de sesión

            // Intenta iniciar sesión a través del repositorio
            val totek = userRepo.loginUsuario(toData).body()

            delay(1500L)  // Simula un retraso de red

            // Si el inicio de sesión es exitoso, almacena el token
            TokenUtils.TOKEN_CONTENT = "Bearer " + totek?.token
            Log.i("DATAXDMP", "Holas")

            // Publica los datos de respuesta del usuario en el LiveData
            listUser.postValue(totek!!)
            Log.i("DATAXDMP", TokenUtils.TOKEN_CONTENT)

            // Verifica si el token es válido
            if (TokenUtils.TOKEN_CONTENT != "Bearer null") {
                TokenUtils.USER_LOGIN = toData.correo  // Almacena el correo del usuario
                _islogin.postValue(true)  // Actualiza el estado de inicio de sesión a verdadero
            } else {
                isError.postValue(true)  // Actualiza el estado de error a verdadero
                delay(1500L)  // Simula un retraso antes de restablecer el estado de error
                isError.postValue(false)  // Restablece el estado de error
            }
            _isLoading.postValue(false)  // Oculta el indicador de carga
        }
    }
}