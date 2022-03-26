import java.util.*

open class Id {//открытый класс
   private var _Id:UUID=UUID.randomUUID()//функция для создания уникального рандомного айди
    var Id: UUID
        get() =_Id//получить айди
        set(value) {//отдать айди
            _Id= value
        }
}