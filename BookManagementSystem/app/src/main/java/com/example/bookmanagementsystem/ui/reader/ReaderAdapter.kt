package com.example.bookmanagementsystem.ui.reader

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanagementsystem.R
import com.example.bookmanagementsystem.data.model.Reader
import kotlinx.android.synthetic.main.item_reader.view.*

/**
 * @author dizzylay
 * @date 2019-10-11
 * @email liaoyanglay@outlook.com
 */
class ReaderAdapter(private var readerList: ArrayList<Reader>? = null) :
    RecyclerView.Adapter<ReaderAdapter.ViewHolder>() {
    var readerOnClickListener: ((Reader) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reader, parent, false)
        )

    override fun getItemCount() = readerList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        readerList?.apply { holder.bind(this[position]) }
    }

    fun setReaderList(readerList: ArrayList<Reader>) {
        this.readerList = readerList
        notifyDataSetChanged()
    }

    fun addReaderList(readerList: ArrayList<Reader>) {
        this.readerList?.addAll(readerList) ?: setReaderList(readerList)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        @SuppressLint("SetTextI18n")
        fun bind(reader: Reader) = with(itemView) {
            setOnClickListener { readerOnClickListener?.invoke(reader) }
            name_text.text = "姓名：${reader.name}"
            male_text.text = "性别：${reader.sex}"
            tel_text.text = "电话：${reader.telcode}"
            reader_id_text.text = "ID:${reader.readerId}"
        }
    }
}