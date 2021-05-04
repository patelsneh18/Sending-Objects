package com.streamliners.sendingobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.streamliners.sendingobjects.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        getObj();
    }

    /**
     * Receive and show object obtained from intent
     */
    public void getObj(){
        // Validate student object which is coming through the intent
        Student student = getIntent().getExtras().getParcelable(Constants.SEND_OBJ);
        if (student == null){
            Toast.makeText(this, "No data received!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Showing data received from intent
        b.showNameField.getEditText().setText(student.name);
        b.showGenderField.getEditText().setText(student.gender);
        b.showRollNoField.getEditText().setText(student.rollNo);
        b.showMobileNumField.getEditText().setText(student.phoneNo);
    }

}