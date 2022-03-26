//Создайте функцию, которая по данной функции f : T− > T и числу n
//возвращает функцию f(f(f(...f(x)...), где f вызывается n раз. Здесь
//T – любой тип.
fun main(){//мэйн
    print("Введите число: ")//сообщение
    val xx =readLine()//чтение
    val x = xx?.toIntOrNull()//проверка
    print("Введите n: ")//колчисетво повторений
    val nn = readLine()//чтение
    val n = nn?.toIntOrNull()//проверка
    if ((x!=null)&&(n!=null)) {//если не нул оба числа
        val f = {s:Int->s*s}//лямбда на произведение
        val f2 = {arg:Float->arg+arg}//лябда на сумму
        val result = callNTimes(n,f)//вызов функции
        val result2 = callNTimes(n,f2)(1.2f)//вызов функции для флоат
        val maxInt = result(x)//закидываем результат
        println(if ((maxInt==0)||(maxInt==-2147483648)) "Переполнение Int" else maxInt)//проверяем на переполнение  выводим ответ или сообщение
        println("second: $result2")//для цифр с плавающей точкой
    }else println("Неправильно введено значение")//хоть 1 число введено некоректно
}
fun <T> callNTimes(n:Int,f:(T)->T):(T)->T {//сама функция счета
    return  { x ->
        var res = x //дефолт
        var i = 0//счетчик
        while(n>i){//пока счетчик не меньше нашего числа вызываем функцию
            res= f(res)
            i++
        }
        res
    }
}


