package com.example.hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class HelloController {
    @FXML
    public ImageView img;

    @FXML
    public Circle p1;

    @FXML
    public Circle p2;

    @FXML
    public Button b1;

    @FXML
    public Button b2;

    @FXML
    public TextField t1;

    @FXML
    public TextField t2;

    @FXML
    public AnchorPane ap;

    public static int j = 0;
    public int step = 1;
    public static int i = 0;

    public int count = 1;

    public int player1Count = 0;
    public int player2Count = 0;

    @FXML
    private ImageView diceImage;

    @FXML
    private Button rollButton;

    public static int num = 0;
    @FXML
    void roll(ActionEvent event) throws IOException{

        rollButton.setDisable(true);
        Random random = new Random();
        Thread thread = new Thread(){
            public void run(){

                try {
                    for (int i = 0; i < 10; i++) {
                        num=random.nextInt(6)+1;
                        File file = new File("src/main/resources/com/example/hellofx/dice" + (num)+".png");

                        diceImage.setImage(new Image(file.toURI().toString()));
                        Thread.sleep(50);


                    }
//                    move(dice);
                    if(count==1){
                        p1.setTranslateY(p1.getTranslateY()-57);
                        moveInPositiveDirection(player2, num-1, p1);
                        player2Count = player2Count + (num);


                        if(board.getSnakes().containsKey(player2Count)){
                            Thread thread = new Thread(){
                                public void run(){
                                    try{
                                        System.out.println("Snake");
                                        Thread.sleep(1000);
                                        moveInNegativeDirection(player2, board.getSnakes().get(player2Count), p1);
                                        player2Count = player2Count - board.getSnakes().get(player2Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();

                        }

                        if(board.getLadders().containsKey(player2Count)){
                            Thread thread = new Thread(){
                                public void run(){
                                    try{
                                        System.out.println("Ladder");
                                        Thread.sleep(1000);
                                        moveInPositiveDirection(player2, board.getLadders().get(player2Count), p1);
                                        player2Count = player2Count + board.getLadders().get(player2Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }

                    }

                    else if(count==2){
                        p2.setTranslateY(p2.getTranslateY()-27);
                        moveInPositiveDirection(player1, num-1, p2);
                        player1Count = player1Count + (num );

                        if(board.getSnakes().containsKey(player1Count)){
                            Thread thread = new Thread(){
                                public void run(){
                                    try{
                                        Thread.sleep(1000);
                                        System.out.println("Snake");
                                        moveInNegativeDirection(player1, board.getSnakes().get(player1Count), p2);
                                        player1Count = player1Count - board.getSnakes().get(player1Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }

                        if(board.getLadders().containsKey(player1Count)){
                            Thread thread = new Thread(){
                                public void run(){
                                    try{
                                        System.out.println("Ladder");
                                        Thread.sleep(1000);
                                        moveInPositiveDirection(player1, board.getLadders().get(player1Count), p2);
                                        player1Count = player1Count + board.getLadders().get(player1Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }

                    }

                    else{
                        if(count%2==0){
                            if(game.isValidMove(player1, num, player1Count)){
                                System.out.println("Player1 pos: "+player1Count);
                                if(!game.isComplete(player1Count + num)) {

                                    moveInPositiveDirection(player1, num, p2);
                                    player1Count = player1Count + num;

                                    if(board.getSnakes().containsKey(player1Count)){

                                        Thread thread = new Thread(){
                                            public void  run(){
                                                try{
                                                    System.out.println("Snake");
                                                    Thread.sleep(1000);
                                                    moveInNegativeDirection(player1, board.getSnakes().get(player1Count), p2);
                                                    player1Count = player1Count - board.getSnakes().get(player1Count);
                                                }
                                                catch (InterruptedException e){
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        thread.start();
                                    }

                                    if(board.getLadders().containsKey(player1Count)){
                                        Thread thread = new Thread(){
                                            public void  run(){
                                                try {
                                                    Thread.sleep(1000);
                                                    System.out.println("Ladder");
                                                    moveInPositiveDirection(player1, board.getLadders().get(player1Count), p2);
                                                    player1Count = player1Count + board.getLadders().get(player1Count);
                                                }
                                                catch (InterruptedException e){
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        thread.start();
                                    }
                                }
                                else{
                                    Stage st = (Stage) ap.getScene().getWindow();
                                    FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("ending.fxml"));
                                    Scene sc = new Scene(fxmlLoader.load());
                                    st.setScene(sc);
                                    st.show();
                                }

                            }
                        } else{
                            if(game.isValidMove(player2, num, player2Count)){
                                System.out.println("Player pos2: "+player2Count);
                                if(!game.isComplete(player2Count+num)){
                                    moveInPositiveDirection(player2, num, p1);
                                    player2Count = player2Count + num;
                                    if(board.getSnakes().containsKey(player2Count)){
                                        Thread thread = new Thread(){
                                            public void run(){
                                                try {
                                                    Thread.sleep(1000);
                                                    System.out.println("Snake");
                                                    moveInNegativeDirection(player2, board.getSnakes().get(player2Count), p1);
                                                    player2Count = player2Count - board.getSnakes().get(player2Count);
                                                }
                                                catch (InterruptedException e){
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        thread.start();
                                    }
                                    if(board.getLadders().containsKey(player2Count)){
                                        Thread thread = new Thread(){
                                            public void run(){
                                                try{
                                                    System.out.println("Ladder");
                                                    Thread.sleep(1000);
                                                    moveInPositiveDirection(player2, board.getLadders().get(player2Count), p1);
                                                    player2Count = player2Count + board.getLadders().get(player2Count);
                                                }
                                                catch (InterruptedException e){
                                                    e.printStackTrace();
                                                }
                                            }
                                        };
                                        thread.start();
                                    }
                                }
                                else{
                                    Stage st = (Stage) ap.getScene().getWindow();
                                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ending.fxml"));
                                    Parent root = fxmlLoader.load();
                                    Scene sc = new Scene(root);
                                    st.setScene(sc);
                                }
                            }
                        }
                    }
                    count++;
                    rollButton.setDisable(false);

                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

    Player player1 = new Player("123", p1);
    Player player2 = new Player("345", p2);

    Game game = new Game();
    Board board = new Board();


    public void move(int num) throws IOException {
        if(count==1){
            p1.setTranslateY(p1.getTranslateY()-57);
            moveInPositiveDirection(player2, num-1, p1);
            player2Count = player2Count + (num);


            if(board.getSnakes().containsKey(player2Count)){
                Thread thread = new Thread(){
                    public void run(){
                        try{
                            System.out.println("Snake");
                            Thread.sleep(1000);
                            moveInNegativeDirection(player2, board.getSnakes().get(player2Count), p1);
                            player2Count = player2Count - board.getSnakes().get(player2Count);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();

            }

            if(board.getLadders().containsKey(player2Count)){
                Thread thread = new Thread(){
                    public void run(){
                        try{
                            System.out.println("Ladder");
                            Thread.sleep(1000);
                            moveInPositiveDirection(player2, board.getLadders().get(player2Count), p1);
                            player2Count = player2Count + board.getLadders().get(player2Count);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }

        }

        else if(count==2){
            p2.setTranslateY(p2.getTranslateY()-27);
            moveInPositiveDirection(player1, num-1, p2);
            player1Count = player1Count + (num );

            if(board.getSnakes().containsKey(player1Count)){
                Thread thread = new Thread(){
                    public void run(){
                        try{
                            Thread.sleep(1000);
                            System.out.println("Snake");
                            moveInNegativeDirection(player1, board.getSnakes().get(player1Count), p2);
                            player1Count = player1Count - board.getSnakes().get(player1Count);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }

            if(board.getLadders().containsKey(player1Count)){
                Thread thread = new Thread(){
                    public void run(){
                        try{
                            System.out.println("Ladder");
                            Thread.sleep(1000);
                            moveInPositiveDirection(player1, board.getLadders().get(player1Count), p2);
                            player1Count = player1Count + board.getLadders().get(player1Count);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }

        }

        else{
            if(count%2==0){
                if(game.isValidMove(player1, num, player1Count)){
                    System.out.println("Player1 pos: "+player1Count);
                    if(!game.isComplete(player1Count + num)) {

                        moveInPositiveDirection(player1, num, p2);
                        player1Count = player1Count + num;

                        if(board.getSnakes().containsKey(player1Count)){

                            Thread thread = new Thread(){
                                public void  run(){
                                    try{
                                        System.out.println("Snake");
                                        Thread.sleep(1000);
                                        moveInNegativeDirection(player1, board.getSnakes().get(player1Count), p2);
                                        player1Count = player1Count - board.getSnakes().get(player1Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }

                        if(board.getLadders().containsKey(player1Count)){
                            Thread thread = new Thread(){
                                public void  run(){
                                    try {
                                        Thread.sleep(1000);
                                        System.out.println("Ladder");
                                        moveInPositiveDirection(player1, board.getLadders().get(player1Count), p2);
                                        player1Count = player1Count + board.getLadders().get(player1Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }
                    }
                    else{
                        Stage st = (Stage) ap.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("ending.fxml"));
                        Scene sc = new Scene(fxmlLoader.load());
                        st.setScene(sc);
                        st.show();
                    }

                }
            } else{
                if(game.isValidMove(player2, num, player2Count)){
                    System.out.println("Player pos2: "+player2Count);
                    if(!game.isComplete(player2Count+num)){
                        moveInPositiveDirection(player2, num, p1);
                        player2Count = player2Count + num;
                        if(board.getSnakes().containsKey(player2Count)){
                            Thread thread = new Thread(){
                                public void run(){
                                    try {
                                        Thread.sleep(1000);
                                        System.out.println("Snake");
                                        moveInNegativeDirection(player2, board.getSnakes().get(player2Count), p1);
                                        player2Count = player2Count - board.getSnakes().get(player2Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }
                        if(board.getLadders().containsKey(player2Count)){
                            Thread thread = new Thread(){
                                public void run(){
                                    try{
                                        System.out.println("Ladder");
                                        Thread.sleep(1000);
                                        moveInPositiveDirection(player2, board.getLadders().get(player2Count), p1);
                                        player2Count = player2Count + board.getLadders().get(player2Count);
                                    }
                                    catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }
                    }
                    else{
                        Stage st = (Stage) ap.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ending.fxml"));
                        Parent root = fxmlLoader.load();
                        Scene sc = new Scene(root);
                        st.setScene(sc);
                    }
                }
            }
        }
        count++;
    }

    public void moveInNegativeDirection(Player p, int num, Circle p1){
        j = p.getJth();
        step = 0;
        i = p.getIth();

        Thread thread = new Thread(){
            public void run(){
                try {
                    while(step < num){
                        if(i%2!=0){
                            if(j==0){
                                p1.setTranslateY(p1.getTranslateY() + 57);
                                Thread.sleep(100);
                                j = 9;
                                i++;
                                step++;
                            }
                            else{
                                p1.setTranslateX(40+ p1.getTranslateX());
                                Thread.sleep(100);
                                j--;
                                step++;

                            }
                        }
                        else{
                            if(j==0){
                                p1.setTranslateY(p1.getTranslateY() + 57);
                                Thread.sleep(100);
                                j = 9;
                                i++;
                                step++;
                            }
                            else{
                                p1.setTranslateX(p1.getTranslateX()-40);
                                Thread.sleep(100);
                                j--;
                                step++;

                            }
                        }
                    }
                    p.setJth(j);
                    p.setIth(i);
                    p.setPosition( p.getPosition() - num);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void moveInPositiveDirection(Player p, int num, Circle p1){
        j = p.getJth();
        step = 0;
        i = p.getIth();

        Thread thread = new Thread(){
            public void run(){
                try {
                    while(step < num){
                        if(i%2==0){
                            if(j==9){
                                p1.setTranslateY(p1.getTranslateY() - 57);

                                Thread.sleep(100);
                                j = 0;
                                i++;
                                step++;
                            }
                            else{
                                p1.setTranslateX(40+ p1.getTranslateX());
                                Thread.sleep(100);
                                j++;
                                step++;

                            }
                        }
                        else{
                            if(j==9){
                                p1.setTranslateY(p1.getTranslateY() - 57);

                                Thread.sleep(100);
                                j = 0;
                                i++;
                                step++;
                            }
                            else{
                                p1.setTranslateX(p1.getTranslateX()-40);
                                Thread.sleep(100);
                                j++;
                                step++;
                            }
                        }
                    }
                    p.setJth(j);
                    p.setIth(i);
                    p.setPosition(num + p.getPosition());
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}