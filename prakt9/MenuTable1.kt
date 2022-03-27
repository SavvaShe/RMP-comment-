package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MenuTable1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {//создание
        super.onCreate(savedInstanceState)//супер класс
        setContentView(R.layout.activity_menu_table1)//ресурсы
        val buttonExit = findViewById<Button>(R.id.buttonExit)//кнопка выхода
        val buttonOutput = findViewById<Button>(R.id.buttonOutput)//кнопка вывода
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)//кнопка добавления
        val buttonDeleting = findViewById<Button>(R.id.buttonDeleting)//кнопка удаления
        val buttonEditing = findViewById<Button>(R.id.buttonEditing)//кнопка редакттирования
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)//кнопка поиска
        val buttonSorting = findViewById<Button>(R.id.buttonSorting)//кнопка сортировки
        MainActivity.Table1IsTrue= true
        buttonOutput.setOnClickListener {//описание кнопки вывода информации
            var intent = Intent(this,ListOutputActivity::class.java)//интент
            startActivity(intent)//начало активити
            finish()//конец активити
        }
        //аналогично со всеми кнопками
        buttonAdd.setOnClickListener {
            var intent = Intent(this,AddTable1::class.java)
            startActivity(intent)
            finish()
        }
        buttonEditing.setOnClickListener {
            var intent = Intent(this,EditingTable1::class.java)
            startActivity(intent)
            finish()
        }
        buttonDeleting.setOnClickListener {
            var intent = Intent(this,DeletingTable1::class.java)
            startActivity(intent)
            finish()
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
            startActivityForResult(intent, MenuTable1.REQUEST_CODE_ENTER)
            finish()
        }
    }
    companion object{
        const val REQUEST_CODE_ENTER = 1//код запроса
        const val IsTable1_ = "IsTable1_"//текстовая константа
    }
}