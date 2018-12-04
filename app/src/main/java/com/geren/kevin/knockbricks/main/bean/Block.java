package com.geren.kevin.knockbricks.main.bean;

public class Block {

    private int id;
    private int x;
    private int y;
    private int value;

    public Block(int id, int x, int y, int value) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}
