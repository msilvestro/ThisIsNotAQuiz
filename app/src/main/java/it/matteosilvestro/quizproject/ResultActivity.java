package it.matteosilvestro.quizproject;

import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.id;
import static android.R.attr.x;
import static it.matteosilvestro.quizproject.R.string.score;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get the variables passed by the previous activity
        Bundle b = getIntent().getExtras();
        int score = getScore(b);
        displayScore(score);

    }

    /**
     * Get the score of the quiz, given all the answers given by the user.
     * @param b bundle with all answers
     * @return the quiz score
     */
    private int getScore(Bundle b) {
        int score = 0;

        // now color the answer given by the user
        // Paradox #1
        int answer_1 = b.getInt("answer_1");
        switch (answer_1) {
            case 0:
                setTextColor(R.id.paradox_1_choice_1);
                break;
            case 1:
                setTextColor(R.id.paradox_1_choice_2);
                break;
            case 2: // right answer
                setTextColor(R.id.paradox_1_choice_3);
                score += 10;
                break;
        }

        // Paradox #2
        int answer_2 = b.getInt("answer_2");
        switch (answer_2) {
            case 0:
                setTextColor(R.id.paradox_2_choice_1);
                break;
            case 1:
                setTextColor(R.id.paradox_2_choice_2);
                break;
            case 2: // right answer
                setTextColor(R.id.paradox_2_choice_3);
                score += 10;
                break;
        }

        // Paradox #3
        int answer_3 = b.getInt("answer_3");
        switch (answer_3) {
            case 0:
                setTextColor(R.id.paradox_3_choice_1);
                break;
            case 1:
                setTextColor(R.id.paradox_3_choice_2);
                break;
            case 2: // right answer
                setTextColor(R.id.paradox_3_choice_3);
                score += 10;
                break;
        }

        // Paradox #4
        int answer_4 = b.getInt("answer_4");
        // remember that 4th answer is in binary form (even though the number itself is decimal),
        // so we need to extract the checked checkboxes from the binary form
        while (answer_4 > 0) {
            if (answer_4 >= 8) { // right answer
                answer_4 -= 8;
                setTextColor(R.id.paradox_4_choice_4);
                score += 5;
            } else if (answer_4 >= 4) {
                answer_4 -= 4;
                setTextColor(R.id.paradox_4_choice_3);
            } else if (answer_4 >= 2) {  // right answer
                answer_4 -= 2;
                setTextColor(R.id.paradox_4_choice_2);
                score += 5;
            } else if (answer_4 >= 1) {
                answer_4 -= 1;
                setTextColor(R.id.paradox_4_choice_1);
            }
        }

        // Paradox #5
        String answer_5 = b.getString("answer_5");
        ((TextView) findViewById(R.id.paradox_5_guessed)).setText(answer_5);
        if (answer_5.equals(getString(R.string.paradox_5_correct))) score += 10;

        return score;
    }

    /**
     * Change color of the a TextView with accent color.
     * @param id of the TextView which color should be changed.
     */
    private void setTextColor(int id) {
        ((TextView) findViewById(id)).setTextColor(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }

    /**
     * Display the score.
     * @param score of the quiz
     */
    private void displayScore(int score) {
        // update the score
        ((TextView) findViewById(R.id.score_text)).setText(String.valueOf(score));

        // change image
        if (score > 25) {
            ((ImageView) findViewById(R.id.result_image)).setImageResource(R.drawable.glados);
            ((TextView) findViewById(R.id.result_description)).setText(getString(R.string.glados));
        }
    }

}
