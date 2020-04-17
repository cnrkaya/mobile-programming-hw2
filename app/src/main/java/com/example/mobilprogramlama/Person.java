package com.example.mobilprogramlama;

import java.util.ArrayList;

public class Person {
    private String userNname;
    private String password;
    private int photoId;


    public Person(String userName, String password, int photoId) {
        this.userNname = userName;
        this.password = password;
        this.photoId = photoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getUserName() {
        return userNname;
    }

    public void setUserName(String userNname) {
        this.userNname = userNname;

    }
    public static  ArrayList<Person> getPersonsList(){
        ArrayList persons = new ArrayList();
        persons.add( new Person("caner","123",R.drawable.can));
        persons.add( new Person("tom35","123456",R.drawable.bonnie));
        persons.add( new Person("Angela","lailealpiderunclelse",R.drawable.angela));
        persons.add( new Person("Edward","Aquarius",R.drawable.edward));
        persons.add( new Person("Ernest","frizbee12",R.drawable.ernest));
        persons.add( new Person("henry","zoorperry",R.drawable.henry));
        persons.add( new Person("joeee","ganvigh",R.drawable.joe));
        persons.add( new Person("joseph","ginsispy",R.drawable.joseph));
        persons.add( new Person("kimberly","beauseughs",R.drawable.kimberly));
        persons.add( new Person("larry","cueneabbi",R.drawable.larry));
        persons.add( new Person("mary","saue4",R.drawable.mary));
        persons.add( new Person("sheila","vubespaickulpimarfly",R.drawable.sheila));
        return persons;
    }






}
