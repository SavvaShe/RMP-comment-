//База данных велоклуба. Поля: ФИО, тип велосипеда (MTB и др.), стаж участия в велоклубе.
fun main(args: Array<String>) {//главный файл
    /*val listCommandsMenu1: List<Pair<Int,String>> = listOf(
        Pair(0," Таблица №1"),
        Pair(1," Таблица №2"),
        Pair(2," Выход     "))
    val listCommandsMenu2: List<Pair<Int,String>> = listOf(
        Pair(0," Просмотр      "),
        Pair(1," Добавление    "),
        Pair(2," Редактирование"),
        Pair(3," Удаление      "),
        Pair(4," Сортировка    "),
        Pair(5," Поиск         "),
        Pair(6," Выход         "))
    //val DataBase : TableP =TableParticipant()
    var dbP = ActionP()
    var dbB = ActionB()*/
    var DB = DB()//добавили класс
    var exitkey = DB.OListB//укоротили запись
    var t1 = ActionB(exitkey,//таблица 1
        DB.OBicycles,
        mutableListOf(Pair("Тип велосипеда"){x->x.BicyclesType as Comparable<Any>}),//небольшая лямбда
        mutableListOf(Pair("Поиск по типу велосипеда") { x -> x.BicyclesType}))//как и впредыдущем создаем пару тип и значение
    var t2 =ActionP(t1,//аналогично первой таблице,только значений больше
        DB.OListP,
        DB.OParticipant,mutableListOf(Pair("Фамилия") { x -> x.F as Comparable<Any> },
            Pair("Имя") { x -> x.I as Comparable<Any> },
            Pair("Отчество") { x -> x.O as Comparable<Any> },
            Pair("Стаж") { x -> x.Experience as Comparable<Any> }),
        mutableListOf(Pair("Поиск по фамилии") { x -> x.F },
            Pair("Поиск по имени") { x -> x.I },
            Pair("Поиск по отчеству") { x -> x.O },
            Pair("Поиск по типу велосипеда") { x -> x.IdBicycleType },
            Pair("Поиск по стажу") { x -> x.Experience.toString() }))
    val listCommandsMenu: List<Triple<Int,String,CheckItemMenu>> = listOf(//спсиок начального меню
        Triple(0," Таблица №1",t1),
        Triple(1," Таблица №2",t2),
        Triple(2," Выход     ",t1))
    val mm = MainMenu()//объект класса
    mm.itemMenu(listCommandsMenu)//запуск проги
}