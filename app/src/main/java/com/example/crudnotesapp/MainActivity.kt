package com.example.crudnotesapp

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var listNotes = ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //Load from Db
        LoadQuery("%")
    }
    @SuppressLint("Range")
    private fun LoadQuery(title:String){
        var dbManager = DbManager(this)
        val projections = arrayOf("ID", "Title", "Description")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        this.listNotes.clear()
        if (cursor.moveToFirst()){
            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))
            }while (cursor.moveToNext())
        }

        //adapter
        var myNotesAdapter = MyNotesAdapter(this,listNotes)

    }

    inner class MyNotesAdapter(mainActivity: MainActivity, listNotes: ArrayList<Note>) {


    }

}

