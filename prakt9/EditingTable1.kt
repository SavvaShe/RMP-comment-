package com.example.prakt9

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.prakt9.Model.Bicycles
import com.example.prakt9.Model.Valid
import java.util.*

class EditingTable1 : AppCompatActivity() {//класс редактирования 1 таблички наследует AppCompatActivity()
    override fun onCreate(savedInstanceState: Bundle?) {//переопределение функции для инициализации активности
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)//род класс
        setContentView(R.layout.activity_editing_table1)//метод для размещения пользовательского интерфейса
        val buttonEditing = findViewById<Button>(R.id.buttonEditing)//кнопка редактирования
        val buttonInput = findViewById<Button>(R.id.buttonInput)//кнопка ввода
        val buttonAddPhoto = findViewById<Button>(R.id.buttonAddPhoto)//кнопка добавить фото
        val editTextBicycleType = findViewById<EditText>(R.id.editTextBicycleType)//кнопка редактирования велосипедов
        val editTextIndex = findViewById<EditText>(R.id.editTextNumber)//кнопка редактирования индекса
        val imageViewPhoto = findViewById<ImageView>(R.id.imageViewPhoto)//показ фоток
        buttonEditing.isEnabled = false//значение по дефолту
        buttonAddPhoto.setOnClickListener {//описание кнопки при нажатии
            val intent = Intent()//интент
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE//работа с медиа вот из этой штуки import android.provider.MediaStore
            startActivityForResult(intent, AddTable1.REQUEST_IMAGE_CAPTURE)//запуск активити
        }
        buttonInput.setOnClickListener {//кнока ввода
            if (editTextIndex.text != null) {
                val indexStr = editTextIndex.text.toString()//перевод того что мы хотим редачнуть в строку
                val indexInt = indexStr.toIntOrNull()//проверка индекса
                var result: UUID? = null//запись нул в результат
                var result_photo: Bitmap? = null//аналогично но результат для фоток
                if (indexInt != null){//проверка на не нул
                    MainActivity.DB.OListB.getBicyclesList().forEachIndexed { index, id ->//бежим по списку велосипедов по индексу
                        if (index == (indexInt - 1)) {
                            result = id.Id//запись в результат айдишки из списка
                        }
                    }
                if (result != null) {//проверка на не нул
                    MainActivity.DB.OListB.getBicyclesList().forEachIndexed { index, Bicucles ->//бежим по списку велосипедов по индексу
                        if (index == (indexInt - 1)) {
                            result_photo = Bicucles.Photo//запись в результат фотки из списка
                        }
                    }
                    if (result_photo!=null){//если не нул выкидываем результат в функцию что описана ниже и выводим
                        imageViewPhoto.setImageBitmap(result_photo)
                    }
                    buttonEditing.isEnabled = true//делаем кнопку включенной
                    buttonEditing.setOnClickListener {//описание кнопки редактирования
                        if (editTextBicycleType.text != null) {//проверка что введен хоть какой-то текст
                            if (Valid.checkType(editTextBicycleType.text.toString())) {//проверка типа и приеобразование в строку
                                if (Photo!=null) {//проверка на не нул
                                    val Bicycles_ = Bicycles(editTextBicycleType.text.toString(),
                                        Photo)//в переменную для более короткой записи
                                    MainActivity.DB.OListB.Edit(//вызываем фукнцию из мейн активити и передаем туда
                                    Pair(Bicycles_, result),
                                    MainActivity.DB.OListB.getBicyclesList()
                                     )
                                    editTextBicycleType.text = null//обнуляем все
                                    editTextIndex.text = null//обнуляем все
                                    buttonEditing.isEnabled = false//выруюаем кнопку
                                } else Toast.makeText(this,getString(R.string.you_have_not_added_a_photo),Toast.LENGTH_SHORT).show()//иначе вывод сообщения
                            } else Toast.makeText(//иначе вывод сообщения
                                this,
                                getString(R.string.you_entered_the_wrong_type),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else Toast.makeText(//иначе вывод сообщения
                            this,
                            getString(R.string.you_havent_entered_anything),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else Toast.makeText(this, "", Toast.LENGTH_SHORT).show()//иначе вывод сообщения

            } else Toast.makeText(//иначе вывод сообщения
                this,
                getString(R.string.you_havent_entered_anything),
                Toast.LENGTH_SHORT
            ).show()
        }
        }

    }
    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1 //код запроса
        var Photo : Bitmap? = null//дефолт для фоток
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {//результат
        super.onActivityResult(requestCode, resultCode, data)//род класс
        if ((requestCode== AddTable1.REQUEST_IMAGE_CAPTURE)&&(resultCode== RESULT_OK)){//проверка
            val bitmap= data?.extras?.get("data") as Bitmap//запись в неизменую переменую данных
            AddTable1.Photo = bitmap//запись в переменную для фоток
            val imageViewPhoto = findViewById<ImageView>(R.id.imageViewPhoto)//переменная для показа фоток
            imageViewPhoto.setImageBitmap(bitmap)//умная функция которая принимает на вход данные и выводит их
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