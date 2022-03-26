import java.util.*//импорт всего

open class // открытый класс
ActionB(           var ListBicycles:ListBicycles,//АсtionB- название класса в скобках передаваемые параметры Список велосипедов
                   var BInput:BInput,//введенный тип велосипеда
                   var list:MutableList<Pair<String,(Bicycles)->Comparable<Any>>>,//MutableList<T> - это список с операциями записи в данном случае используется Pair – сохранить пару любых значений
                   var searchlist:MutableList<Pair<String,(Bicycles)->String>>):CheckItemMenu(){// аналогично с предыдущим,так же в этой строке указываю от чего наследую(абстрактный классCheckItemMenu())
    //var listOfB : MutableList<Bicycles> = mutableListOf()
        //отcоединить
    override fun Sort() {//переопределенная функция полученная от родителя функция сортировки(если по названию не дошло:))
        println("Выберите тип сортировки \n 1.По возрастанию \n 2.По убыванию")//вывод сообщения \n - перенос строки
      //  var Res = false
      //  while (!Res) {
            var getLine = readLine()?.toIntOrNull()// создал с помощью var ИЗМЕНЯЕМУЮ переменную в которую записанываю ввод с конссоли,так же тут проверка на тип инт или нул
            if (getLine != null) {//проверка если в веденную переменную записался не нул то выполняю условие ниже
                when (getLine) {//чтобы не использовать много ифов когда переменная равна значению выполнится условие
                    1 -> {
                        //listOfB.sortBy{it.BicyclesType}
                        //Res= true
                        ListBicycles.getBicyclesList().sortBy(list[0].second)//ссылаюсь на род класс ListBicycles беру функцию getBicyclesList() и сортирую с помощью функции
                        //sortBy() помогает мне сортировать множественный список на месте по определенному полю(по возрастанию). .second второе значение пары
                        println("Сортировка выполнена успешно")//вывод сообщения
                    }
                    2 -> {
                       // listOfB.sortByDescending {it.BicyclesType}
                        //Res= true
                        ListBicycles.getBicyclesList().sortByDescending(list[0].second)//тоже самое что и выше только сортирова по убыванию из-за функцииsort ByDescending
                        println("Сортировка выполнена успешно")//вывод сообщения
                    }
                    else -> {println ("Неверно выбрана сортировка")}// если любое другое число кроме 1 или 2 выведет сообщение
                }
            }
                //   }
        See()//вызов функции просмотра
    }
    override fun Delete() {//опять переопределение функции удления
        var getdeleteobg = CheckIndex()//вызов функции проверки
        if (getdeleteobg!=null)//елси не нул то идет удаление с помощью род класса
            ListBicycles.Delete(getdeleteobg,ListBicycles.getBicyclesList())
        /*if(listOfB.isNullOrEmpty()){
            println("База данных пуста")
        }else{
            See();
            var inputIndex=0
            var Res = false
            while (!Res) {
                println("Выберите номер записи для удаления")
                inputIndex = readLine()?.toIntOrNull()!!
                if (inputIndex != null) {
                    if((inputIndex>0)||(inputIndex<listOfB.size)){
                        println("Удаляемая  строка:   ${listOfB[inputIndex-1].Id} Тип велосипеда: ${listOfB[inputIndex-1].BicyclesType}")
                                listOfB.remove(listOfB[inputIndex-1])
                                println("Запись успешно удалена")
                                Res =true
                    }else { println("Записи с таким номером не существует")}
                }
            }
        }*/
    }
//UUID? либо уникальный ид либо нул
    override fun CheckIndex(): UUID? {//функция проверки индекса UUID (умная вещь) - создание уникального айди элемента(создание происходит не здесь)
        See()//вызов функции просмотра
        println("Выберите элемент списка")//вывод сообщения
        var getindex = readLine()?.toIntOrNull()//очередная проверка нул или инт
        if (getindex!=null) {//если не нул то
            var result: UUID? = null//UUID такой, что сам я ему значение задавать не должен, там автоматом оно получается, однако инициализировать его надо, вот я и присваиваиваю ему нулл - единственное возможное значение, которое я сам могу присвоить
            ListBicycles.getBicyclesList().forEachIndexed { index, id ->//род класс.используемая функция.спец функция пройти по списку и получить индекс
                if (index == getindex) { //если индекс равен нашему числу то
                    result = id.Id//перезапись в результат
                    println(result)//печать результата
                }
            }
            return result//возвращение результата
        }
        return null //возвращение нул
    }
    override fun Edit() {//переопределение функции редактирование
        var geteditobg = CheckIndex() //проверка индекса
  ю      var newnameobg = BInput.Input() //введенный тип
        if (newnameobg!=null) ListBicycles.Edit(Pair(newnameobg,geteditobg),ListBicycles.getBicyclesList())//если не нул то использование функции род класа добваление
      /*  if(listOfB.isNullOrEmpty()){
            println("База данных пуста")
        }else{
         See();
        var inputIndex=0
            var Res = false
        while (!Res) {
            println("Выберите номер записи для редактирования")
            inputIndex = readLine()?.toIntOrNull()!!
            if (inputIndex != null) {
                if((inputIndex>0)||(inputIndex<listOfB.size)){
                println("Изменяемая строка:   ${listOfB[inputIndex-1].Id} Тип велосипеда: ${listOfB[inputIndex-1].BicyclesType}")
                println("Введите тип велосипеда")
                val str  = readLine()
                if (str!=null) {
                    if (checkType(str)) {
                        listOfB[inputIndex-1].BicyclesType = str
                        println("Запись успешно отредактирована")
                        Res =true

                    }
                }
            }else { println("Записи с таким номером не существует")}
            }
        }
        }*/
    }
    override fun Search() {//переопределение функции поиска
        println("Выберите элемент поиска")//вывод сообщения
        searchlist.forEach { println(it.first) }//пробегаемся по списку и когда находим нужный элемент печатаем его
        var getindex = readLine()?.toIntOrNull()//проверка на нул или инт
        if (getindex!=null) {//если не нул то условие
            var getstr = readLine()//чтение
            if (getstr!=null) {//если не нул то условие
                var takeProp = searchlist[getindex].second//записть элемента с индексом
                println(ListBicycles.getBicyclesList().filter{ takeProp(it).contains(getstr) }.toString())//печать с помощью род класса и вывод нужного с помощью фильра так же с помощью toString() преобразовнаие в строку
            }
        }
        /*println("Введите тип велосипеда который хотите найти:")
        var str = readLine()
        if (str!=null) {
            if(listOfB.find { it.BicyclesType == str }?.BicyclesType!=null) {
                println("[${listOfB.indexOf(listOfB.find { it.BicyclesType == str })}] ${listOfB.find { it.BicyclesType == str }?.Id} " +
                        "Тип велосипеда: ${listOfB.find { it.BicyclesType == str }?.BicyclesType}"
                )
            }else println("Такого ти па велосипед не найден не найден")
        }*/
    }
  override  fun Add(){//добавление
      var getobg = BInput.Input()//ввод
      if (getobg!=null) ListBicycles.Add(getobg,ListBicycles.getBicyclesList())//еслти не нул добавить
    }
    override fun See() {//просмотр
        var i = 1//счетчик
      ListBicycles.getBicyclesList().forEach{//пробегаемся по списку
          println("[$i] ${it.Id} Тип велосипеда: ${it.BicyclesType}")//печать id и тип велика
            i++//увеличение счетчика
        }
        if(ListBicycles.getBicyclesList().isNullOrEmpty()){//проверка или нул или значение
            println("База данных пуста")//вывод сообщенния
        }
    }
}