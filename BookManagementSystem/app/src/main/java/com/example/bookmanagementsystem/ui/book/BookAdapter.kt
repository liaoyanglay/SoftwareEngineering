package com.example.bookmanagementsystem.ui.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmanagementsystem.R
import com.example.bookmanagementsystem.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

/**
 * @author dizzylay
 * @date 2019-10-09
 * @email liaoyanglay@outlook.com
 */

class BookAdapter(private var bookList: ArrayList<Book>? = null) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    var bookOnClickListener: ((Book) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book, parent, false)
        )

    override fun getItemCount() = bookList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bookList?.apply { holder.bind(this[position]) }
    }

    fun setBookList(bookList: ArrayList<Book>) {
        this.bookList = bookList
        notifyDataSetChanged()
    }

    fun addBookList(bookList: ArrayList<Book>) {
        this.bookList?.addAll(bookList) ?: setBookList(bookList)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(book: Book) = with(itemView) {
            setOnClickListener { bookOnClickListener?.invoke(book) }
            book_name_text.text = book.name
            author_text.text = book.author
            Glide.with(cover_image).load(book.cover).into(cover_image)
            rend_text.visibility = if (book.rent) View.VISIBLE else View.INVISIBLE
            class_text.text = book.clazz
        }
    }
}