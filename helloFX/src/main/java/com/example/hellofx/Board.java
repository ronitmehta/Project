package com.example.hellofx;

import javafx.scene.image.Image;

import java.util.HashMap;

public class Board {
    private Image img;
    private static HashMap<Integer, Integer> snakes = new HashMap<>();
    private static HashMap<Integer, Integer> ladders = new HashMap<>();

    public Board(Image img){
        setImage(img);
        setSnakes();
        setLadders();
    }

    public Board(){}

    public void setImage(Image img ){
        this.img = img;
    }

    public Image getImage(){
        return this.img;
    }

    public void setSnakes(){
        snakes.put(98, 33);
        snakes.put(95, 41);
        snakes.put(92, 44);
        snakes.put(66, 10);
        snakes.put(59, 19);
        snakes.put(51, 40);
        snakes.put(46, 21);
        snakes.put(32, 23);
        snakes.put(23, 18);
        snakes.put(81, 19);
    }

    public void setLadders(){
        ladders.put(2, 19);
        ladders.put(6, 21);
        ladders.put(8, 25);
        ladders.put(38, 20);
        ladders.put(24, 40);
        ladders.put(16, 5);
        ladders.put(63, 19);
        ladders.put(73, 31);
        ladders.put(70, 21);
        ladders.put(85, 12);
    }

    public HashMap<Integer, Integer> getSnakes(){
        setSnakes();
        return snakes;
    }

    public HashMap<Integer, Integer> getLadders(){
        setLadders();
        return ladders;
    }

}
