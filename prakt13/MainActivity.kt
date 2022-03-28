 package com.example.prakt13
import android.graphics.Region
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

import java.math.BigInteger

//64

@Entity
data class CountryRoom( //описание таблички
    @ColumnInfo(name="name") val name: String,//имя
    @ColumnInfo(name="regionalBloc") val regionalBloc: String?,//региональный блок
    @ColumnInfo(name="capital")val capital: String,//столица
    @ColumnInfo(name="region")val region: String//регион
){
    @PrimaryKey(autoGenerate = true)//айди - первичный ключ
    var id: Int=0
}
@Dao//описание команд
interface CountryDao{
    @Delete//удаление
    fun delete(countryRoom: CountryRoom)
    @Query("SELECT * FROM CountryRoom")//показ всего
    fun getAll(): List<CountryRoom>
    @Insert
    fun insertAll(vararg country:CountryRoom)//вставка записей в бд
    @Query("DELETE FROM CountryRoom")//удалить все
    fun dropAll()

}
@Database(entities = [CountryRoom::class], version = 1)//мы делаем бд закрытой,только наследники могут обращаться с ФУНКЦИЯМИ,но не могут изменить логику таблицы
abstract class AppDatabase: RoomDatabase() {//название бд и делаем абстрактный класс
    abstract fun countryDao(): CountryDao//делаем функцию котрая наследует интерфейс
}

data class Country(//класс данных с нул по дефолту
    var name: String? = null,
    var regionalBloc: String? = null,
    var capital: String? = null,
    var region:String?=null
)


public interface APIPublicCountry{//интерфейс для взаимодействия с апи
    @GET("{regionalBloc}?access_key=abff3bf73fe29409e8ea84033be2aa50")//аннотация для GET-запроса к апи по указанному адресу
    fun getCountry(@Path("regionalBloc") regionalBloc:String): Call<MutableList<Country>>//функция для осуществления этого самого запроса, аннотация @Path указывает параметр, который будет передан в фигурные скобки для аннотации GET(строчкой выше в адресе есть фигурные скобки с таким же содержимым regionalBloc)

}
class CountryAdapter(private val country:MutableList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){//адаптер
    fun getResult() = country//принять результат
    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun fill(NameCountry:String,Capital:String,Region:String) {//запись в вьюхи
            itemView.findViewById<TextView>(R.id.textViewNameCountry).text = NameCountry//страна
            itemView.findViewById<TextView>(R.id.textViewCapital).text = Capital//столица
            itemView.findViewById<TextView>(R.id.textViewRegion).text = Region//регион
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country,parent,false)//LayoutInflater - это класс, используемый для создания представлений из файла ресурс макета
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = country[position]
        var name = country.name//имя
        var capital = country.capital//столица
        var region = country.region//регион
        if ((name!=null)&&(capital!=null)&&(region!=null)) {//если все не нул закидываем в запись
            holder.fill(name,capital,region)//вот этой штукой
        }
       /* holder.textViewNameCountry.text=country[position].name
        holder.textViewCapital.text=country[position].capital
        holder.textViewRegion.text=country[position].region*/
       // holder.textViewArea.text=country[position].area

    }

    override fun getItemCount() = country.size//количесто пунтков
}

class MainActivity : AppCompatActivity() {//мейник
    override fun onCreate(savedInstanceState: Bundle?) {//переопределение
        super.onCreate(savedInstanceState)//род класс
        setContentView(R.layout.activity_main)//ресурсы для пользовательского интерфейса
        val retrofit = Retrofit.Builder().baseUrl("http://api.countrylayer.com/v2/regionalbloc/").//ссылка дана по заданию
        addConverterFactory(GsonConverterFactory.create()).build()//конвектор джисона
        val publicCountry = retrofit.create(APIPublicCountry::class.java)//страна
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)//кнопка поиска
        val ListCountry = findViewById<RecyclerView>(R.id.ListCountry)//список стран
        val layoutManager = LinearLayoutManager(this)
        ListCountry.layoutManager = layoutManager //запись в список из ресуросов
        val editTextRegionalAcronym = findViewById<EditText>(R.id.editTextRegionalAcronym)//добавление текста
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "country").build()//сокразение для обращения к бд
        val countryDao = db.countryDao()//наши функции бд

        buttonSearch.setOnClickListener {//кнопка поиска
            if (editTextRegionalAcronym.text!=null){//если введен не нул
                if (Regex("[A-Z]+").matches(editTextRegionalAcronym.text.toString())) {//проверка с помощью регулярного выражения что введен текст
//                    val result = mutableListOf(
//                        Country("land Islands", null, "Mariehamn", "Europe"),
//                        Country("Austria", null, "Vienna", "Europe"),
//                        Country("Belgium", null, "Brussels", "Europe")
//                    )
//                    if (result != null) {
//                        GlobalScope.launch(Dispatchers.IO) {
//                            countryDao.dropAll()
//                            countryDao.insertAll(
//                                *result.toList()
//                                    .map { CountryRoom(it.name!!, null, it.capital!!, it.region!!) }
//                                    .toTypedArray()
//                            )
//                        }
//                        val adapter = CountryAdapter(result)
//                        ListCountry.adapter = adapter
//                    }

                     publicCountry.getCountry(editTextRegionalAcronym.text.toString()).enqueue(object: Callback<MutableList<Country>>{// обращение к апи
                    override fun onResponse( //переопределение функции обработки ответа от сервера на  запрос к апи
                        call: Call<MutableList<Country>>,// это стандартные параметры для onResponse
                        response: Response<MutableList<Country>>
                    ) {
                        val result = response.body()//138 запись ответа от сервера в переменную
                        if (result!=null) {//если не нул
                            GlobalScope.launch(Dispatchers.IO){//жизнь корутины
                                countryDao.dropAll()//удаляем все
                                countryDao.insertAll(*result.toList().map { CountryRoom(it.name!!,null,it.capital!!,it.region!!) }.toTypedArray())//вставляем записи в бд
                            }
                            val adapter = CountryAdapter(result)//адаптер
                            ListCountry.adapter = adapter//вывод
                        }

                    }

                    override fun onFailure(call: Call<MutableList<Country>>, t: Throwable) {//если ошибка
                        Toast.makeText(this@MainActivity,getString(R.string.error),Toast.LENGTH_SHORT).show()//вывод сообщения
                    }

                })
                }else Toast.makeText(this,getString(R.string.error_regional_acronym),Toast.LENGTH_SHORT).show()//вывод сообщения
            }else Toast.makeText(this,getString(R.string.nothing_entered),Toast.LENGTH_SHORT).show()//вывод сообщения

        }




    }
}