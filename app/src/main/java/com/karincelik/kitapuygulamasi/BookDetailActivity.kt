package com.karincelik.kitapuygulamasi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // Intent ile gelen verileri alıyoruz
        val bookTitle = intent.getStringExtra("BOOK_TITLE")
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR")
        val bookImageResId = intent.getIntExtra("BOOK_IMAGE", 0)
        val bookDescription = intent.getStringExtra("BOOK_DESCRIPTION")

        // Arayüz elemanlarına verileri bağlıyoruz
        findViewById<ImageView>(R.id.detail_book_image).setImageResource(bookImageResId)
        findViewById<TextView>(R.id.detail_book_title).text = bookTitle
        findViewById<TextView>(R.id.detail_book_author).text = bookAuthor
        findViewById<TextView>(R.id.detail_book_description).text = bookDescription
    }
}
