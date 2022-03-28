package com.example.prakt9

import android.content.ActivityNotFoundException
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

class AddTable1 : AppCompatActivity() {//неаследник  AppCompatActivity()
    override fun onCreate(savedInstanceState: Bundle?) {//переопределение функции создания
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)//род класс
        setContentView(R.layout.activity_add_table1)// ресурсы пользовательский интерфейс
        val Add = findViewById<Button>(R.id.buttonAdd)//записывание в переменуюю
        val buttonAddPhoto = findViewById<Button>(R.id.buttonAddPhoto)//кнопка добавить фото
        val editTextBicycleType = findViewById<EditText>(R.id.editTextBicycleType)//текст типа
        buttonAddPhoto.setOnClickListener {//описание фотки ,ждем один клик
            val intent = Intent()//интент
           // val intent = getIntent()

            //intent.component = null
            //intent.setPackage("com.google.android.GoogleCamera")
           // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            //startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE//работа с акшином из медии
            //if (intent.resolveActivity(packageManager)!=null) {
            try {//ловим ошибки
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }catch(e: ActivityNotFoundException){//выводим их если есть
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            }
            //}
        }
        Add.setOnClickListener {//кнопка добавить
            val typeStr = editTextBicycleType.text.toString()//запись в строкку
            if (editTextBicycleType.text!=null){//если не нул
                if(Valid.checkType(typeStr)){//проверка типа
                    if (Photo!=null) {//если фото не нул
                        val Bicycles_ = Bicycles(typeStr, Photo)//запись пары
                        MainActivity.DB.OListB.Add(//передача в мейн актививити
                            Bicycles_,
                            MainActivity.DB.OListB.getBicyclesList()
                        )
                        editTextBicycleType.text = null//обнуляем
                        Toast.makeText(//вывод сообщения
                            this,
                            getString(R.string.entry_added_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    }else Toast.makeText(this,getString(R.string.you_have_not_added_a_photo),Toast.LENGTH_SHORT).show()//вывод сообщения
                }else Toast.makeText(this,getString(R.string.you_entered_the_wrong_type),Toast.LENGTH_SHORT).show()//вывод сообщения
            }else Toast.makeText(this,getString(R.string.you_havent_entered_anything),Toast.LENGTH_SHORT).show()//вывод сообщения
        }
    }
    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1
        var Photo :Bitmap? = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {//функция результата активити
        super.onActivityResult(requestCode, resultCode, data)//род класс
        if ((requestCode== REQUEST_IMAGE_CAPTURE)&&(resultCode== RESULT_OK)){//проверки то что все ок
            val bitmap= data?.extras?.get("data") as Bitmap//проверка данных и запись их
            Photo = bitmap//запись в переменную для фоток
            val imageViewPhoto = findViewById<ImageView>(R.id.imageViewPhoto)// для показа фоток
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