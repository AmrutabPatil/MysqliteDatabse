package com.example.sqlitedatabasetest

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step 1:create database with the help of openOrCreateDatabase Method
        var sqlitedatabse = openOrCreateDatabase("db_15_april", Context.MODE_PRIVATE, null)

        // step 2: table creation.

     //var query="create table if not exists AndroidBatch(id number,name varchar(50),qual varchar(50),desig varchar(50))"

        sqlitedatabse.execSQL("create table if not exists AndroidBatch(_id integer primary key autoincrement,id integer unique,name varchar(50),qual varchar(50),desig varchar(50))")

     //step 3:data insertion
        btn_insert.setOnClickListener {

            var cv = ContentValues()
            cv.put("id", et1.text.toString())
            cv.put("name", et2.text.toString())
            cv.put("qual", et3.text.toString())
            cv.put("desig", et4.text.toString())

            var status =
                sqlitedatabse.insert("AndroidBatch", null, cv)

            if (status == -1.toLong()) {
                Toast.makeText(this, "unable to insert the data", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "data inserted successfully", Toast.LENGTH_SHORT).show()
            }

        }

        btn_read.setOnClickListener {

            var adapter=sqlitedatabse.query("AndroidBatch",null,null,null,null,null,null)
            var fromArray= arrayOf("id","name","qual","desig")
            var toArray= intArrayOf(R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4)

            var cursor_adapter= SimpleCursorAdapter(this,R.layout.my_design,adapter,fromArray,toArray)
            list_view.adapter=cursor_adapter

        }


        btn_update.setOnClickListener {

            var cv = ContentValues()
            cv.put("id", et1.text.toString())
            cv.put("name", et2.text.toString())
            cv.put("qual", et3.text.toString())
            cv.put("desig", et4.text.toString())

            var status= sqlitedatabse.update("AndroidBatch",cv,"id=?", arrayOf(et1.text.toString()))
            if(status>0)
            {
                Toast.makeText(this,"data updated succcesfully",Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(this,"failed to update the data",Toast.LENGTH_SHORT).show()

            }

        }


        btn_delete.setOnClickListener {
            var status= sqlitedatabse.delete("AndroidBatch","id=?", arrayOf(et1.text.toString()))

            if(status>0)
            {
                Toast.makeText(this,"data deleted succcesfully",Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(this,"failed to delete the data", Toast.LENGTH_SHORT).show()

            }

        }
    }
}

