package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    public static String[] data = new String[7];

    public static final String MSG = "data";

    TextView amount;
    TextView isIll;
    TextView days;
    Button btn;
    String got;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = (TextView) findViewById(R.id.amount);
        isIll = (TextView) findViewById(R.id.isIll);
        days = (TextView) findViewById(R.id.days);
        btn = (Button) findViewById(R.id.actions);

        SharedPreferences sPref = getPreferences(MODE_PRIVATE);

        Intent intent = getIntent();

        if (intent.hasExtra(MSG)) {
            got = intent.getStringExtra(MSG);
        }

        String data_txt = makeString(data);

        if (got == null) {
            data = loadText(sPref);
            if (Arrays.equals(data, new String[]{})) {
                data = new String[]{"1", "1", "0", "10", "80", "false", "0"};
            }
        } else {
            saveText(sPref, got);
            data = got.split(" ");
        }

        amount.setText("Total ailing people: " + data[1]);
        if (Boolean.parseBoolean(data[5])) {
            isIll.setText("You are ill!");
            isIll.setBackgroundColor(getResources().getColor(R.color.ill));
        }
        days.setText(String.format("Day %s\n%s/4", data[0], data[2]));

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1;
                if (data[5].equals("true")) {
                    intent1 = new Intent(getApplicationContext(), WithAction.class);
                } else {
                    intent1 = new Intent(getApplicationContext(), WithoutAction.class);
                }
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    void saveText(SharedPreferences sPref, String text) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(MSG, text);
        ed.apply();
    }

    String[] loadText(SharedPreferences sPref) {
        return sPref.getString(MSG, "").equals("") ? new String[]{} : sPref.getString(MSG, "").split(" ");
    }

    public static String makeString(String[] array) {
        String res = "";
        for (int i = 0; i < array.length - 1; i++) {
            res += array[i] + " ";
        }
        res += array[array.length - 1];
        return res;
    }
}