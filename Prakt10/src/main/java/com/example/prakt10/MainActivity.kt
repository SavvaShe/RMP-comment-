package com.example.prakt10

//импорты
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.math.BigInteger



interface ListenerAnswer { //интерфейс обработчика события получения результата вычислений
    fun receiveAnswer(answer: BigInteger) //объявление функции получения результата вычислений
}
class Calculate { //Класс для вычислений
    var job: Job? = null //переменная для описания фоновой работы (по вычислению результата)
    fun calculate(maxFib: BigInteger): BigInteger { //функция вычисления выражения

        var f1 = 1.toBigInteger() //первое предыдущее число Фибоначчи
        var f2 = 1.toBigInteger() //второе предыдущее число Фибоначчи
        var f = 1.toBigInteger() //следующее число Фибоначчи
        var i = 0.toBigInteger() //счётчик для цикла расчёта искомого выражения
        while (i < (maxFib*2.toBigInteger())-3.toBigInteger()) { //Цикл для расчёта искомого выражения
            f = f1 + f2 //получение следующего числа Фибоначчи
            f1 = f2 //смещение первого предыдущего числа Фибоначчи
            f2 = f //смещение второго предыдущего числа Фибоначчи
            i++ //увеличение счётчика цикла
        }
        return f //возвращение функцией результата искомого выражения
    }

    fun startcalculate(fibrestricttion: BigInteger) = //Функция начала вычисления выражения
        CoroutineScope(Dispatchers.Main).launch { //Запуск корутины для параллельного работе приложения вычисления выражения
            job = launch { //запуск фоновой работы для вычисления выражения
                var fib = calculate(fibrestricttion) //вычисление выражения
                listenerAnswer?.receiveAnswer(fib) //получение результата вычислений
            }
            job?.join() //приостановка корутины до момента выполнения работы по вычислению выражения
        }

    fun stopcalculating() = job?.cancel() //функция остановки работы по вычислению выражения


    var listenerAnswer: ListenerAnswer? = null //объявление и инициализация обработчика события
    fun register(listenerAnswer: ListenerAnswer) { //функция регистрации обработчика события
        this.listenerAnswer = listenerAnswer //обновление обработчика события
    }

    companion object { //объект, к свойствам и методам которого можно обращаться через имя класса
        private var calculate: Calculate? = null //объявление и инициализация переменной типа класса
        fun getInstance(): Calculate { //функция получения экземпляра класса вычислений
            if (calculate == null) //проверка на null
                calculate = Calculate() //присваивание переменной экземпляра класса
            return calculate!! //возврат экземпляра класса
        }
    }
}
class MainActivity : AppCompatActivity(),ListenerAnswer { //класс главной активности, унаследовавший AppCompatActivity и интерфейс обработчика событий
    override fun onCreate(savedInstanceState: Bundle?) { //переопределение функции для инициализации активности
        super.onCreate(savedInstanceState) //инициализация активности
        Calculate.getInstance().register(this) //регистрация обработчика события получения результата вычислений
        setContentView(R.layout.activity_main) //метод для размещения пользовательского интерфейса
        val buttonStart = findViewById<Button>(R.id.buttonStart) //привязка кнопки запуска вычислений к неизменяемой переменной buttonStart
        val buttonStop = findViewById<Button>(R.id.buttonStop) //привязка кнопки остановки вычислений к неизменяемой переменной buttonStop
        buttonStart.setOnClickListener { //обработчик события нажатия на кнопку запуска вычислений
            var fvr = findViewById<EditText>(R.id.editTextNumber).text.toString().toBigIntegerOrNull() //введённое пользователем число
            if (fvr!=null && fvr<= 1000000000.toBigInteger()) { //если введённое значение непустое и не слишком большое
                Calculate.getInstance().startcalculate(fvr) //вызов функции запуска вычислений
            }
            else { //если введённое значение некорректно
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show() //вывод сообщения о некорректном значении
            }
        }
        buttonStop.setOnClickListener{ //обработчик события нажатия на кнопку остановки вычислений
            Calculate.getInstance().stopcalculating() //остановка вычислений

        }
    }
    override fun receiveAnswer(answer: BigInteger) { //переопределение функции получения результата вычислений
        CoroutineScope(Dispatchers.Main).launch { //запуск корутины для вывода результата вычислений
            findViewById<TextView>(R.id.result).text = answer.toString() //вывод результата вычислений в поле вывода
        }
    }
}