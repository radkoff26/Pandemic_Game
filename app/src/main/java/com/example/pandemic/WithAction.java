package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import static com.example.pandemic.MainActivity.MSG;

public class WithAction extends AppCompatActivity {

    Button treat;
    Button tea;
    Button sleep;
    Button eat;
    Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_action);

        treat = (Button) findViewById(R.id.treat);
        tea = (Button) findViewById(R.id.tea);
        sleep = (Button) findViewById(R.id.sleep);
        eat = (Button) findViewById(R.id.eat);
        restart = (Button) findViewById(R.id.restart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(MSG, "1 1 0 10 80 false 0");
                MainActivity.data = new String[]{"1", "1", "0", "10", "80", "false", "0"};
                startActivity(intent);
            }
        });

        Listener listener = new Listener();
        treat.setOnClickListener(listener);
        tea.setOnClickListener(listener);
        sleep.setOnClickListener(listener);
        eat.setOnClickListener(listener);
    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            MainActivity.data = Calculations.skipPart(MainActivity.data);
            intent.putExtra(MSG, MainActivity.makeString(MainActivity.data));
            startActivity(intent);
        }
    }
}