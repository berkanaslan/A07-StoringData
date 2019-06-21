package com.berkanaslan.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Public variables
    EditText ageText;
    TextView resultText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.berkanaslan.storingdata", Context.MODE_PRIVATE);
        resultText = (TextView)findViewById(R.id.textView);
        ageText = (EditText)findViewById(R.id.editText2);

        int storedUserAge = sharedPreferences.getInt("storedAge", 0);

        if (storedUserAge == 0) {
            resultText.setText("Your age:");
        } else {
            resultText.setText("Saved user age: " + storedUserAge);
        }
    }

    public void save(View view) {
       /*
       Method 1:
       if(ageText.getText().toString().matches("")) {
            resultText.setText("Age not entered.");
        } else {
            int age = Integer.parseInt(ageText.getText().toString());
            resultText.setText("Age:" + age);
        }*/

        /* Method 2 (Use of "!")*/
        if(!ageText.getText().toString().matches(""))
        {
            int userAge = Integer.parseInt(ageText.getText().toString());
            resultText.setText("Your age: " + userAge);

            sharedPreferences.edit().putInt("storedAge",userAge).apply();

        }
    }

    public void delete(View view) {
        int storedData = sharedPreferences.getInt("storedAge", 0);

        if(storedData != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
            resultText.setText("Age deleted.");
        }

    }
}
