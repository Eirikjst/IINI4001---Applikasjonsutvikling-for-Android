package com.example.ejs.exercise2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculationApp extends AppCompatActivity {

    private int start_number_one = 3;
    private int start_number_two = 5;
    private int upper_bound = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_app);

        TextView t1 = findViewById(R.id.textView3);
        t1.setText("First number: "+Integer.toString(start_number_one));
        TextView t2 = findViewById(R.id.textView4);
        t2.setText("Second number: "+Integer.toString(start_number_two));

        EditText boundtext = findViewById(R.id.plain_text_input2);
        boundtext.setText(Integer.toString(upper_bound));

        final Button button = findViewById(R.id.button2);
        final Button button2 = findViewById(R.id.button3);

        //Listener for addition button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText answer = findViewById(R.id.plain_text_input);
                boolean proceed = check_user_input(answer);
                if (proceed) {
                    check_answer(Integer.parseInt(answer.getText().toString()), "+");
                } else {
                    Toast.makeText(getApplicationContext(),"Input is not a number, try again.",Toast.LENGTH_SHORT).show();
                }
                update_numbers();
            }
        });

        //Listener for multiplication button
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText answer = findViewById(R.id.plain_text_input);
                boolean proceed = check_user_input(answer);
                if (proceed) {
                    check_answer(Integer.parseInt(answer.getText().toString()), "*");
                } else {
                    Toast.makeText(getApplicationContext(),"Input is not a number, try again.",Toast.LENGTH_SHORT).show();
                }
                update_numbers();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==1 && resultCode==RESULT_OK){
            String numberone = (String)data.getStringExtra("random_int1");
            String numbertwo = (String)data.getStringExtra("random_int2");

            //start_number_one = Integer.parseInt(numberone);
            //start_number_two = Integer.parseInt(numbertwo);

            TextView t1 = findViewById(R.id.textView3);
            t1.setText("First number: "+numberone);
            TextView t2 = findViewById(R.id.textView4);
            t2.setText("Second number: "+numbertwo);

        }
    }

    private int fetch_int_from_textview(TextView tv){
        return Integer.parseInt(tv.getText().toString().replaceAll("\\D+", ""));
    }

    private boolean check_user_input(EditText et){
        try{
            Integer.parseInt(et.getText().toString());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private void check_answer(int answer, String operator){
        TextView temp1 = findViewById(R.id.textView3);
        TextView temp2 = findViewById(R.id.textView4);

        int number_one = fetch_int_from_textview(temp1);
        int number_two = fetch_int_from_textview(temp2);

        if (operator.equals("+")){
            if (answer == (number_one+number_two)){
                answer_correct_wrong((number_one+number_two), true);
            } else {
                answer_correct_wrong((number_one+number_two), false);
            }
        } else {
            if (answer == (number_one*number_two)){
                answer_correct_wrong((number_one*number_two), true);
            } else {
                answer_correct_wrong((number_one*number_two), false);
            }
        }
    }

    //true = correct, false = wrong
    private void answer_correct_wrong(int answer, boolean operator){
        if (operator) {
            Toast.makeText(getApplicationContext(),getString(R.string.Correct), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.Wrong_Correct_answer_is)+": "+answer,Toast.LENGTH_SHORT).show();
        }
    }

    private void update_numbers(){
        EditText bound_input = findViewById(R.id.plain_text_input2);
        boolean proceed = check_user_input(bound_input);
        if (proceed) {
            Intent intent = new Intent(this, RandomInteger.class);
            String message = bound_input.getText().toString();
            intent.putExtra("upper_bound", message);
            startActivityForResult(intent, 1);
        } else {
            Toast.makeText(getApplicationContext(),"Upper bound is not a number, could not update numbers.",Toast.LENGTH_SHORT).show();
        }
    }
}
