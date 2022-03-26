abstract class CheckItemMenu :InterAdd,InterCheckIndex, InterEdit,InterSearch,InterSort,InterSee,InterDelete{//абстрактный класс который наследует интерфейсы
    var listfunc = mutableListOf<Pair<String,()->Unit>>()//коллекция пара строка + функция
    init {//инициализация
        listfunc.add(Pair("[0] Просмотр      ") { this.See() })//по названию очевидно..
        listfunc.add(Pair("[1] Добавление    ") { this.Add() })
        listfunc.add(Pair("[2] Редактирование") { this.Edit() })
        listfunc.add(Pair("[3] Удаление      ") { this.Delete() })
        listfunc.add(Pair("[4] Сортировка    ") { this.Sort() })
        listfunc.add(Pair("[5] Поиск         ") { this.Search() })
        listfunc.add(Pair("[6] Выход         ") { Unit })
    }
}
