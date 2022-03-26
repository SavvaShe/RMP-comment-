//import java.lang.IllegalArgumentException
//function add (el: T): Bool вставляет элемент в конец списка;
//fun delete(): Bool удаляет элемент с конца списка
//fun print():Unit печатает все элементы
class MyList<T> {
    private var arr = arrayOfNulls<Any?>(0) as Array<T?>//проверка массива
    fun add(el: T): Boolean {
        var ret = false//дефолт
        try {//ловим ошибки
            arr = arr.copyOf(arr.size + 1)//добавляем +1 к размеру массива
            arr[arr.lastIndex] = el //элемент в конец списка
            ret = true//ошибок нет
        } catch(e: Exception) {//вывод ошибок
            println("exception: ${e.message}")
            ret = false//ошибки есть
        }
        return ret// возвращаем если смогли тру,если есть ошибка фолз
    }
    fun delete(): Boolean {//удаляем элемент с конца
        var ret = false//дефолт
        try { //ловим ошибки
            arr = arr.copyOfRange(0, arr.lastIndex)
            ret = true//если все гуд то тру
        } catch(e: Exception) {
            if(e is java.lang.IllegalArgumentException) {//проверка есть ли элементы
                println("list is null");//вывод сообщения
            } else {
                println("exception: ${e.printStackTrace()}")//показываем где возникла проблема и выводим в консоль
            }
            ret = false//поймали ошибку - фолз
        }
        return ret// возвращаем если смогли тру,если есть ошибка фолз
    }
    fun print(): Unit {
        arr.iterator().forEach {//бежим по списку interator- смотрим последовательно
            println(it)//выводим
        }
    }
    fun eraseAll(): Unit {
        arr = arrayOfNulls<Any?>(0) as Array<T?>//очищаем список с помощью arrayOfNulls
    }
}

fun main() { //мейн
    val obj = MyList<Int>()//обЪект класса
    //просто вызов методов
    obj.add(3)
    obj.add(4)
    obj.delete()
    obj.delete()
    obj.delete()
    obj.add(5)
    obj.print()
    obj.eraseAll()
    obj.add(7)
    obj.print()

}