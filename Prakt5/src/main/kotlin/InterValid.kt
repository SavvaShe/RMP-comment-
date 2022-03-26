interface InterValid {
    fun checkFIO(str:String?):Boolean= str!=null && Regex("[А-Я][а-я]+").matches(str)//проверка с помощью регулярного выражения фио
    fun checkExperience(str:String?):Boolean = str!=null && Regex("([0-9]+)|([0-9]+\\.[0-9]+)").matches(str)//проврка с помощью регулярного выражения опыта
    fun checkType(str:String?):Boolean = str!=null && Regex("([A-Za-z]+)|([А-Яа-яёЁ]+)").matches(str)//проверка с помощью регулярного выражения типа
}