package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Cabecera()
        Cuerpo()
    }
}

@Composable
private fun Cuerpo() {
    var posicion by remember { mutableStateOf(1) }
    var numeroDeClicks by remember { mutableStateOf(0) }

    when (posicion) {

        1 -> {
            ImagenTexto(
                foto = R.drawable.lemon_tree,
                texto = R.string.TapTree,
                descripcion = R.string.Tree,
                imagenSiguiente = {
                    numeroDeClicks = (2..4).random()
                    posicion++
                }
            )
        }

        2 -> {
            ImagenTexto(
                foto = R.drawable.lemon_squeeze,
                texto = R.string.TapLemon,
                descripcion = R.string.Lemon,
                imagenSiguiente = {
                    numeroDeClicks--
                    if (numeroDeClicks == 0) {
                        posicion++
                    }
                }
            )
        }

        3 -> {
            ImagenTexto(
                foto = R.drawable.lemon_drink,
                texto = R.string.TapFullGlass,
                descripcion = R.string.FullGlass,
                imagenSiguiente = {
                    posicion++
                }
            )
        }

        4 -> {
            ImagenTexto(
                foto = R.drawable.lemon_restart,
                texto = R.string.TapEmptyGlass,
                descripcion = R.string.EmptyGlass,
                imagenSiguiente = {
                    posicion = 1
                }
            )
        }
    }
}

@Composable
private fun ImagenTexto(
    foto: Int,
    texto: Int,
    descripcion: Int,
    imagenSiguiente: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(35.dp))
                .background(Color(195, 236, 210))
        ) {
            Image(
                painter = painterResource(foto),
                contentDescription = stringResource(descripcion),
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        start = 30.dp,
                        bottom = 20.dp,
                        end = 30.dp,
                    )
                    .clickable { imagenSiguiente() }
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(texto),
                modifier = Modifier
                    .padding(top = 15.dp)
            )
        }
    }
}

@Composable
private fun Cabecera() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow),
        horizontalArrangement = Arrangement.Center,

        ) {
        Text(
            text = "Lemonade",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(30.dp)
        )
    }
}