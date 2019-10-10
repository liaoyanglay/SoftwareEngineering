package com.example.bookmanagementsystem.ui.book

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bookmanagementsystem.R
import com.example.bookmanagementsystem.data.model.Book
import kotlinx.android.synthetic.main.activity_book.*

class BookInfoActivity : AppCompatActivity() {
    companion object {
        const val BOOK_DATA = "BOOK"
    }

    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        book = intent.getSerializableExtra(BOOK_DATA) as Book
        initView()
        initEvent()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        toolbar.inflateMenu(R.menu.edit_book_menu)
        toolbar.menu.findItem(R.id.edit_book)
            .setOnMenuItemClickListener { onOptionsItemSelected(it) }
        toolbar.menu.findItem(R.id.delete_book)
            .setOnMenuItemClickListener { onOptionsItemSelected(it) }
        Glide.with(cover_image).load(book.cover).into(cover_image)
        book_name_text.text = book.name
        author_text.text = "作者：" + book.author
        publisher_text.text = "出版社：" + book.publisher
        publish_date_text.text = "出版年份：" + book.publishDate
        price_text.text = "价格：" + book.price
        ISBN_text.text = "ISBN：" + book.ISBN
        description_text.text = book.description
        rend_button.text = if (book.isRent) "归 还" else "借 阅"
    }

    private fun initEvent() {
        back_button.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_book -> {
                Toast.makeText(this, "编辑成功", Toast.LENGTH_SHORT).show()
            }
            R.id.delete_book -> {
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}
