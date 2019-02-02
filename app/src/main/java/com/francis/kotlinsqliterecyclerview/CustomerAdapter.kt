package com.francis.kotlinsqliterecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.francis.kotlinsqliterecyclerview.models.Customer
import kotlinx.android.synthetic.main.list_customers_row.view.*


class CustomerAdapter(val mContext:Context, val customers : ArrayList<Customer>) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val tvCustomerName = itemView.tvCustomerName
        val tvMaxCredit = itemView.tvMaxCredit
        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
       val view = LayoutInflater.from(mContext).inflate(R.layout.list_customers_row, parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder:ViewHolder, p1: Int) {
    }

}