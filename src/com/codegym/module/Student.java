package com.codegym.module;

public class Student {
    private long MSV;
    private String name;
    private int age;
    private String gender;
    private String address;
    private double avgScore;

    public Student() {
    }

    public Student(long MSV, String name, int age, String gender, String address, double avgScore) {
        this.MSV = MSV;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.avgScore = avgScore;
    }

    public static Student parse(String record) {
        String[] fields = record.split(",");
        long MSV = Long.parseLong(fields[0]);
        String name = fields[1];
        int age = Integer.parseInt(fields[2]);
        String gender = fields[3];
        String address = fields[4];
        double avgScore = Double.parseDouble(fields[5]);
        return new Student(MSV, name, age, gender, address,avgScore);
    }

    public long getMSV() {
        return MSV;
    }

    public void setMSV(long MSV) {
        this.MSV = MSV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return  MSV + ","
                + name + ","
                + age + ","
                + gender + ","
                + address + ","
                + avgScore + ",";
    }
}
