package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuTable2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_table2)
        val buttonExit = findViewById<Button>(R.id.buttonExit)
        val buttonOutput = findViewById<Button>(R.id.buttonOutput)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonDeleting = findViewById<Button>(R.id.buttonDeleting)
        val buttonEditing = findViewById<Button>(R.id.buttonEditing)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)
        val buttonSorting = findViewById<Button>(R.id.buttonSorting)
        MainActivity.Table1IsTrue=false
        buttonOutput.setOnClickListener {
            var intent = Intent(this,ListOutputActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonAdd.setOnClickListener {
            var intent = Intent(this,AddTable2::class.java)
            startActivity(intent)
            finish()
        }
        buttonEditing.setOnClickListener {
            var intent = Intent(this,EditingTable2::class.java)
            startActivity(intent)
            finish()
        }
        buttonDeleting.setOnClickListener {

        }
        buttonSorting.setOnClickListener {
            var intent = Intent(this,SortingTable::class.java)
            startActivity(intent)
            finish()
        }
        buttonSearch.setOnClickListener {
            var intent = Intent(this,SearchTable::class.java)
            startActivity(intent)
            finish()
        }
        buttonExit.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)
            startActivityForResult(intent, MenuTable2.REQUEST_CODE_ENTER)
            finish()
        }
    }
    companion object{
        const val REQUEST_CODE_ENTER = 1
    }
}