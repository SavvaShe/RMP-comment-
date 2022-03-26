open class MainMenu() {
    open fun itemMenu(ListCommandsMenu:List<Triple<Int,String,CheckItemMenu>>){//пункты меню
        //var menu = Menu()
        var exit =false//выход по умолчанию сделаем ложным
    /*    val listItem = listOf("[0] Таблица №1",
            "[1] Таблица №2",
            "[2] Выход     ")
*/
        while (!exit){//пока ложь делаем
            println(Item1)//верхняя граница
            ListCommandsMenu.forEach{//бежим по списку
                println("$Item2[${it.first}]${it.second}$Item3\n$Item1")//печатаем границы
            }
            println (Item)//вывод текста
            var inputItem = readLine()?.toIntOrNull()//читаем пункт
            if (inputItem!=null){//если не нул
                var takeTable =ListCommandsMenu[inputItem].third//берем третье значение
                if(inputItem==0){//если ноль(1 табличка)
                    for(i in 1..20){//проверяем входит ли в диапозон
                        println("")//печать
                    }
                    //menu.tableFirst(dbB,ListCommandMenu2)
                    //var i = 1
                    println(Item4)//нижняя граница
                    takeTable.listfunc.forEach{//бежтм по списку
                        print(Item2)//верх
                        print(it.first)//пункт меню
                        println(Item3)//право
                        println(Item4)//низ
                        //println(println("$Item2${it.first}$Item3\n$Item4"))
                       // i++
                    }
                    println(Item)//пункт
                    var getFunction = readLine()?.toIntOrNull()//чтение
                    if (getFunction != null) {//проверка на не нулл
                        takeTable.listfunc[getFunction].second.invoke()//транслируется н-р a(i_1, ..., i_n)	a.invoke(i_1, ..., i_n)

                    }
                }else if(inputItem==1){//если 1(вторая табличка)
                    //отчистка консоли
                    for(i in 1..20){
                        println("")

                    }
                    //var i = 1

                    println(Item4)//нижняя граница
                    takeTable.listfunc.forEach{//отрисовка
                        print(Item2)
                        print(it.first)
                        println(Item3)
                        println(Item4)
                        //println(println("$Item2${it.first}$Item3\n$Item4"))
                       // i++
                    }
                    println(Item)//пункт
                    var getFunction = readLine()?.toIntOrNull()//чтение
                    if (getFunction != null) {//проверка на не нулл
                        takeTable.listfunc[getFunction].second.invoke()//транслируется н-р a(i_1, ..., i_n)	a.invoke(i_1, ..., i_n)

                        // menu.tableSecond(dbP,ListCommandMenu2)

                } else if(inputItem==2){//если 2 то выход
                    exit=true
                    break//используется для остановки
                }else{
                    println("Такого пункта меню не существует!!")//ошибся пользователь цифрой
                }
            }

        }

    }
    companion object{//костанты для отрисовки
        const val Item = "Для выбора пункта меню напишите число"
        const val Item1 = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
        const val Item2 = "┃         "
        const val Item3 = "         ┃"
        const val Item4 = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
        const val Item5 = "┃         "
        const val Item6 = "         ┃"
    }
}