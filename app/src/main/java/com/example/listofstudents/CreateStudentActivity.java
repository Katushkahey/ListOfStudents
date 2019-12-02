package com.example.listofstudents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class CreateStudentActivity extends Activity {

    String sex;
    int posterValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        Button add = findViewById(R.id.activity_create_student__add);
        Button cancel = findViewById(R.id.activity_create_student__cancel);
        final EditText name = findViewById(R.id.activity_create_student__name);
        final EditText surname = findViewById(R.id.activity_create_student__surname);
        final ImageView poster = findViewById(R.id.activity_create_student__image);
        final RadioButton male = findViewById(R.id.activity_create_student__male);
        final RadioButton female = findViewById(R.id.activity_create_student__female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poster.setImageResource(R.drawable.boy);
                posterValue = R.drawable.boy;
                sex = "male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poster.setImageResource(R.drawable.girl);
                posterValue = R.drawable.girl;
                sex = "female";
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSexSelected(male, female)) {
                    Toast.makeText(CreateStudentActivity.this, "Выбеите пол", Toast.LENGTH_LONG).show();
                } else if (name.getText().length() == 0) {
                    Toast.makeText(CreateStudentActivity.this, "Введите имя", Toast.LENGTH_LONG).show();
                } else if (surname.getText().length() == 0) {
                    Toast.makeText(CreateStudentActivity.this, "Введите фамилию", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("surname", surname.getText().toString());
                    intent.putExtra("poster", posterValue);
                    intent.putExtra("sex", sex);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isSexSelected(RadioButton a, RadioButton b) {
        return (a.isChecked() || b.isChecked());
    }

//    private void selectSex(RadioButton a, RadioButton b){
//        if(a.isChecked()){
//            sex = "male";
//        }else{
//            sex = "female";
//        }
//    }

}
