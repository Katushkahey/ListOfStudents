package com.example.listofstudents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Student> students = new ArrayList<>();
    Student newStudent;
    Intent intent;
    StudentAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.list);
        adapter = new StudentAdapter(students, new StudentAdapter.Listener() {
            @Override
            public void onStudentClick(Student student) {
                intent = new Intent("com.example.listofstudents.EditStudentActivity");
                intent.putExtra("student", student);
                intent.putExtra("poster", student.poster);
                intent.putExtra("index", students.indexOf(student));
                startActivityForResult(intent, 2);
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Button buttonAddStudent = findViewById(R.id.activity_main__addstudent);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent("com.example.listofstudents.CreateStudentActivity");
                startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        } else {
            if (requestCode == 1) {
                String name = data.getStringExtra("name");
                String surname = data.getStringExtra("surname");
                String sex = data.getStringExtra("sex");
                int poster = data.getIntExtra("poster", 0);
                newStudent = new Student(name, surname, poster, sex);
                students.add(newStudent);
                adapter.notifyDataSetChanged();
            }
            if (requestCode == 2) {
                String button = data.getStringExtra("button");
                if (button.equals("delete")) {
                    int i = data.getIntExtra("index", -1);
                    students.remove(i);
                    adapter.notifyDataSetChanged();
                } else if (button.equals("save")) {
                    String newName = data.getStringExtra("name");
                    String newSurname = data.getStringExtra("surname");
                    String newSex = data.getStringExtra("sex");
                    int newPoster = data.getIntExtra("poster", 0);
                    int i = data.getIntExtra("index", -1);
                    students.get(i).name = newName;
                    students.get(i).surname = newSurname;
                    students.get(i).sex = newSex;
                    students.get(i).poster = newPoster;
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }


//    private List<Student> onGenerateListOfStudents(){
//        students = new ArrayList<>();
//        students.add(new Student());
//        return students;
//    }
}
