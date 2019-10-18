package com.example.bookmanagementsystem.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmanagementsystem.R
import kotlinx.android.synthetic.main.fragment_record.*

class RecordFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel
    private val adapter = RecordAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recordViewModel =
            ViewModelProviders.of(this).get(RecordViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_record, container, false)
        recordViewModel.allRecords.observe(this, Observer {
            progressBar.visibility = View.GONE
            if (it.isEmpty()) {
                showToast("获取失败")
            } else {
                adapter.setRecordList(it)
            }
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        adapter.recordOnClickListener = {

        }
    }

    override fun onStart() {
        super.onStart()
        recordViewModel.fetchRecords()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}