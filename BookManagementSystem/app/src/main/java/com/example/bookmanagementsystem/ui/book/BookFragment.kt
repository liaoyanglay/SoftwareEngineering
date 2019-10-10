package com.example.bookmanagementsystem.ui.book

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanagementsystem.R
import kotlinx.android.synthetic.main.fragment_book.*

class BookFragment : Fragment(),
    SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var backPressedCallback: OnBackPressedCallback
    private val adapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookViewModel =
            ViewModelProviders.of(this).get(BookViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_book, container, false)
        bookViewModel.allBooks.observe(this, Observer {
            adapter.addBookList(it)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.inflateMenu(R.menu.search_menu)
        val searchView = toolbar.menu.findItem(R.id.search).actionView as SearchView
        searchView.isIconified = true
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
        searchView.setOnSearchClickListener {
            category_button.visibility = View.GONE
            toolbar_text.visibility = View.GONE
            backPressedCallback.isEnabled = true
        }
        backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            searchView.setQuery("", false)
            searchView.isIconified = true
            backPressedCallback.isEnabled = false
        }
        backPressedCallback.isEnabled = false
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        adapter.bookOnClickListener = {
            val intent = Intent(requireContext(), BookInfoActivity::class.java)
            intent.putExtra(BookInfoActivity.BOOK_DATA, it)
            startActivity(intent)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onClose(): Boolean {
        category_button.visibility = View.VISIBLE
        toolbar_text.visibility = View.VISIBLE
        return false
    }
}