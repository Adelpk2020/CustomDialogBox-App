package com.example.customdialogbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private static final String TAG = "MainActivity";

    private Button btn1, btn2;
    private TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        dateText = findViewById(R.id.date_text);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog("Dialog Box 1", "This is Dialog Box 1", "cancelMethod1", "okMethod1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog("Dialog Box 2", "This is Dialog Box 2", "cancelMethod2", "okMethod2");
            }
        });

        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                (DatePickerDialog.OnDateSetListener) this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }



    public void customDialog(String title, String message, final String cancelMethod, final String okMethod){
        AlertDialog.Builder builderSingle =new AlertDialog.Builder(this);
        builderSingle.setIcon(R.mipmap.ic_notification);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Cancel Called.");
                        if(cancelMethod.equals("cancelMethod1")){
                            cancelMethod1();
                        }
                        else if(cancelMethod.equals("cancelMethod2")){
                            cancelMethod2();
                        }

                    }
                });

        builderSingle.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: OK Called.");
                        if(okMethod.equals("okMethod1")){
                            okMethod1();
                        }
                        else if(okMethod.equals("okMethod2")){
                            okMethod2();
                        }
                    }
                });


        builderSingle.show();
    }

    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private void cancelMethod1(){
        Log.d(TAG, "cancelMethod1: Called.");
        toastMessage("Cancel Method1.");
    }
    private void cancelMethod2(){
        Log.d(TAG, "cancelMethod2: Called.");
        toastMessage("Cancel Method2.");
    }
    private void okMethod1(){
        Log.d(TAG, "okMethod1: Called.");
        toastMessage("OK Method1.");
    }
    private void okMethod2(){
        Log.d(TAG, "okMethod2: Called.");
        toastMessage("OK Method2.");
    }
}

