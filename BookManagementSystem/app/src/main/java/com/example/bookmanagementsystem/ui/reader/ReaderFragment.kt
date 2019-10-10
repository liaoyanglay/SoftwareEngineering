package com.example.bookmanagementsystem.ui.reader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bookmanagementsystem.R

class ReaderFragment : Fragment() {

    private lateinit var readerViewModel: ReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        readerViewModel =
            ViewModelProviders.of(this).get(ReaderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_reader, container, false)
        readerViewModel.text.observe(this, Observer {
        })
        return root
    }
}