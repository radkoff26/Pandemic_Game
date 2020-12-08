package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.pandemic.MainActivity.MSG;

public class WithoutAction extends AppCompatActivity {

    Button walk;
    Button shop;
    Button work;
    Button stay;
    Button eat;
    Button sport;
    Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_without_action);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        Intent intent = getIntent();

        walk = (Button) findViewById(R.id.treat);
        shop = (Button) findViewById(R.id.tea);
        work = (Button) findViewById(R.id.work);
        stay = (Button) findViewById(R.id.sleep);
        eat = (Button) findViewById(R.id.eat);
        sport = (Button) findViewById(R.id.sport);
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

        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Condition.class);
                intent.putExtra(MSG, "What do you need to wear?:Mask, sure:The most fashionable clothes!:");
                startActivity(intent);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Condition.class);
                intent.putExtra(MSG, "What do you need to do when you are in queue?:Hold distance!:Don't care about distance");
                startActivity(intent);
            }
        });
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Condition.class);
                intent.putExtra(MSG, "How do you need to work?:Remotely:Locally");
                startActivity(intent);
            }
        });
        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Condition.class);
                intent.putExtra(MSG, "What do you need to do before having a meal?:Wash hands with soap:Take a spoon/fork!");
                startActivity(intent);
            }
        });
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition();
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition();
            }
        });
    }

    private void transition() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        MainActivity.data = Calculations.nextPart(MainActivity.data, true);
        intent.putExtra(MSG, MainActivity.makeString(MainActivity.data));
        startActivity(intent);
    }
}