class Menu(): MainMenu() {
    private fun printItemMenuM(ListCommandMenu2:List<Pair<Int,String>>){//рисуем менющку
     println(Item1)
        ListCommandMenu2.forEach{//бежим по списку и отрисовываем
         println("$Item2 [${it.first}]${it.second}$Item3\n$Item1")
     }
        println (Item)//вывод на экран
    }
    companion object{//заводим константы
        const val Item = "Для выбора пункта меню напишите число"
        const val Item1 = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
        const val Item2 = "┃         "
        const val Item3 = "         ┃"
    }
    fun tableFirst(dbB:ActionB,ListCommandMenu2:List<Pair<Int,String>>){//описание таблички 1
        var listOfItemsMenu = mutableListOf(dbB::See,dbB::Add,dbB::Edit,dbB::Delete,dbB::Sort,dbB::Search)//передаем все возможные функции с ней
        var getItem = -1//значение поля по дефолту
        while (getItem!=6){//пока не равно 6 делаем следующее
            printItemMenuM(ListCommandMenu2)//печатаем команды
            getItem = readLine()!!.toInt()//читаем цифорку
            if (getItem<6&&getItem>=0) {//проверка на пункт меню
                listOfItemsMenu[getItem]()//открытие действия с табличкой
            }else if (getItem!=6) println("Такого пункта меню не существует")//выкидываем ошибку
        }

    }
    fun tableSecond(dbP:ActionP,ListCommandMenu2:List<Pair<Int,String>>){
        var listOfItemsMenu = mutableListOf(dbP::See,dbP::Add,dbP::Edit,dbP::Delete,dbP::Sort,dbP::Search)
        var getItem = -1
        while (getItem!=6){//пока не равно 6 делаем следующее
            printItemMenuM(ListCommandMenu2)//печатаем команды
            getItem = readLine()!!.toInt()//читаем цифорку
            if (getItem<6&&getItem>=0) {//проверка на пункт меню
                listOfItemsMenu[getItem]()//открытие действия с табличкой
            }else if (getItem!=6) println("Такого пункта меню не существует")//выкидываем ошибку
        }

    }
}