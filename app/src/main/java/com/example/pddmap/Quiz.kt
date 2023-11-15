package com.example.pddmap

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Quiz : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizScreen()
        }
    }

    @Composable
    fun QuizScreen() {
        var currentQuestionIndex by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            var currentQuestion = questions[currentQuestionIndex]

            // Вопрос
            Text(
                text = currentQuestion.text,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Изображение
            val imageBitmap = ImageBitmap.imageResource(currentQuestion.imageResId)
            Image(
                bitmap = imageBitmap,
                contentDescription = "Question Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(bottom = 16.dp)
            )

            // Варианты ответов
            currentQuestion.options.forEachIndexed { index, option ->
                Button(
                    onClick = {
                        val isCorrect = index == currentQuestion.correctOptionIndex
                        if (isCorrect) {
                            Toast.makeText(this@Quiz, "Молодец", Toast.LENGTH_SHORT).show()
                            currentQuestion = questions[currentQuestionIndex]
                        } else {
                            Toast.makeText(this@Quiz, "Плохо", Toast.LENGTH_SHORT).show()
                        }

                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                        } else {
                            val intent = Intent(this@Quiz, MainActivity::class.java)
                            startActivity(intent)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = option)
                }
            }
        }
    }
}