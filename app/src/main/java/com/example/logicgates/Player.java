package com.example.logicgates;

public class Player {
    private String Name;
    private String Score;

    public Player(String name, String score) {
        Name = name;
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    @Override
    public String toString() {
        return Score + "               " + Name ;
    }
}
