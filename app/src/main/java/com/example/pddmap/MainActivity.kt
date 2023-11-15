package com.example.pddmap

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pddmap.ui.theme.PDDMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()

        }
    }

    @Composable
    fun MainScreen() {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()
                Title()
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        val intent = Intent(this@MainActivity, MapYandex::class.java)
                        startActivity(intent)
                              },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Установить знак на карте")
                }
                Button(
                    onClick = {
                        val intent = Intent(this@MainActivity, Quiz::class.java)
                        startActivity(intent)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Вопросы по ПДД")
                }
                Button(
                    onClick = {
                              val intent = Intent(this@MainActivity, SignHome::class.java)
                        startActivity(intent)
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Каталог дорожных знаков")
                }
            }
        }
    }

    @Composable
    fun Logo() {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Логотип",
            modifier = Modifier.size(128.dp)
        )
    }

    @Composable
    fun Title() {
        Text(
            text = "Дорожные знаки",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
    }

    @Preview
    @Composable
    fun MainScreenPreview() {
        MainScreen()
    }}