package com.example.facelite;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Post {
    String userName,text;
    int likesNum;
    Image image;
    ArrayList<Post> comments = new ArrayList<>();
    ArrayList<Image> images = new ArrayList<>();
    Post(String userName,String text){
        this.text = text;
        this.userName = userName;
        this.likesNum = 0;
    }
    Post(String userName,String text,Image image){
        this(userName,text);
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public int getLikesNum() {
        return likesNum;
    }

    public Image getImage() {
        return image;
    }

    public ArrayList<Post> getComments() {
        return comments;
    }
    public void setComments(ArrayList<Post> comments) {
        this.comments = comments;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void addLikes() {
        this.likesNum++;
    }
    public void addLikes(int num){
        this.likesNum+=num;
    }
    public int getCommentsNum() {
        return comments.size();
    }
    public Pane getPane(){
        VBox vPost = new VBox();
        vPost.getChildren().addAll(new Label(this.userName),new ImageView(this.image),new Label(this.text));

        return vPost;
    }

    @Override
    public String toString() {
        return text.length() > 20 ?  this.userName + ": "+ this.text.substring(0,20) : this.userName + ": "+ this.text ;
    }
}
