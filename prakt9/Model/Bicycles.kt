package com.example.prakt9.Model

import android.graphics.Bitmap

class Bicycles (nBicyclesType:String,nPhoto:Bitmap?):Id(){
    private  var _BicyclesType:String = nBicyclesType
    var BicyclesType:String
        get() =_BicyclesType
        set(value) {
            _BicyclesType= value
        }
    private var _Photo :Bitmap? = nPhoto
    var Photo :Bitmap?
    get() = _Photo
    set(value) {
            _Photo = value
    }
}