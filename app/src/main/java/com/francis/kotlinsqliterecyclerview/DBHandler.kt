package com.francis.kotlinsqliterecyclerview

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.francis.kotlinsqliterecyclerview.models.Customer
import java.lang.Exception

class DBHandler(context: Context, name:String?, factory: SQLiteDatabase.CursorFactory?, version:Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "myData.db"
        private  val DATABASE_VERSION = 1

        val CUSTOMER_TABLE_NAME ="customers "
        val COL_CUSTOMERID = "customerID"
        val COL_CUSTOMERNAME = "customerName"
        val COL_MAXCREDIT = "maxCredit"
    }
        override fun onCreate(db: SQLiteDatabase?) {
            val createCustomersTable = ("CREATE TABLE $CUSTOMER_TABLE_NAME (" +
                    "$COL_CUSTOMERID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COL_CUSTOMERNAME TEXT, " + "$COL_MAXCREDIT DOUBLE DEFAULT 0)")
            db!!.execSQL(createCustomersTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

  fun getCustomer(context: Context):ArrayList<Customer>{
      val query = "SELECT * FROM $CUSTOMER_TABLE_NAME"
      val db = this.readableDatabase
      val cursor = db.rawQuery(query, null)
      val customers = ArrayList<Customer>()

      if (cursor.count == 0)
          Toast.makeText(context, "No Record Found", Toast.LENGTH_SHORT).show()
       else {

         cursor.moveToFirst()
          while(cursor.moveToNext()){
              val customer = Customer()
              customer.customerID = cursor.getInt(cursor.getColumnIndex(COL_CUSTOMERID))
              customer.customerName = cursor.getString(cursor.getColumnIndex(CUSTOMER_TABLE_NAME))
              customer.maxCredit = cursor.getDouble(cursor.getColumnIndex(COL_MAXCREDIT))
              customers.add(customer)
          }
          Toast.makeText(context, "${cursor.count.toString()} Record Found", Toast.LENGTH_SHORT).show()
      }
      cursor.close()
      db.close()
      return customers
  }
    fun addCustomer(mContext: Context, customer: Customer) {
        val values  = ContentValues()
        values.put(COL_CUSTOMERNAME, customer.customerName)
        values.put(COL_MAXCREDIT, customer.maxCredit)
        val db = this.writableDatabase
        try {
            db.insert(CUSTOMER_TABLE_NAME, null, values)
            Toast.makeText(mContext, "Customer Added..", Toast.LENGTH_SHORT).show()
        } catch (e: Exception){
            Toast.makeText(mContext, e.message, Toast.LENGTH_SHORT).show()
        }

        db.close()
    }
}