package it.matteosilvestro.quizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Check if all conditions for starting the quiz are met.
     * @param view just because this function will be called by the button "Continue to the quiz"
     */
    public void startQuiz(View view) {
        // get the values from all checkboxes
        boolean accepted = ((CheckBox) findViewById(R.id.checkbox_accept)).isChecked();
        boolean not_accepted = ((CheckBox) findViewById(R.id.checkbox_not_accept)).isChecked();
        boolean not_robot = ((CheckBox) findViewById(R.id.checkbox_captcha)).isChecked();

        // check all possible combinations of checked checkboxes
        if (accepted && not_accepted) {
            // paradox! You have two opposite choices at once
            Toast.makeText(this, R.string.paradox_lover, Toast.LENGTH_SHORT).show();
        } else if (!accepted && !not_accepted) {
            // no choice at all
            Toast.makeText(this, R.string.select_choice, Toast.LENGTH_SHORT).show();
        } else if (accepted && !not_accepted) {
            // continue to the quiz if the user is not a robot
            if (not_robot) {
                // start quiz
                Intent myIntent = new Intent(this, QuizActivity.class);
                this.startActivity(myIntent);
            } else {
                // the user is a robot
                Toast.makeText(this, R.string.robot_warning, Toast.LENGTH_SHORT).show();
            }
        } else if (!accepted && not_accepted) {
            // return to home screen
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }

    }

}
