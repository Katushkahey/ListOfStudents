package com.example.listofstudents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;


import androidx.annotation.Nullable;

public class EditStudentActivity extends Activity {

    ImageView posterView;
    EditText nameView;
    EditText surnameView;
    RadioButton maleView;
    RadioButton femaleView;
    Button deleteView;
    Button saveView;
    Button cancelView;
    String name;
    String surname;
    String sex;
    int poster;
    int index;
    int posterValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        posterView = findViewById(R.id.activity_edit_student__image);
        nameView = findViewById(R.id.activity_edit_student__name);
        surnameView = findViewById(R.id.activity_edit_student__surname);
        maleView = findViewById(R.id.activity_edit_student__male);
        femaleView = findViewById(R.id.activity_edit_student__female);
        deleteView = findViewById(R.id.activity_edit_student__delete);
        saveView = findViewById(R.id.activity_edit_student__save);
        cancelView = findViewById(R.id.activity_edit_student__cancel);

        Student student = getIntent().getParcelableExtra("student");
        name = student.name;
        surname = student.surname;
        sex = student.sex;
        poster = getIntent().getIntExtra("poster",-1);
        index = getIntent().getIntExtra("index", -1);

        onSetChecked(maleView, femaleView, sex);
        nameView.setText(name);
        surnameView.setText(surname);
        posterView.setImageResource(poster);


        maleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posterView.setImageResource(R.drawable.boy);
                poster = R.drawable.boy;

            }
        });

        femaleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posterView.setImageResource(R.drawable.girl);
                poster = R.drawable.girl;


            }
        });


        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("index", index);
                intent.putExtra("button", "delete");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", nameView.getText().toString());
                intent.putExtra("surname", surnameView.getText().toString());
                intent.putExtra("poster", poster);
                intent.putExtra("sex", sex);
                intent.putExtra("index", index);
                intent.putExtra("button", "save");
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    private void onSetChecked(RadioButton a, RadioButton b, String str) {
        if (str.equals("male")) {
            a.setChecked(true);
        } else {
            b.setChecked(true);
        }
    }


}
