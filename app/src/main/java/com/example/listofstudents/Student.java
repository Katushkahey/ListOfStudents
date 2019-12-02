package com.example.listofstudents;


import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Student implements Parcelable {

    String name;
    String surname;
    String sex;
    int poster;
    ImageView photo;

    public Student(String name, String surname, int poster, String sex) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.poster = poster;
    }

    public Student() {
    }

    public Student(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        name = data[0];
        surname = data[1];
        sex = data[2];
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{name, surname, sex});
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
