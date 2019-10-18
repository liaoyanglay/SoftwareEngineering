package com.example.bookmanagementsystem.ui.record

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmanagementsystem.R
import com.example.bookmanagementsystem.data.model.Record
import kotlinx.android.synthetic.main.item_lend.view.*

/**
 * @author dizzylay
 * @date 2019-10-11
 * @email liaoyanglay@outlook.com
 */
class RecordAdapter(private var recordList: ArrayList<Record>? = null) :
    RecyclerView.Adapter<RecordAdapter.ViewHolder>() {
    var recordOnClickListener: ((Record) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lend, parent, false)
        )

    override fun getItemCount() = recordList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        recordList?.apply { holder.bind(this[position]) }
    }

    fun setRecordList(recordList: ArrayList<Record>) {
        this.recordList = recordList
        notifyDataSetChanged()
    }

    fun addRecordList(recordList: ArrayList<Record>) {
        this.recordList?.addAll(recordList) ?: setRecordList(recordList)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        @SuppressLint("SetTextI18n")
        fun bind(record: Record) = with(itemView) {
            setOnClickListener { recordOnClickListener?.invoke(record) }
            sernum_text.text = "流水号：${record.sernum}"
            readerId_text.text = "读者ID：${record.readerId}"
            bookId_text.text = "图书ID：${record.bookId}"
            lend_date_text.text = "借阅日期：${record.lendDate}"
            return_date_text.text = "归还日期：${record.backDate}"
        }
    }
}