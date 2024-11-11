import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FormularioAnimado() {
    // Estados para almacenar lols textos ingresado
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    // estado para manejar la visibilidad del mensaje
    var isRegisteredVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
Text("Animación de Visibillidad con AnimatedVisibility", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para Nombre
        TextField(
            value = nombre,//valor actual del nombre
            onValueChange = { nombre = it },// se va actualizando el nombre
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para Apellido
        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto para Correo
        TextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón "Enviar"
        Button(onClick = {
            // Alterna la visibilidad del mensaje al hacer clic en "Enviar"
            isRegisteredVisible = !isRegisteredVisible
        }) {
            Text(text = "Enviar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // AnimatedVisibility para el mensaje "Formulario Registrado"
        AnimatedVisibility(
            visible = isRegisteredVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = "Formulario registrado para $nombre $apellido",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioAnimadoPreview() {
    FormularioAnimado()
}
