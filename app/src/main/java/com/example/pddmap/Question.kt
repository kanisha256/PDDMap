package com.example.pddmap

data class Question(
    val text: String,
    val imageResId: Int,
    val options: List<String>,
    val correctOptionIndex: Int
)

val questions = listOf(
    Question(
        "Как называется эта животная?",
        R.drawable.s1_25,
        listOf("Кошка", "Собака", "Кролик", "Лиса"),
        0
    ),
    Question(
        "Как называется эта птица?",
        R.drawable.s2_4,
        listOf("Курица", "Ворона", "Голубь", "Сова"),
        2
    ),
    Question(
        "Как называется это растение?",
        R.drawable.s3_1,
        listOf("Роза", "Подсолнух", "Тюльпан", "Орхидея"),
        1
    )
)

