package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.prakt9.Model.DB

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {//создание савед для сохранения данных при переворачивании экрана
        super.onCreate(savedInstanceState)//суперкласс
        setContentView(R.layout.activity_main)//ресурсы
        val buttonTable1 = findViewById<Button>(R.id.buttonTable1)//кнопка таблички 1
        val buttonTable2 = findViewById<Button>(R.id.buttonTable2)//кнопка таблички 2
        val buttonExit = findViewById<Button>(R.id.buttonExit)//выход
      //  var Table1IsTrue = false


        buttonTable1.setOnClickListener {//описание кнопки 1 таблицы ждем нажатия
            Table1IsTrue = true//дефолт
            var intent = Intent(this,MenuTable1::class.java)//интент как и в 7 работе используется для перехода по активити
            //intent.putExtra(Table1IsTrue_,Table1IsTrue)
            startActivity(intent)//начало активити с передачей интента
            finish()//завершение работы активити
        }
        buttonTable2.setOnClickListener {//описание кнопки 2 таблицы ждем нажатия
            var intent = Intent(this,MenuTable2::class.java)//аналогично с 1 табличкой
            //intent.putExtra(Table1IsTrue_,Table1IsTrue)
            startActivity(intent)//старт
            finish()//конец
        }
        buttonExit.setOnClickListener {//описание кнопки выхода
            finish()//завершение активити
        }

    }
    companion object{
        var DB=DB()//бд
        var Table1IsTrue = false//дефолтное значение
        const val REQUEST_CODE_ENTER = 1 //код запроса
        const val Table1IsTrue_ = "Table1IsTrue"//правильная запись текстовой константы
        //const val DB_ = "DB"
    }
}