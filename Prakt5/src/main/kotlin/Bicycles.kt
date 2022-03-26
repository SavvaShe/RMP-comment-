import java.util.*

open class Bicycles( nBicyclesType:String):Id(){
   private  var _BicyclesType:String = nBicyclesType //объявление приватности
   var BicyclesType:String//  указываем тип
      get() =_BicyclesType //получение
      set(value) {
         _BicyclesType= value//отдача типа велосипеда
      }
}