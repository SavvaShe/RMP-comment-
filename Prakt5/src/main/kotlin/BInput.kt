import java.util.*

class BInput():InterValid {//наследуем интерфейс
    fun Input(): Bicycles? {//ввод
        //var Res = false
       // while (!Res) {
            println("Введите тип велосипеда")//печать сообщения
            var str = readLine()//чтение
            if (str != null) {//если введен не нул
                if (checkType(str)) {//импользуем проверку если гуд то выполняем условие.
                    var Id = UUID.randomUUID()//генерим рандомный айди
                    //var B = Bicycles(Id,str)
                    return Bicycles(str)//возвращаем введеную строку
                    // B.BicyclesType = str
                    //listOfB.add(B)
                  //  Res = true
                    //println("Поле успешно добавлено")
                } else return null //println("Вы неправильно ввели строку")//выкидываем нул
            } else return null//println("Вы ничего не ввели")//выкидываем нул
      //  }
    }
}