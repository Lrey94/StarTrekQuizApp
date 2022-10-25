package com.example.android.startrekquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;

    int ChosenOption = 0;

    int NumberOfCorrectAnswers = 0;

    int QuestionNumber = 1;

    String Question_1 = "What is the name of the Captain of the USS Voyager ?";
    String Question_2 = "Which Actor played Spock in the Film - Star Trek (2009)?";
    String Question_3 = "What is the name of the species that would like to 'Assimilate' you?";
    String Question_4 = "What is the name of the Chief Security Officer on the USS Enterprise?";
    String Question_5 = "What is the name of the Klingon Homeworld?";
    String Question_6 = "If someone was to be 'beamed up' - what would be used for this?";
    String Question_7 = "Which of the below are NOT documentaries on Star Trek?";
    String Question_8 = "How many different colored uniforms are there in Star Trek: Enterprise?";

    boolean Q1_CorrectAnswer = false;
    boolean Q2_CorrectAnswer = false;
    boolean Q3_CorrectAnswer = false;
    boolean Q4_CorrectAnswer = false;
    boolean Q5_CorrectAnswer = false;
    boolean Q6_CorrectAnswer = false;
    boolean Q7_CorrectAnswer = false;
    boolean Q8_CorrectAnswer = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView CheckBoxQuestion1 = (TextView) findViewById(R.id.checkBoxAnswer1);
        TextView CheckBoxQuestion2 = (TextView) findViewById(R.id.checkBoxAnswer2);

        CheckBoxQuestion1.setVisibility(View.GONE);
        CheckBoxQuestion2.setVisibility(View.GONE);

    }

    public void StartQuiz (View view) {

//        This method starts when 'start quiz' is pressed. It then presents question 1 and checks
//        whether the correct option is chosen. This method is only valid for question 1.

        QuestionNumber = 1;

        TextView QuestionNumberIndicator = (TextView) findViewById(R.id.QuestionNumber);
        TextView CheckBoxQuestion1 = (TextView) findViewById(R.id.checkBoxAnswer1);
        TextView CheckBoxQuestion2 = (TextView) findViewById(R.id.checkBoxAnswer2);
        TextView EditTextQuestion = (TextView) findViewById(R.id.QuestionTextEditText);
        EditText EditTextEntry = (EditText) findViewById(R.id.QuestionEditText);

        EditTextQuestion.setVisibility(View.GONE);
        EditTextEntry.setVisibility(View.GONE);

        if(QuestionNumber < 9) {

            CheckBoxQuestion1.setVisibility(View.GONE);
            CheckBoxQuestion2.setVisibility(View.GONE);

            QuestionNumberIndicator.setText("Question " + QuestionNumber);

            TextView DisplayQuestion = (TextView) findViewById(R.id.QuestionText);
            DisplayQuestion.setText(Question_1);

            radioGroup = findViewById(R.id.AnswersOptionsRG);
            RadioButton Option1 = (RadioButton) findViewById(R.id.radioButton1);
            RadioButton Option2 = (RadioButton) findViewById(R.id.radioButton2);

            radioGroup.clearCheck();

            Option1.setText("Katherine Jayneway");
            Option2.setText("James Kirk");
        }
    }


    public void NextQuestion (View view) {

        TextView DisplayQuestion = (TextView) findViewById(R.id.QuestionText);
        TextView DisplayCheckBoxQuestion = (TextView) findViewById(R.id.QuestionTextCheckBox);
        TextView CheckBoxQuestion1 = (TextView) findViewById(R.id.checkBoxAnswer1);
        TextView CheckBoxQuestion2 = (TextView) findViewById(R.id.checkBoxAnswer2);
        TextView QuestionTextEditText = (TextView) findViewById(R.id.QuestionTextEditText);
        EditText EditTextEntry = (EditText) findViewById(R.id.QuestionEditText);

        CheckBoxQuestion1.setVisibility(View.GONE);

        checkAnswers(QuestionNumber);

        QuestionNumber += 1;

        TextView QuestionNumberIndicator = (TextView) findViewById(R.id.QuestionNumber);

        if(QuestionNumber < 9) {
            QuestionNumberIndicator.setText("Question " + QuestionNumber);
        }

        if(QuestionNumber == 7) {
            radioGroup.setVisibility(View.GONE);
            DisplayQuestion.setText("See below for Question 7");
            DisplayCheckBoxQuestion.setText(Question_7);
            CheckBoxQuestion1.setVisibility(View.VISIBLE);
            CheckBoxQuestion2.setVisibility(View.VISIBLE);
        }
        if (QuestionNumber == 8) {
            DisplayQuestion.setText("See below for Question 8");
            DisplayCheckBoxQuestion.setVisibility(View.GONE);
            QuestionTextEditText.setVisibility(View.VISIBLE);
            QuestionTextEditText.setText(Question_8);
            CheckBoxQuestion1.setVisibility(View.GONE);
            CheckBoxQuestion2.setVisibility(View.GONE);
            EditTextEntry.setVisibility(View.VISIBLE);
        }

        if (QuestionNumber < 9) {
            createQuestion();
        }

        Log.i("Info: ", "Question Number: " + QuestionNumber);
    }

    public void PreviousQuestion (View view) {

        TextView DisplayQuestion = (TextView) findViewById(R.id.QuestionText);
        TextView DisplayCheckBoxQuestion = (TextView) findViewById(R.id.QuestionTextCheckBox);
        TextView CheckBoxQuestion1 = (TextView) findViewById(R.id.checkBoxAnswer1);
        TextView CheckBoxQuestion2 = (TextView) findViewById(R.id.checkBoxAnswer2);
        EditText EditTextEntry = (EditText) findViewById(R.id.QuestionEditText);
        TextView QuestionTextEditText = (TextView) findViewById(R.id.QuestionTextEditText);

        QuestionNumber -= 1;

        Log.i("Info: ", "Question Number: " + QuestionNumber);

        TextView QuestionNumberIndicator = (TextView) findViewById(R.id.QuestionNumber);

        if (QuestionNumber < 8) {
            QuestionNumberIndicator.setText("Question " + QuestionNumber);
        }

        if(QuestionNumber < 7) {
            radioGroup.setVisibility(View.VISIBLE);
            CheckBoxQuestion1.setVisibility(View.GONE);
            CheckBoxQuestion2.setVisibility(View.GONE);
            DisplayCheckBoxQuestion.setVisibility(View.GONE);
            EditTextEntry.setVisibility(View.GONE);
        }

        if(QuestionNumber == 7){
            radioGroup.setVisibility(View.GONE);
            DisplayQuestion.setText("See below for Question 7");
            DisplayCheckBoxQuestion.setText(Question_7);
            DisplayCheckBoxQuestion.setVisibility(View.VISIBLE);
            CheckBoxQuestion1.setVisibility(View.VISIBLE);
            CheckBoxQuestion2.setVisibility(View.VISIBLE);
            EditTextEntry.setVisibility(View.GONE);
            QuestionTextEditText.setVisibility(View.GONE);
        }

        createQuestion();
    }

    public void onRadioButtonClicked (View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton1:
                if(checked){
                    ChosenOption = 1;
                }
                break;
            case R.id.radioButton2:
                if(checked){
                    ChosenOption = 2;
                }
                break;
        }
        Log.i("Info: ", "Chosen Option: " + ChosenOption);
    }


    public void SubmitAnswers (View view) {

        checkAnswers(QuestionNumber);

        if (Q1_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }
        if (Q2_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }
        if (Q3_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }
        if (Q4_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }
        if (Q5_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }
        if (Q6_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }
        if (Q7_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }
        if (Q8_CorrectAnswer) {
            NumberOfCorrectAnswers += 1;
        }

        Toast.makeText(this, "You scored " + NumberOfCorrectAnswers + " out of 8", Toast.LENGTH_LONG).show();
    }


    public void checkAnswers (int QuestionNumber) {

        CheckBox TrekonomicsBox = findViewById(R.id.checkBoxAnswer1);
        CheckBox SpockCheckBox = findViewById(R.id.checkBoxAnswer2);
        boolean TrekonomicsBoxChecked = TrekonomicsBox.isChecked();
        boolean SpockCheckBoxChecked = SpockCheckBox.isChecked();

        EditText EditTextEntry = (EditText) findViewById(R.id.QuestionEditText);
        String EditTextQuestionString = EditTextEntry.getText().toString();

//        Question 1
        if(QuestionNumber == 1){
            if (ChosenOption == 1) {
                Q1_CorrectAnswer = true;
            } else if (ChosenOption == 2){
                Q1_CorrectAnswer = false;
            }
            Log.i("Info:", "Q1 Correct answer: " + Q1_CorrectAnswer );
        }
//        Question 2
        else if(QuestionNumber == 2){
            if (ChosenOption == 1) {
                Q2_CorrectAnswer = false;
            } else if (ChosenOption == 2){
                Q2_CorrectAnswer = true;
            }
            Log.i("Info:", "Q2 Correct answer: " + Q2_CorrectAnswer );
        }
//          Question 3
        else if(QuestionNumber == 3) {
            if (ChosenOption == 1) {
                Q3_CorrectAnswer = true;
            } else if (ChosenOption == 2){
                Q3_CorrectAnswer = false;
            }
            Log.i("Info:", "Q3 Correct answer: " + Q3_CorrectAnswer );
        }
//          Question 4
        else if(QuestionNumber == 4) {
            if (ChosenOption == 1) {
                Q4_CorrectAnswer = false;
            } else if (ChosenOption == 2){
                Q4_CorrectAnswer = true;
            }
            Log.i("Info:", "Q4 Correct answer: " + Q4_CorrectAnswer );
        }
//          Question 5
        else if(QuestionNumber == 5) {
            if (ChosenOption == 1) {
                Q5_CorrectAnswer = false;
            } else if (ChosenOption == 2){
                Q5_CorrectAnswer = true;
            }
            Log.i("Info:", "Q5 Correct answer: " + Q5_CorrectAnswer );
        }
//          Question 6
        else if(QuestionNumber == 6) {
            if (ChosenOption == 1) {
                Q6_CorrectAnswer = true;
            } else if (ChosenOption == 2){
                Q6_CorrectAnswer = false;
            }
            Log.i("Info:", "Q6 Correct answer: " + Q6_CorrectAnswer );
        }
//        Question 7
        else if(QuestionNumber == 7) {
            if (TrekonomicsBoxChecked && SpockCheckBoxChecked) {
                Q7_CorrectAnswer = true;
            } else {
                Q7_CorrectAnswer = false;
            }
            Log.i("Info:", "Q7 Correct answer: " + Q7_CorrectAnswer );
        }
//        Question 8
        else if(QuestionNumber == 8) {
            if (EditTextQuestionString.equals("three") || EditTextQuestionString.equals("3")) {
                Q8_CorrectAnswer = true;
            } else {
                Q8_CorrectAnswer = false;
            }
            Log.i("Info:", EditTextQuestionString);
            Log.i("Info:", "Q8 Correct answer: " + Q8_CorrectAnswer );
        }
    }

    public void createQuestion () {

        TextView DisplayQuestion = (TextView) findViewById(R.id.QuestionText);

        radioGroup = findViewById(R.id.AnswersOptionsRG);
        RadioButton Option1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton Option2 = (RadioButton) findViewById(R.id.radioButton2);

        if (QuestionNumber == 1) {
            DisplayQuestion.setText(Question_1);
            Option1.setText("Katherine Jayneway");
            Option2.setText("James Kirk");

        }else if (QuestionNumber == 2) {
            DisplayQuestion.setText(Question_2);
            Option1.setText("Simon Pegg");
            Option2.setText("Zachary Quinto");

        } else if (QuestionNumber == 3) {
            DisplayQuestion.setText(Question_3);
            Option1.setText("The Borg");
            Option2.setText("Cardasians");

        } else if (QuestionNumber == 4) {
            DisplayQuestion.setText(Question_4);
            Option1.setText("Worf");
            Option2.setText("Natasha Yar");

        } else if (QuestionNumber == 5) {
            DisplayQuestion.setText(Question_5);
            Option1.setText("Vulkan");
            Option2.setText("Kronos");

        } else if (QuestionNumber == 6) {
            DisplayQuestion.setText(Question_6);
            Option1.setText("Transporter");
            Option2.setText("Holodeck");
        }
    }


//    Need to add previous question button - need to retain user choices for every question - difficult
//    Stop quiz from moving to question if no radio buttons checked - toast message "Must select an option"
//    Need to create intent to web page with live long motto and star trek sound playing at same time
//    Configure app colors for dark mode


}