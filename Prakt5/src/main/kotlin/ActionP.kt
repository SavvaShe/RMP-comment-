import java.util.*
//аналогично Action B
data class ActionP(var Bicycle: ActionB,
                   var ListParticipant:ListParticipant,
                   var PInput:PInput,
                   var list:MutableList<Pair<String,(Participant)->Comparable<Any>>>,
                   var searchlist:MutableList<Pair<String,(Participant)->String>>):CheckItemMenu(){
    //private var listOfP : MutableList<Participant> = mutableListOf()
    override fun See() {
        var i = 1
       /* listOfP.forEach{
            var B = it.BikeType
            println("[$i] ${it.Id} ${it.F} ${it.I} ${it.O}  Тип велосипеда: ${listOfB.find{it.Id.toString() == B}?.BicyclesType} Стаж: ${it.Experience}")
            i++
        }
        if(listOfP.isNullOrEmpty()){
            println("База данных пуста")
        }*/
        ListParticipant.getParticipantList().forEach {
            println("[$i] ${it.Id} ${it.F} ${it.I} ${it.O}  Тип велосипеда: ${it.IdBicycleType} Стаж: ${it.Experience}")
            i++
        }
            //println("${it.Id} Название товара : ${it.NameProduct} , Цена товара: ${it.priceProduct} , Описание: ${it.descriptionProduct}, Категория: ${it.IdCategory}")}
    }

    override fun Delete() {
        var getdeleteobg = CheckIndex()
        if (getdeleteobg!=null)
            ListParticipant.Delete(getdeleteobg)
       /* if(listOfP.isNullOrEmpty()){
            println("База данных пуста")
        }else{
            See();
            var inputIndex=0
            var Res = false
            while (!Res) {
                println("Выберите номер записи для удаления")
                inputIndex = readLine()?.toIntOrNull()!!
                if (inputIndex != null) {
                    if((inputIndex>0)||(inputIndex<listOfP.size)){
                        var B = listOfP[inputIndex].BikeType
                        println("Удаляемая  строка:  ${listOfP[inputIndex].Id} ${listOfP[inputIndex].F} ${listOfP[inputIndex].I} ${listOfP[inputIndex].O}  Тип велосипеда: ${listOfB.find{it.Id.toString() == B}?.BicyclesType} Стаж: ${listOfP[inputIndex].Experience} ")
                        listOfP.remove(listOfP[inputIndex-1])
                        println("Запись успешно удалена")
                        Res =true
                    }else { println("Записи с таким номером не существует")}
                }
            }
        }*/
    }

    override fun CheckIndex(): UUID? {
        See()
        println("Выберите над каким элементом списка вы хотите совершить действие")
        var getindex = readLine()?.toIntOrNull()
        if (getindex!=null) {
            var result: UUID? = null
            var ee = ListParticipant.getParticipantList().filterIndexed{ index,_ ->(index==getindex)}
            result = ee.first().Id
            return if (result!=null) result else null
        }
        return null
    }
    override fun Sort() {
        list.forEach{ println(it.first)}
        var getLine2 = readLine()!!.toIntOrNull()
        if (getLine2!=null) {
            println("Выберите тип сортировки \n 1.По возрастанию \n 2.По убыванию")
            var getLine = readLine()!!.toIntOrNull()
            when (getLine) {
                1 -> ListParticipant.getParticipantList().sortBy(list[getLine2].second)
                2 -> ListParticipant.getParticipantList().sortByDescending(list[getLine2].second)
            }
            See()
        }
    }

    override fun Add() {
        var getobg = PInput.Input(Bicycle)
        if (getobg!=null) ListParticipant.Add(getobg)
    }

    override fun Search() {
        println("Выберите элемент поиска")
        searchlist.forEach { println(it.first) }
        var getindex = readLine()?.toIntOrNull()
        if (getindex!=null) {
            var getstr = readLine()
            if (getstr!=null) {
                var takeProp = searchlist[getindex].second
               // var ListA = ListParticipant.ParticipantList.filter{ takeProp(it).contains(getstr) }.toList()
                println("${ListParticipant.getParticipantList().filter{ takeProp(it).contains(getstr) }}")
            }
        }
    }


    override fun Edit() {
        var geteditobg = CheckIndex()
        var newnameobg = PInput.Input(Bicycle)
        if (newnameobg!=null) ListParticipant.Edit(Pair(newnameobg,geteditobg))
    }
}