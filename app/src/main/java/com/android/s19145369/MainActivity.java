package com.android.s19145369;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =  MainActivity.class.getSimpleName();;
    private EditText money;
    private EditText interest;
    private EditText period;
    public static final int REQUEST=1;
    public static int tienlai;
    public static int tong;
    public static final String EXTRA_MONEY = "com.android.s19145369.EXTRA_MONEY";
    public static final String TOTAL_MONEY = "com.android.s19145369.TOTAL_MONEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        money=findViewById(R.id.money);
        interest = findViewById(R.id.interest);
        period = findViewById(R.id.period);
        money.addTextChangedListener(onTextChangedListener());
    }

    public void goResult(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        float tiengui = Integer.parseInt(money.getText().toString().replaceAll(",", ""));
        float laisuat = Integer.parseInt(interest.getText().toString());
        float kyhan = Integer.parseInt(period.getText().toString());
        tienlai = (int)(tiengui*(laisuat/100)*((kyhan*30)/360));
        tong = (int)tiengui+ tienlai;
        Locale locale = new Locale("en", "EN");
        NumberFormat en =  NumberFormat.getInstance(locale);
        Bundle extras = new Bundle();
        extras.putString(EXTRA_MONEY, String.valueOf(en.format(tienlai)));
        extras.putString(TOTAL_MONEY, String.valueOf(en.format(tong)));
        intent.putExtras(extras);
        startActivity(intent);
        Log.d(LOG_TAG,"Button Clicked!");
    }
    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                money.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    money.setText(formattedString);
                    money.setSelection(money.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                money.addTextChangedListener(this);
            }
        };
    }
}