package com.egreksystems.yoloveggies;

public class Fruit {
    String fruit,  image, score;

    public Fruit(String fruit, String image, String score) {
        this.fruit = fruit;
        this.image = image;
        this.score = score;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
