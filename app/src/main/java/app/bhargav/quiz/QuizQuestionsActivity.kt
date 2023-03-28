package app.bhargav.quiz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import com.google.android.material.resources.MaterialResources.getDrawable


class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener{
    private var mcurrentPosition : Int = 1
    private var mquestionList :ArrayList<Question>? =null
    private var mselectedOption :Int = 0
 private var mCorrectAnswers:Int = 0

// to retirve to name
    private var muserName :String ? = null
    private var progressBar :ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestions : TextView? = null
    private var tvImage : ImageView?= null
    private var tvoptionOne : TextView? = null
    private var tvoptionTwo : TextView? = null
    private var tvoptionThree : TextView? = null
    private var tvoptionFour : TextView? = null
    private var btnSumbit :Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

//retriving name in oncrete

        muserName = intent.getStringExtra(Constants.USER_NAME)
        // Declaring all variables
progressBar = findViewById(R.id.tv_progressbar)
        tvProgress= findViewById(R.id.tv_progress)
        tvQuestions = findViewById(R.id.tv_quetions)
        tvImage= findViewById(R.id.tv_imageview)
        tvoptionOne= findViewById(R.id.tv_option_one)
        tvoptionTwo = findViewById(R.id.tv_option_two)
        tvoptionThree = findViewById(R.id.tv_option_three)
        tvoptionFour = findViewById(R.id.tv_option_four)
        mquestionList = Constants.getQuestions()
        btnSumbit= findViewById(R.id.btn_submit)

        tvoptionOne?.setOnClickListener(this)
        tvoptionTwo?.setOnClickListener(this)
        tvoptionThree?.setOnClickListener(this)
        tvoptionFour?.setOnClickListener(this)
btnSumbit?.setOnClickListener(this)
        setQuestion()
       // defaultOptinView()
    }

    private fun selectedOptionView(tv: TextView,selectedOptionNum :Int){
        defaultOptinView()

        mselectedOption = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun setQuestion() {
defaultOptinView()
        // Setting Questions
        var question: Question = mquestionList!![mcurrentPosition - 1]

        progressBar?.progress = mcurrentPosition
        tvProgress?.text = "$mcurrentPosition / ${progressBar?.max}"
        tvImage?.setImageResource(question.image)
        tvQuestions?.text = question.question
        tvoptionOne?.text = question.optionOne
        tvoptionTwo?.text = question.optionTwo
        tvoptionThree?.text = question.optionThree
        tvoptionFour?.text = question.optionFour


// Conditions to for last and middle questind to set submit to finish
        if(mcurrentPosition == mquestionList!!.size){
            btnSumbit?.text = "FINISH"
        }else{
            btnSumbit?.text="SUBMIT"
        }
    }


    private fun defaultOptinView(){
        // setting options

        val options = ArrayList<TextView>()

        tvoptionOne?.let {
            options.add(0,it)
        }
        tvoptionTwo?.let {
            options.add(1,it)
        }
        tvoptionThree?.let {
            options.add(2,it)
        }
        tvoptionFour?.let {
            options.add(3,it)
        }


        for(option in options){
            option.setTextColor(Color.parseColor("#FF0000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border_bg
            )

        }


    }

    override fun onClick(view: View?) {
when(view?.id){
    R.id.tv_option_one->{
        tvoptionOne?.let {
            selectedOptionView(it,1)
        }
    }
    R.id.tv_option_two->{
        tvoptionTwo?.let {
            selectedOptionView(it,2)
        }
    }
    R.id.tv_option_three->{
        tvoptionThree?.let {
            selectedOptionView(it,3)
        }
    }
    R.id.tv_option_four->{
        tvoptionFour?.let {
            selectedOptionView(it,4)
        }
    }
    R.id.btn_submit->{
        //TODO implentaion
        // need to check selecxted option is correct or not

        if(mselectedOption == 0){
            mcurrentPosition++

            when{
                mcurrentPosition <= mquestionList!!.size->{
                    setQuestion()
                }
                else->{
                    val intent = Intent(this ,resultActivity::class.java)
                    intent.putExtra(Constants.USER_NAME,muserName)
                    intent.putExtra(Constants.CORRECT_ANSWER,mCorrectAnswers)
                    intent.putExtra(Constants.TOTAL_QUESTIONS,mquestionList?.size)
                    startActivity(intent)
                    finish()
                }
            }
        }else{
            val question = mquestionList?.get(mcurrentPosition-1)

            if(question!!.CoorectAnswer != mselectedOption){
                answerView(mselectedOption,R.drawable.wrong_option_border_bg)
            }else{
                mCorrectAnswers++
            }
            answerView(question.CoorectAnswer,R.drawable.correct_option_border_bg)

            if(mcurrentPosition==mquestionList!!.size){
                btnSumbit?.text = "FINISH"
            }else{
                btnSumbit?.text="GO TO NEXT QUESTION "
            }

            mselectedOption= 0
        }


    }
}
    }

    private fun answerView(answer : Int , drawableView : Int){
        when(answer){
            1->{
                tvoptionOne?.background= ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                tvoptionTwo?.background= ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                tvoptionThree?.background= ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                tvoptionFour?.background= ContextCompat.getDrawable(this,drawableView)
            }
        }
    }
}