package com.streamliners.sendingobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.streamliners.sendingobjects.databinding.ActivityMainBinding;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setupHideErrorForEditText();
    }

    /**
     * TextWatcher which hides error when text changes
     */
    private void setupHideErrorForEditText(){
        b.rollNoField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.rollNoField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        b.phoneNoField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                b.phoneNoField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * Send Object as explicit intent to another activity
     * @param view
     */
    public void objSender(View view){
        //Get name, gender, rollno and phone no of student
        String name = b.nameField.getEditText().getText().toString().trim();
        int checkBox = b.genderRadio.getCheckedRadioButtonId();
        String gender;
        if (checkBox == R.id.female) gender = "Female";
        else gender = "Male";
        String rollNo = b.rollNoField.getEditText().getText().toString().trim();
        if (!rollNo.matches("[0-9]{2}[a-zA-Z]{4}[0-9]{3}")){
            b.rollNoField.setError("Invalid Roll No!");
            return;
        }
        String phoneNo = b.phoneNoField.getEditText().getText().toString().trim();
        if (!phoneNo.matches("^\\d{10}$")){
            b.phoneNoField.setError("Invalid Number!");
            return;
        }

        //Create student object
        Student student = new Student(name,gender,rollNo,phoneNo);

        //Send student object as explicit intent
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra(Constants.SEND_OBJ, student);
        startActivity(intent);
    }
}