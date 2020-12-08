package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.pandemic.MainActivity.MSG;

public class Condition extends AppCompatActivity {

    TextView condition;
    Button option_right;
    Button option_wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);

        condition = (TextView) findViewById(R.id.condition);
        option_right = (Button) findViewById(R.id.option1);
        option_wrong = (Button) findViewById(R.id.option2);

        Intent intent = getIntent();
        String[] data = intent.getStringExtra(MSG).split(":");

        if (data.length != 3) {
            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);
        } else {
            condition.setText(data[0]);
            option_right.setText(data[1]);
            option_wrong.setText(data[2]);
        }

        option_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                MainActivity.data = Calculations.nextPart(MainActivity.data, true);
                intent.putExtra(MSG, MainActivity.makeString(MainActivity.data));
                startActivity(intent);
            }
        });

        option_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                MainActivity.data = Calculations.nextPart(MainActivity.data, false);
                intent.putExtra(MSG, MainActivity.makeString(MainActivity.data));
                startActivity(intent);
            }
        });
    }
}