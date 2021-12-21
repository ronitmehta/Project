package com.example.hellofx;

public class Game {

    public void game(){

    }

    public boolean isValidMove(Player p, int num){
        if(p.getPosition()+num<=100){
            return true;
        }
        return false;
    }

    public boolean isComplete(Player p){
        if(p.getIth()==9 && p.getJth()==0){
            return true;
        }
        else{
            return false;
        }
    }
}
