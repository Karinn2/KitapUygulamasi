package com.karincelik.kitapuygulamasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private var bookList: List<Book>, // Filtrelenmiş kitap listesi
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var filteredBookList = bookList // Filtrelenmiş kitap listesini tutan değişken

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImage: ImageView = itemView.findViewById(R.id.book_image)
        val bookTitle: TextView = itemView.findViewById(R.id.book_title)
        val bookAuthor: TextView = itemView.findViewById(R.id.book_author)

        fun bind(book: Book) {
            bookImage.setImageResource(book.imageResId)
            bookTitle.text = book.title
            bookAuthor.text = book.author

            // Kitaba tıklama olayı
            itemView.setOnClickListener {
                onBookClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(filteredBookList[position]) // Filtrelenmiş listeyi bağla
    }

    override fun getItemCount(): Int = filteredBookList.size // Filtrelenmiş kitap sayısını döndür

    // Filtreleme metodunu ekle
    fun filter(query: String?) {
        filteredBookList = if (query.isNullOrEmpty()) {
            bookList // Eğer sorgu boşsa orijinal listeyi göster
        } else {
            bookList.filter { book ->
                book.title.contains(query, ignoreCase = true) || // Kitap başlığına göre filtrele
                        book.author.contains(query, ignoreCase = true) // Yazar adına göre filtrele
            }
        }
        notifyDataSetChanged() // Adapter'ı güncelle
    }
}
