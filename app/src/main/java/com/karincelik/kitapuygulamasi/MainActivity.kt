package com.karincelik.kitapuygulamasi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private lateinit var bookList: List<Book> // Kitap listesini burada tanımlıyoruz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Kitap listesi örneği
        bookList = listOf(
            Book(R.drawable.ic_launcher_background, "Kitap 1", "Yazar 1", "Kitap açıklaması 1"),
            Book(R.drawable.ic_launcher_background, "Kitap 2", "Yazar 2", "Kitap açıklaması 2"),
            Book(R.drawable.ic_launcher_background, "Kitap 3", "Yazar 3", "Kitap açıklaması 3"),
            Book(R.drawable.ic_launcher_background, "Kitap 4", "Yazar 4", "Kitap açıklaması 4")
        )

        // RecyclerView ve Adapter ayarları
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this) // Dikey listeleme düzeni

        // BookAdapter'i oluştur ve tıklama olayını ayarla
        bookAdapter = BookAdapter(bookList) { selectedBook ->
            // Kitaba tıklandığında detay sayfasına git
            val intent = Intent(this, BookDetailActivity::class.java).apply {
                putExtra("BOOK_TITLE", selectedBook.title)
                putExtra("BOOK_AUTHOR", selectedBook.author)
                putExtra("BOOK_IMAGE", selectedBook.imageResId)
                putExtra("BOOK_DESCRIPTION", selectedBook.description)
            }
            startActivity(intent) // Detay sayfasına geçiş yap
        }

        // Adapter'i RecyclerView'a bağla
        recyclerView.adapter = bookAdapter
    }

    // Menü öğelerini oluştur
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu2, menu) // Menüyü inflate et

        // Arama butonunu bul ve SearchView nesnesini ayarla
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        // Arama sorgusu değiştiğinde yapılacak işlemler
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false // Arama sorgusu gönderildiğinde yapılacak işlemler
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Kullanıcı her bir harfi yazdığında listeyi filtrele
                bookAdapter.filter(newText)
                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Menü öğelerine tıklama durumunu işliyoruz
        return super.onOptionsItemSelected(item)
    }
}
