package com.example.listofstudents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    private List<Student> students;
    private final Listener onStudentClickListener;

    public StudentAdapter(List<Student> students, Listener onStudentClickListener) {
        this.students = students;
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStudentClickListener.onStudentClick((Student) v.getTag());
            }
        });
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int i) {
        if (students.size() == 0) {
            return;
        } else {
            Student student = students.get(i);
            holder.bind(student);
            holder.itemView.setTag(student);
        }
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class StudentHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView surname;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.student_item__image);
            name = itemView.findViewById(R.id.student_item__name);
            surname = itemView.findViewById(R.id.student_item__surname);
        }

        public void bind(Student student) {
            name.setText(student.name);
            surname.setText(student.surname);
            imageView.setImageResource(student.poster);
        }
    }

    interface Listener {
        void onStudentClick(Student student);
    }
}

