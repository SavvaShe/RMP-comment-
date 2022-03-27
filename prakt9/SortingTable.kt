package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.prakt9.Model.Bicycles
import com.example.prakt9.Model.Participant

class SortingTable : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {//функция для инициализации активности
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)//род класс
        setContentView(R.layout.activity_sorting_table)//ресурсы для пользовательского интерфейса
        val buttonAscending = findViewById<Button>(R.id.buttonAscending)//кнопка для сортировки по возрастанию
        val buttonDescending = findViewById<Button>(R.id.buttonDescending)//кнопка для сортировки по убыванию
        val editTextNumberColumn = findViewById<EditText>(R.id.editTextNumberColumn)//для редактирования номера
        val textViewTable2 = findViewById<TextView>(R.id.textViewTable2)//таблица 2
        val textViewColumn = findViewById<TextView>(R.id.textViewColumn)//колокна
        val listB= mutableListOf<Pair<String,(Bicycles)->Comparable<Any>>>(Pair("Тип велосипеда"){ x->x.BicyclesType as Comparable<Any>})//велики
        val listP= mutableListOf<Pair<String,(Participant)->Comparable<Any>>>(Pair("1) Фамилия ") { x -> x.F as Comparable<Any> },//фамилия и тд по названи.
            Pair("2) Имя ") { x -> x.I as Comparable<Any> },
            Pair("3) Отчество ") { x -> x.O as Comparable<Any> },
            Pair("4) Стаж ") { x -> x.Experience as Comparable<Any> })
        textViewTable2.text =null//закидываем нул
        textViewColumn.text = null//закидываем нул
        editTextNumberColumn.isEnabled=false//закидываем фолз
        buttonAscending.setOnClickListener {//описание процесса по возрастанию
            if (MainActivity.Table1IsTrue){//если тру то сортируем 1 табличку
                MainActivity.DB.OListB.getBicyclesList().sortBy(listB[0].second)//сортируем по второму полю пары список велосипедов
                Toast.makeText(// вывод сообщения
                    this,
                    getString(R.string.table_sorted_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }else {//если фолз переходим на вторую табличку
                textViewTable2.text = getString(R.string.enter_the_sort_field_number)//принимаем строку из таблички 2
                editTextNumberColumn.isEnabled=true//включаем
                var strColumn =""//строка пуста
                listP.forEach {//бежим по списку
                    strColumn += it.first//увеличиваем строку
                }
                textViewColumn.text = strColumn
                if (editTextNumberColumn.text!=null) {//если не нул
                    val indexStr = editTextNumberColumn.text.toString()//перевод в строку
                    val indexInt = indexStr.toIntOrNull()//проверка индекса
                    if (indexInt!=null) {//если есть индекс сортируем
                        MainActivity.DB.OListP.getParticipantList().sortBy(listP[indexInt-1].second)
                        editTextNumberColumn.isEnabled = false//останавливаем
                        textViewTable2.text =null//обнуляем
                        Toast.makeText(// вывод сообщения
                            this,
                            getString(R.string.table_sorted_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else Toast.makeText(// вывод сообщения
                    this,
                    getString(R.string.you_havent_entered_anything),
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
        buttonDescending.setOnClickListener {//аналогично сортировке по возрастанию
            if (MainActivity.Table1IsTrue){
                MainActivity.DB.OListB.getBicyclesList().sortByDescending(listB[0].second)
                Toast.makeText(// вывод сообщения
                    this,
                    getString(R.string.table_sorted_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                textViewTable2.text = getString(R.string.enter_the_sort_field_number)
                editTextNumberColumn.isClickable=true
                var strColumn =""
                listP.forEach {
                    strColumn += it.first
                }
                textViewColumn.text = strColumn
                if (editTextNumberColumn.text!=null) {
                    val indexStr = editTextNumberColumn.text.toString()
                    val indexInt = indexStr.toIntOrNull()
                    if (indexInt!=null) {
                        MainActivity.DB.OListP.getParticipantList().sortByDescending(listP[indexInt].second)
                        editTextNumberColumn.isClickable = false
                        textViewTable2.text =null
                        Toast.makeText(/вывод сообщения
                            this,
                            getString(R.string.table_sorted_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else Toast.makeText(//иначе вывод сообщения
                    this,
                    getString(R.string.you_havent_entered_anything),
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            if (MainActivity.Table1IsTrue){
                var intent = Intent(this,MenuTable1::class.java)
                startActivity(intent)//старт активити в менюшке 1 таблицы
                finish()//конец
            }else {
                var intent = Intent(this,MenuTable2::class.java)
                startActivity(intent)//старт активити в менюшке 2 таблицы
                finish()//конец
            }
        }
        return true
    }
}