package com.android.s19145369;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import static com.android.s19145369.MainActivity.tong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private static final String LOG_TAG =  SecondActivity.class.getSimpleName();;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent =getIntent();
        Bundle extras = intent.getExtras();
        String interest_received = extras.getString(MainActivity.EXTRA_MONEY);
        String total =extras.getString(MainActivity.TOTAL_MONEY);
        Log.d(LOG_TAG,"tong:"+total);
        Log.d(LOG_TAG,"tienlai:"+interest_received);
        TextView interst = findViewById(R.id.interest_received);
        TextView totalTV = findViewById(R.id.total);
        interst.setText(interest_received+" đ");
        totalTV.setText(total+ " đ");
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
}