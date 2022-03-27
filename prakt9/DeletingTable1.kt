package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class DeletingTable1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {//создание(запускается при создании активити)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//поддержка вверхней плашки приложения.дисплей дома
        super.onCreate(savedInstanceState)//супер класс(родительский)
        setContentView(R.layout.activity_deleting_table1)//ресурсы
        val buttonInput = findViewById<Button>(R.id.buttonInput)//кнопка ввода
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)//редактирование текста
        buttonInput.setOnClickListener {//опписание кнопки
            if (editTextNumber.text != null) {//если не нул
                val indexStr = editTextNumber.text.toString()//преобразование в строку
                val indexInt = indexStr.toIntOrNull()//проверка индекса
                var result: UUID? = null//записываем в айдишку нул
                if (indexInt != null) {//если индекс не нул
                    if (indexInt <= MainActivity.DB.OListB.getBicyclesList().size) {//если индекс меньше или равен номеру списков
                        MainActivity.DB.OListB.getBicyclesList().forEachIndexed { index, id ->//бежим по списку
                            if (index == (indexInt - 1)) {//записываем айди
                                result = id.Id
                            }

                        }
                        if (result != null) {//если результат не нул удаляем
                            MainActivity.DB.OListB.Delete(
                                result,
                                MainActivity.DB.OListB.getBicyclesList()
                            )
                            Toast.makeText(
                                this,
                                getString(R.string.entry_successfully_deleted),
                                Toast.LENGTH_SHORT
                            ).show()
                            editTextNumber.text = null
//и выводим сообщение успешное удаление
                        }
                    } else Toast.makeText(//иначе выводим сообщение о номере большим чем номер записей
                        this,
                        getString(R.string.The_entered_number_is_greater_than_the_number_of_records),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else Toast.makeText(//сообщение о не понятных данных
                this,
                getString(R.string.you_havent_entered_anything),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {//выбранный номер
        if(item.itemId==android.R.id.home){//если совпадает
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