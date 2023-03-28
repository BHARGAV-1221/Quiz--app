package app.bhargav.quiz

object Constants {

    const val USER_NAME :String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val  CORRECT_ANSWER :String = "correct_answer"

    fun getQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()

            // QUE 1
        val que1=Question(
            1,"what Country does this flag belongs to?",
        R.drawable.malaysia,
            "Argentina",
            "America",
            "Malaysia",
            "China",
            3
        )

        // Que 2
        val que2=Question(
            1,"what Country does this flag belongs to?",
            R.drawable.canada,
            "Cannada",
            "America",
            "India",
            "China",
            1
        )
        questionList.add(que2)

        // Que 3
        val que3=Question(
            1,"what Country does this flag belongs to?",
            R.drawable.southkorea,
            "Argentina",
            "SouthKorea",
            "Malaysia",
            "China",
            2
        )
        questionList.add(que3)

        // Que 4
        val que4=Question(
            1,"what Country does this flag belongs to?",
            R.drawable.switzerland,
            "Argentina",
            "America",
            "Malaysia",
            "Switzerland",
            4
        )
        questionList.add(que4)

        //Que 5
        val que5=Question(
            1,"what Country does this flag belongs to?",
            R.drawable.china,
            "Argentina",
            "America",
            "Malaysia",
            "China",
            4
        )
        questionList.add(que5)

        // QUE 6
          val que6=Question(
            1,"what Country does this flag belongs to?",
            R.drawable.america,
            "Argentina",
            "America",
            "Malaysia",
            "China",
            2
        )
        questionList.add(que6)

        return  questionList
    }
}