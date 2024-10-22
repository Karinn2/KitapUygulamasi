package com.karincelik.kitapuygulamasi

data class Book(
    val imageResId: Int, // Kitap resiminin ID'si
    val title: String,   // Kitap adı
    val author: String,  // Yazar adı
    val description: String // Kitap açıklaması
)
