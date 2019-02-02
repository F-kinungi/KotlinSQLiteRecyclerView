package com.francis.kotlinsqliterecyclerview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
     lateinit var dbHandler: DBHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = DBHandler(this, null, null, 1)
        viewCustomers()

        fab.setOnClickListener {
         val intent = Intent(this, AddCustomerActivity::class.java)
            startActivity(intent)
        }
    }
        private fun viewCustomers() {
        val customerList = dbHandler.getCustomer(this)
        val adapter = CustomerAdapter(this, customerList)
        //val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager
        recyclerView.adapter = adapter
    }
    override fun onResume() {
        viewCustomers()
        super.onResume()
    }

}
