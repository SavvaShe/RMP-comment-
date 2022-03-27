package com.example.prakt9.Model

interface InterValid {
    fun checkFIO(str:String?):Boolean= str!=null && Regex("[А-Я][а-я]+").matches(str)
    fun checkExperience(str:String?):Boolean = str!=null && Regex("([0-9]+)|([0-9]+\\.[0-9]+)").matches(str)
    fun checkType(str:String?):Boolean = str!=null && Regex("([A-Za-z]+)|([А-Яа-яёЁ]+)").matches(str)
}