package com.example.prakt9.Model

open class Participant(nF: String,nI:String,nO:String,IdBicycleType_:String,nExperience: Int):Id(){
    private var _F:String = ""
    var F:String
        get() =_F
        set(value) {
            _F= value
        }
    private var _I:String = nI
    var I:String
        get() =_I
        set(value) {
            _I= value
        }
    private var _O:String = nO
    var O:String
        get() =_O
        set(value) {
            _O= value
        }
    private var _IdBicycleType:String =IdBicycleType_
    var IdBicycleType:String
        get() =_IdBicycleType
        set(value) {
            _IdBicycleType= value
        }
    private  var _Experience:Int = nExperience
    var Experience:Int
        get() =_Experience
        set(value) {
            _Experience= value
        }

}