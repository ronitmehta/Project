package com.example.hellofx;

public class Game {

    public void game(){

    }

    public boolean isValidMove(Player p, int num, int pos){
        if(pos+num<=100){
            return true;
        }
        return false;
    }

    public boolean isComplete(int num){
        if(num>=100){
            return true;
        }
        else{
            return false;
        }
    }
}
