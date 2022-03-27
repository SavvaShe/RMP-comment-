package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prakt9.Model.Bicycles
import com.example.prakt9.Model.Participant



class SearchTable : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)//род класс
        setContentView(R.layout.activity_search_table)//ресурсы
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)//кнопка поиск
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)//кнопка редактирования
        val textViewColumn = findViewById<TextView>(R.id.textViewColumn)//ввод текста
        val editTextNumberColumn = findViewById<EditText>(R.id.editTextNumberColumn)//для ввода цифр
        val listB= mutableListOf<Pair<String,(Bicycles)->String>>(Pair("1) Поиск по типу велосипеда") { x -> x.BicyclesType})//для по иска по типу
        val listP= mutableListOf<Pair<String,(Participant)->String>>(Pair("1) Поиск по фамилии ") { x -> x.F },//для поиска по фамилии
            Pair("2) Поиск по имени ") { x -> x.I },//для поиска по имени
            Pair("3) Поиск по отчеству ") { x -> x.O },
            Pair("4) Поиск по типу велосипеда ") { x -> x.IdBicycleType },
            Pair("5) Поиск по стажу ") { x -> x.Experience.toString() })
        var ColumnStr = ""
        if (MainActivity.Table1IsTrue){//проверка активтити если тру бежим по 1 таблице,фолз по второй
            listB.forEach {//бежим по списку
             ColumnStr += it.first//запись
            }
        }else{
            listP.forEach {
                ColumnStr += it.first
            }
        }
        textViewColumn.text = ColumnStr//присваивание
        buttonSearch.setOnClickListener {//обработка кнопки при  нажатии
            if (editTextNumberColumn.text!=null) {//если не нул то
                if (editTextSearch.text!=null) {//если не нул то
                    val numColumnStr = editTextNumberColumn.text.toString()//преобразование в строку
                    val numColumnInt = numColumnStr.toIntOrNull()//проверка на тип
                    if (numColumnInt!=null) {//еси не нул то
                        if (!MainActivity.Table1IsTrue) {//если тут стоит ! так что сначала бежим по 2 табличке потом по 1
                            var takeProp = listP[numColumnInt-1].second//записываем второе значение пары
                            val listPSerch = MainActivity.DB.OListP.getParticipantList()//смотрим список участников
                                .filter{ takeProp(it).contains(editTextSearch.text.toString())}//тут мы фильруем списочек

                            val participantRecyclerView =
                                findViewById<RecyclerView>(R.id.ListRecyclerView)//На экране отображаются видимые элементы списка.
                            // Когда при прокрутке списка верхний элемент уходит за пределы экрана и становится невидимым, его содержимое очищается.
                            // При этом сам "чистый" элемент помещается вниз экрана и заполняется новыми данными, иными словами переиспользуется, отсюда название Recycle.
                            participantRecyclerView.adapter =//для привязки данных к RecyclerView. ViewHolder — для получения ссылки на View-элементы, которые должны быть динамически изменены во время выполнения программы.
                                ParticipantAdapter(listPSerch)
                            participantRecyclerView.layoutManager = LinearLayoutManager(this)
                        } else {//аналогично работате и с таблицей велосипедов
                            var takeProp = listB[numColumnInt-1].second
                            val listBSerch = MainActivity.DB.OListB.getBicyclesList().filter{ takeProp(it).contains(editTextSearch.text.toString())}
                            val bicyclesRecyclerView =
                                findViewById<RecyclerView>(R.id.ListRecyclerView)
                            bicyclesRecyclerView.adapter =
                                BicyclesAdapter(listBSerch)
                            bicyclesRecyclerView.layoutManager = LinearLayoutManager(this)
                        }
                    }
                }else Toast.makeText(
                    this,
                    getString(R.string.you_havent_entered_anything),
                    Toast.LENGTH_SHORT//вывод сообщения на экран
                ).show()
            }else Toast.makeText(
                this,
                getString(R.string.you_havent_entered_anything),
                Toast.LENGTH_SHORT//вывод сообщения на экран
            ).show()
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