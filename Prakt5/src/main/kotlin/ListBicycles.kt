import java.util.*
//список велосипедов
class ListBicycles { // лист велосипдов
     private var BicyclesList:MutableList<Bicycles> = mutableListOf()//опять создаем приват и завписываем коллекцию
     fun  getBicyclesList(): MutableList<Bicycles> {return BicyclesList}//получить списка велосипедов

        fun Add(Bicycles:Bicycles,BicyclesList:MutableList<Bicycles>){//добавить велосипед
            BicyclesList.add(Bicycles)
        }
        fun Delete(id: UUID?, BicyclesList:MutableList<Bicycles>){//удаление
            var getobjdelete = BicyclesList.find { it.Id == id }//поиск нужного когда совпадает запись в объект удаления
            BicyclesList.remove(getobjdelete)//само удаление
        }
        fun Edit(EditbleBicycles: Pair<Bicycles?, UUID?>,BicyclesList:MutableList<Bicycles>):Bicycles?{//редактирование
            if (EditbleBicycles.first != null && EditbleBicycles.second != null) {//проверки что оба элемента пары нул
                var getchekobg = BicyclesList.find { it.Id == EditbleBicycles.second }//поиск нужного
                getchekobg?.BicyclesType = (EditbleBicycles.first)!!.BicyclesType
            }
            return null//в ином случае возвращает нул
        }
}