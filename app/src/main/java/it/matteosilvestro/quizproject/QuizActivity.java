package it.matteosilvestro.quizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;
import static it.matteosilvestro.quizproject.R.string.paradox_1;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    /**
     * Send all the answers given to the result activity.
     * @param view ust because this function will be called by the button "Confirm"
     */
    public void sendQuiz(View view) {
        boolean missing = false;        // whether or not there are missing answers in the quiz
        String missing_paradoxes = "";  // string with the title of the missing answers

        // paradox #1
        int answer_1 = getAnswerRadio(R.id.paradox_1_radio);
        if (answer_1 == -1) {   // this answer is missing
            missing = true;
            missing_paradoxes = getString(R.string.paradox_1);
        }

        // paradox #2
        int answer_2 = getAnswerRadio(R.id.paradox_2_radio);
        if (answer_2 == -1) {
            missing = true;
            String newString = getString(R.string.paradox_2);
            // add a comma at the beginning only if there are already other missing answers
            if (missing_paradoxes.equals("")) missing_paradoxes = newString;
            else missing_paradoxes = missing_paradoxes + ", " + newString;
        }

        // paradox #3
        int answer_3 = getAnswerRadio(R.id.paradox_3_radio);
        if (answer_3 == -1) {
            missing = true;
            String newString = getString(R.string.paradox_3);
            // add a comma at the beginning only if there are already other missing answers
            if (missing_paradoxes.equals("")) missing_paradoxes = newString;
            else missing_paradoxes = missing_paradoxes + ", " + newString;
        }

        // paradox #4
        int[] ids = new int[] {R.id.paradox_4_choice_1, R.id.paradox_4_choice_2, R.id.paradox_4_choice_3, R.id.paradox_4_choice_4};
        int answer_4 = getAnswerCheckboxes(ids);
        if (answer_4 == 0) {
            missing = true;
            String newString = getString(R.string.paradox_4);
            // add a comma at the beginning only if there are already other missing answers
            if (missing_paradoxes.equals("")) missing_paradoxes = newString;
            else missing_paradoxes = missing_paradoxes + ", " + newString;
        }

        // paradox #5
        String answer_5 = ((TextView) findViewById(R.id.paradox_5_text)).getText().toString();
        if (answer_5.equals("")) {
            missing = true;
            String newString = getString(R.string.paradox_5);
            // add a comma at the beginning only if there are already other missing answers
            if (missing_paradoxes.equals("")) missing_paradoxes = newString;
            else missing_paradoxes = missing_paradoxes + ", " + newString;
        }

        if (missing) {
            // there are missing answers
            Toast.makeText(this, getString(R.string.missing_choices, missing_paradoxes), Toast.LENGTH_SHORT).show();
        } else {
            // otherwise the quiz answers are ready to be sent to the result activity
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("answer_1", answer_1);
            i.putExtra("answer_2", answer_2);
            i.putExtra("answer_3", answer_3);
            i.putExtra("answer_4", answer_4);
            i.putExtra("answer_5", answer_5);
            startActivity(i);
        }
    }

    /**
     * Given a radio group, get the index of the radio button checked.
     * @param given_id is the id of the radio group
     * @return the index of the radio button checked, starting from 0
     */
    private int getAnswerRadio(int given_id) {
        RadioGroup group = (RadioGroup) findViewById(given_id);
        int index = -1;
        for (int i = 0; i < group.getChildCount(); i++) {
            if (((RadioButton) group.getChildAt(i)).isChecked()) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Given a set of checkboxes, get which checkboxes are checked.
     * @param ids is an array of checkboxes ids.
     * @return a number which binary form gives which checkboxes are checked.
     * Example: if there are four checkboxes and the 1st and 3rd are checked, the number returned will be
     *          0101 (binary) -> 5 (decimale, returned value)
     */
    private int getAnswerCheckboxes(int[] ids) {
        int binary_choices = 0;
        int i = 0;
        for (int id:ids) {
            CheckBox check = (CheckBox) findViewById(id);
            if (check.isChecked()) binary_choices += Math.pow(2, i);
            i++;
        }
        return binary_choices;
    }

}
