import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.ui.unit.Dp

@Composable
fun FormularioAnimado() {
    // Estados para almacenar los textos ingresados
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    // Estado para manejar la visibilidad del formulario y el mensaje
    var isRegisteredVisible by remember { mutableStateOf(false) }

    // Estado temporal para manejar el color de fondo
    var isTemporaryColor by remember { mutableStateOf(false) }

    // Estado para controlar si el formulario debe cambiar de tamaño
    var isFormSubmitted by remember { mutableStateOf(false) }

    // Color de fondo animado que cambia según el estado de envío
    val backgroundColor by animateColorAsState(
        targetValue = if (isTemporaryColor) Color(0xFFA5D6A7) else Color.White,
        animationSpec = tween(durationMillis = 1500) // Duración de la transición
    )

    // Tamaño animado del formulario (se reduce cuando se envía)
    val formHeight: Dp by animateDpAsState(
        targetValue = if (isFormSubmitted) 200.dp else 400.dp,
        animationSpec = tween(durationMillis = 1000) // Duración de la animación de tamaño
    )

    // Desplazamiento animado del formulario (se mueve hacia arriba al enviarse)
    val offsetY: Dp by animateDpAsState(
        targetValue = if (isFormSubmitted) (-100).dp else 0.dp,
        animationSpec = tween(durationMillis = 1000) // Duración de la animación de desplazamiento
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(formHeight) // Aplica la altura animada
            .offset(y = offsetY) // Aplica el desplazamiento vertical animado
            .background(backgroundColor), // Se aplicará el color de fondo animado
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campos de texto (se ocultan al enviar)
        AnimatedVisibility(visible = !isFormSubmitted) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Texto de encabezado
                Text("Coloque sus datos:", textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    label = { Text("Apellido") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Botón "Enviar"
                Button(onClick = {
                    // Activa el modo enviado, el color temporal y la visibilidad del mensaje
                    isFormSubmitted = true
                    isTemporaryColor = true
                    isRegisteredVisible = true
                }) {
                    Text(text = "Enviar")
                }
            }
        }

        // Texto de encabezado
        Text("Animacion", textAlign = TextAlign.Left)
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de confirmación visible tras el envío
        AnimatedVisibility(
            visible = isRegisteredVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "Formulario enviado. Pronto se contactará con usted Sr/Sra. $nombre $apellido",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Efecto para restablecer el color después de 3 segundos
        LaunchedEffect(isTemporaryColor) {
            if (isTemporaryColor) {
                delay(3000) // Espera 3 segundos
                isTemporaryColor = false // Vuelve el color al original automáticamente
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioAnimadoPreview() {
    FormularioAnimado()
}
