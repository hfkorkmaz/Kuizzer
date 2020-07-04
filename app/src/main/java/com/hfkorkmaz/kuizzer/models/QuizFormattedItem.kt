package com.hfkorkmaz.kuizzer.models

class QuizFormattedItem {
    var category: String? = null
    var type: String? = null
    var difficulty: String? = null
    var question: String? = null
    var options: ArrayList<Option> = ArrayList()
    var correct_answer:String? = null
    var incorrect_answers:ArrayList<String> = ArrayList()
}

class Option{
    var isCorrect:Boolean? = null
    var text: String? =null
}