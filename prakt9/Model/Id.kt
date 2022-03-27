package com.example.prakt9.Model
import java.util.*

open class Id {
    private var _Id:UUID=UUID.randomUUID()
    var Id: UUID
        get() =_Id
        set(value) {
            _Id= value
        }
}