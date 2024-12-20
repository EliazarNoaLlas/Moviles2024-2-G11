package pe.edu.upeu.asistenciaupeujcn.ui.presentation.screens.login

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.ErrorImageAuth
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.ImageLogin
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.ProgressBarLoading

import pe.edu.upeu.asistenciaupeujcn.ui.theme.LightRedColors
import pe.edu.upeu.asistenciaupeujcn.utils.ComposeReal
import pe.edu.upeu.asistenciaupeujcn.utils.TokenUtils

import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioDto
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.form.EmailTextField
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.form.LoginButton
import pe.edu.upeu.asistenciaupeujcn.ui.presentation.components.form.PasswordTextField
import pe.edu.upeu.asistenciaupeujcn.ui.theme.AsistenciaUPeUJCNTheme

/**
 * Pantalla de login que permite a los usuarios autenticarse.
 *
 * @param navigateToHome Función que se invoca al iniciar sesión correctamente, para navegar a la pantalla principal.
 * @param viewModel ViewModel que gestiona el estado y la lógica de autenticación.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    // Observa el estado de carga desde el ViewModel.
    val isLoading by viewModel.isLoading.observeAsState(false)
    // Observa si el login fue exitoso.
    val isLogin by viewModel.islogin.observeAsState(false)
    // Observa si ocurrió un error durante el login.
    val isError by viewModel.isError.observeAsState(false)
    // Observa el resultado del login.
    val loginResul by viewModel.listUser.observeAsState()
    // Corutina para manejar tareas asíncronas.
    val scope = rememberCoroutineScope()
    // Contexto de la aplicación, necesario para mostrar Toasts.
    val context = LocalContext.current

    // Contenedor principal de la UI de login.
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Muestra la imagen de login.
        ImageLogin()
        // Título de la pantalla de login.
        Text("Login Screen", fontSize = 40.sp)

        // Construcción del formulario de login usando EasyForms.
        BuildEasyForms { easyForm ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Campo de texto para el email.
                EmailTextField(easyForms = easyForm, text ="","E-Mail:", "U")
                // Campo de texto para la contraseña.
                PasswordTextField(easyForms = easyForm, text ="", label ="Password:")

                // Botón de login.
                LoginButton(easyForms = easyForm, onClick = {
                    // Obtiene los datos del formulario.
                    val dataForm = easyForm.formData()
                    // Crea un objeto UsuarioDto con los datos ingresados.
                    val user = UsuarioDto(
                        (dataForm.get(0) as EasyFormsResult.StringResult).value,
                        (dataForm.get(1) as EasyFormsResult.StringResult).value
                    )
                    // Llama al método de login en el ViewModel.
                    viewModel.loginSys(user)

                    // Inicia una corutina para manejar el proceso de login.
                    scope.launch {
                        delay(3600) // Espera 3.6 segundos (simula un proceso de autenticación).
                        if (isLogin && loginResul != null) {
                            // Si el login es exitoso, navega a la pantalla principal.
                            Log.i("TOKENV", TokenUtils.TOKEN_CONTENT)
                            Log.i("DATA", loginResul!!.dni)
                            navigateToHome.invoke()
                        } else {
                            // Muestra un mensaje de error si el login falla.
                            Log.v("ERRORX", "Error logeo")
                            Toast.makeText(context, "Error al conectar o loginResul es null", Toast.LENGTH_LONG).show()
                        }
                    }
                }, label = "Log In")

                // Componente adicional de la interfaz (puede ser una barra superior, etc.).
                ComposeReal.COMPOSE_TOP.invoke()
            }
        }
    }

    // Muestra una imagen de error si ocurrió un error durante el login.
    ErrorImageAuth(isImageValidate = isError)
    // Muestra un indicador de carga si el estado es de cargando.
    ProgressBarLoading(isLoading = isLoading)
}

/**
 * Previsualización de la pantalla de login para propósitos de diseño.
 */
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val colors = LightRedColors // Esquema de colores claro.
    val darkTheme = isSystemInDarkTheme() // Determina si el sistema está en modo oscuro.
    AsistenciaUPeUJCNTheme(colorScheme = colors) {
        // Renderiza la pantalla de login en el modo de previsualización.
        LoginScreen(
            navigateToHome = {}
        )
    }
}
