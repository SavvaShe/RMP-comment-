package com.example.prakt7

import android.app.Notification
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.*
import kotlinx.coroutines.launch
import android.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope


enum class EnumRes{ //перечисления
    AGreater,//положительное
    ALess,//отрицательное
    ABEquallyZeroBLessZero,//1 равно 0 ,а 2 <=0
    AGreaterZeroBEquallyZero,//1 = 0,2>0
    ALessZeroBEquallyZero,//1>0,2=0
    AEquallyZeroBGreaterZero,//1<0,2=0
    OverflowDouble,//переполнение
    NoValues//нет значений
}
@Entity //бд описание таблицы
data class resultRoom(
    @ColumnInfo(name="res")var res:String,//результат
){
    @PrimaryKey(autoGenerate = true)//первичный ключ
    var id: Int=0

}
@Dao//методы бд
interface  resultDao{
    @Query("SELECT * FROM resultRoom")
    fun getAll():List<resultRoom>//вывести все
    @Insert
    fun inserAll(vararg result: resultRoom)//обычный инсерт
    @Query("DELETE FROM resultRoom")
    fun dropAll()//удалить все

}
@Database(entities = [resultRoom::class],version = 1)//Аннотацией Database помечаем основной класс по работе с базой данных. Этот класс должен быть абстрактным и наследовать resultRoom.
    abstract class AppDatabase:RoomDatabase(){//абстрактный клас с помощью которого будет взаимодейстиве с бд
    abstract fun resultDao():resultDao
    }


class model(){//модель
    var resNumber:Double = 0.0//заводим дабл
    var res:EnumRes = EnumRes.NoValues//обращаемся к наши перечислением
    constructor(number:Double,resStr:EnumRes):this(){//конструктор
     resNumber = number//присаиваем
        res= resStr//присваиваем
    }
    override fun equals(other: Any?): Boolean =  (other is model) && other.resNumber == resNumber && other.res == other.res//проверка равенства
}
class SubNumber {//сложение
 fun subNumber(numberA:Double, numberB:Double):model{
     //var result = ""
     var model = model()//объект класса
     if (numberA > 0)  {//проверка на положительность первого числа
         val resultNum = -numberB/numberA//деление
         if (!numberA.equals(Double.MIN_VALUE) && !numberA.equals(0.0)) {//две проверки на не равенство
             model.resNumber = resultNum//присваивание
             model.res = EnumRes.AGreater//положительно
         /* result =  "%s%s%s".format(
                 getString(R.string.minusInf),
                 resultNum.toString(),
                 getString(R.string.squareBracketClose)
             )*/
         }else model.res=EnumRes.OverflowDouble//переполнение
     } else if (numberA < 0)  {//если 1 число отрицательное
         val resultNum = -numberB/numberA//деление
         if (!numberA.equals(Double.MIN_VALUE) && !numberA.equals(0.0)) {//проверки на не равенство
             model.resNumber = resultNum//присваивание
             model.res = EnumRes.ALess//отрицательно
           /*  result = "%s%s%s".format(
                 getString(R.string.squareBracketOpen),
                 resultNum.toString(),
                 getString(R.string.PlusInf)
             )*/
         }else model.res=EnumRes.OverflowDouble//переполнение
     } else if ((numberA == 0.0) && ((numberB == 0.0)||(numberB<0))) {//если 1 равно нулю а 2 меньше или равно
         model.resNumber = 0.0//ноль
         model.res = EnumRes.ABEquallyZeroBLessZero//1 - 0
        // result= getString(R.string.AllInf)
     } else if ((numberA == 0.0) && (numberB > 0)) {
         model.resNumber = 0.0//0
         model.res = EnumRes.AEquallyZeroBGreaterZero //1 = 0,2>0
        // result = getString(R.string.noSolutions)
     } else if ((numberA > 0) && (numberB == 0.0)) {
         model.resNumber = 0.0
         model.res = EnumRes.AGreaterZeroBEquallyZero//1>0,2=0
       //  result = getString(R.string.MinusInfZero)
     } else if ((numberA < 0) && (numberB == 0.0)) {
         model.resNumber = 0.0
         model.res = EnumRes.ALessZeroBEquallyZero//1<0,2=0
        // result = getString(R.string.InfZero)
     }
     return model//возвращаем результат из перечислений
 }
}

open class MainActivity : AppCompatActivity() {//мейн активити
    private lateinit var textViewResult: TextView// lateinit мы обещаем что инициализируем,но потом textView - вывод текста
    override fun onSaveInstanceState(outState: Bundle) {// Здесь есть специальная outState – туда можно записывать значения, которые меня будут интересовать при восстановлении
        super.onSaveInstanceState(outState)//ключевое слово super, которое обозначает суперкласс, т.е. класс, производным от которого является текущий класс
        outState.putString(RES,textViewResult.text.toString())//сохранение
    }

    override fun onCreate(savedInstanceState: Bundle?) {//OnCreate – когда activity создаётся savedInstanceState используется для сохранения значений при повороте экрана
        super.onCreate(savedInstanceState)//суперкласс
        setContentView(R.layout.activity_main)//вызываем пользовательский интерфейс  с помощью setContentView кот лежит в папке res/layout
        val db =Room.databaseBuilder(applicationContext, AppDatabase::class.java, "result").fallbackToDestructiveMigration().build()//бд создаем
        val resultDao = db.resultDao()//заимели функции бд
        GlobalScope.launch(Dispatchers.IO) {//GlobalScope — жизненные рамки для корутины.Dispatchers.IO : работа в сети или чтение и запись из файлов — любой ввод и вывод, как следует из названия
            val uytwer = resultDao.getAll()//показать все
            if (uytwer!=null){//если есть что показывать
            launch(Dispatchers.Main) {//Dispatchers.Main : обязательный диспетчер для выполнения событий, связанных с пользовательским интерфейсом, в основном потоке Android или потоке пользовательского интерфейса.
                findViewById<TextView>(R.id.textViewResult).text =uytwer[0].res//findViewById  - позволяет найти объект типа View(Всё что может отображаться на ativity) по идентификатору.
            }}
        }
                 val editTextNumberB = findViewById<EditText>(R.id.editTextNumberB)//опять поиск вьюхи только уже числа
                 val editTextNumberA = findViewById<EditText>(R.id.editTextNumberA)//опять поиск вьюхи только уже числа
                 textViewResult = findViewById(R.id.textViewResult)//опять поиск вьюхи только результата
                 if(savedInstanceState!=null){//если сохранение не нул то
                     textViewResult.text = savedInstanceState.getString(RES)//записываем полученый результат
                 }
                 val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)//кнопка калькулятор
                 val buttonSend = findViewById<Button>(R.id.buttonSend)//кнопка отправить
                 buttonCalculate.setOnClickListener{//при нажатии на калькулятор запись в а и б
                     val numberAText = editTextNumberA.text
                     val numberBText = editTextNumberB.text

                     val numberA = numberAText.toString().toDoubleOrNull()//проверка чисел на тип или нул
                     val numberB = numberBText.toString().toDoubleOrNull()
                     if (numberA!=null) {//если не нул а
                         if (numberB != null) {//если не нул б
                             val subNumber = SubNumber()//объект класса
                             val model =subNumber.subNumber(numberA,numberB)//передаем в функцию наши параметры
                             if (model.res==EnumRes.AGreater){//если положительно
                                 textViewResult.text="%s%s%s".format(//приводим результат к формату %s = строка
                                     getString(R.string.minusInf),//минус
                                     model.resNumber.toString(),//интервал
                                     getString(R.string.squareBracketClose))//квадратная скобка закрыта
                                 GlobalScope.launch(Dispatchers.IO) {
                                     resultDao.dropAll()//стираем все
                                     val result = mutableListOf(textViewResult.text.toString())
                                     resultDao.inserAll(//ввод
                                         *result.toList().
                                         map{resultRoom(it)}.toTypedArray()//запись в базу
                                         )
                                 }//аналогично со всеми интервалами ниже
                             }else if (model.res==EnumRes.ALess){
                                 textViewResult.text="%s%s%s".format(
                                     getString(R.string.squareBracketOpen),
                                     model.resNumber.toString(),
                                     getString(R.string.PlusInf))
                                 GlobalScope.launch(Dispatchers.IO) {
                                     resultDao.dropAll()
                                     val result = mutableListOf(textViewResult.text.toString())
                                     resultDao.inserAll(
                                         *result.toList().
                                         map{resultRoom(it)}.toTypedArray()
                                     )
                                 }
                             }else if (model.res==EnumRes.AEquallyZeroBGreaterZero){
                                 textViewResult.text=getString(R.string.noSolutions)
                                 GlobalScope.launch(Dispatchers.IO) {
                                     resultDao.dropAll()
                                     val result = mutableListOf(textViewResult.text.toString())
                                     resultDao.inserAll(
                                         *result.toList().
                                         map{resultRoom(it)}.toTypedArray()
                                     )
                                 }
                             }else if (model.res==EnumRes.ABEquallyZeroBLessZero){
                                 textViewResult.text=getString(R.string.AllInf)
                                 GlobalScope.launch(Dispatchers.IO) {
                                     resultDao.dropAll()
                                     val result = mutableListOf(textViewResult.text.toString())
                                     resultDao.inserAll(
                                         *result.toList().
                                         map{resultRoom(it)}.toTypedArray()
                                     )
                                 }
                             }else if (model.res==EnumRes.AGreaterZeroBEquallyZero){
                                 textViewResult.text=getString(R.string.MinusInfZero)
                                 GlobalScope.launch(Dispatchers.IO) {
                                     resultDao.dropAll()
                                     val result = mutableListOf(textViewResult.text.toString())
                                     resultDao.inserAll(
                                         *result.toList().
                                         map{resultRoom(it)}.toTypedArray()
                                     )
                                 }
                             }else if (model.res==EnumRes.ALessZeroBEquallyZero){
                                 textViewResult.text=getString(R.string.InfZero)
                                 GlobalScope.launch(Dispatchers.IO) {
                                     resultDao.dropAll()
                                     val result = mutableListOf(textViewResult.text.toString())
                                     resultDao.inserAll(
                                         *result.toList().
                                         map{resultRoom(it)}.toTypedArray()
                                     )
                                 }
                             }else if (model.res==EnumRes.OverflowDouble){
                                 Toast.makeText(this, getString(R.string.OverflowDouble), Toast.LENGTH_LONG).show()//переполнение вывод окошка диалога
                             }
                         } else {
                             Toast.makeText(this, getString(R.string.ErrB), Toast.LENGTH_LONG).show()//вывод окошка диалога
                         }

                     } else {
                         Toast.makeText(this, getString(R.string.ErrA), Toast.LENGTH_LONG).show()//окошка диалога
                     }
                 }
                 buttonSend.setOnClickListener{//описание кнопки принятие.ждем нажатия иии
                     if (textViewResult.text !=null){ //проверка что не нул
                         val intent = Intent()//Для того чтобы работать с новыми активити используется специальный тип Intent().
                         intent.action = Intent.ACTION_SEND//ACTION_SEND — Загружает экран для отправки данных, указанных в намерении.
                         intent.putExtra(Intent.EXTRA_TEXT,textViewResult.text)//текст
                         intent.type="text/plain"//тип
                         val intentCrateChooser=Intent.createChooser(intent,null)// возвращает версию нашего intent,
                         startActivity(intentCrateChooser)//запкускаем активити
                     }else {
                         Toast.makeText(this,"Вы ничего не вычислили",Toast.LENGTH_SHORT).show()//вывод окошка
                     }
                 }
             }
    companion object{
        const val RES = "RES"//константа на ресурсы
    }
}