import java.util.*
//все аналогично классу BINPUT
class PInput():InterValid {
    fun Input(Bicycles:ActionB): Participant? {
       // var Res = false
       // while(!Res) {
            println("Введите фамилию")
            var strF = readLine()
            if (strF!=null) {
                if (checkFIO(strF)) {
                    println("Введите имя")
                    var strI = readLine()
                    if (strI!=null) {
                        if (checkFIO(strI)) {
                            println("Введите отчество")
                            var strO = readLine()
                            if (strO!=null) {
                                if (checkFIO(strO)) {
                                    println("Выберите тип велосипеда")
                                    Bicycles.ListBicycles.getBicyclesList().forEach { println("${it.BicyclesType}: ${it.Id}") }
                                    var strT = UUID.fromString(readLine())
                                    if (strT != null) {
                                        if (Bicycles.ListBicycles.getBicyclesList().find { it.Id == (strT) } != null) {
                                            println("Введите стаж")
                                            var strS = readLine()?.toInt()
                                            if (strS != null) {
                                                return Participant(strF,strI,strO,(Bicycles.ListBicycles.getBicyclesList().find { it.Id == (strT) })!!.BicyclesType,strS)
                                                /*var Id = UUID.randomUUID()
                                                var P = Bicycles(Id, str)
                                                P.BicyclesType = str
                                                listOfB.add(B)
                                                Res = true
                                                println("Поле успешно добавлено")*/
                                            }else return null
                                        }else return null
                                    }else return null
                                }else return null
                            }else return null
                        }else return null
                    }else return null
                }else return null
            }else return null
      //  }

    }
}