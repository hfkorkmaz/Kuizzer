package com.hfkorkmaz.kuizzer.models

class QuizItem {
    var category: String? = null
    var type: String? = null
    var difficulty: String? = null
    var question: String? = null
    var correct_answer:String? = null
    var incorrect_answers:ArrayList<String> = ArrayList()
}