package com.androidbook.services.remote;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class Person implements Parcelable {
    private int age;
    private String name;
    public static final Creator<Person> CREATOR =
            new Creator<Person>() {
                public Person createFromParcel(Parcel in) {
                    return new Person(in);
                }

                public Person[] newArray(int size) {
                    return new Person[size];
                }
            };

    public Person() {
    }

    private Person(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(age);
        out.writeString(name);
    }

    public void readFromParcel(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
