package com.simplicity;

public class GameTimer{
    private int day;
    private int second;

    public static int gameTime = 0;

    public GameTimer() {
        this.day = 1;
        this.second = 0;
    }

    public void setDay() {
        this.day = gameTime/720 + 1;
    }

    public void setSecond(){
        this.second = gameTime%720;
    }

    public int getDay() {
        return day;
    }

    public int getSecond(){
        return second;
    }

    public void startTimer(int duration) {
        try {
            for (int i = 0; i < duration; i++){
                Thread.sleep(100);
                gameTime++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setDay();
        setSecond();
    }
}
