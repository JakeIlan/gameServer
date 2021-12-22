package com.example.game;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
class Player {
    private @Id @GeneratedValue Long id;
    private Integer num;
    private String name;
    private Integer score;

    public Player() {}

    public Player(String name) {
        this.num = 0;
        this.name = name;
        this.score = 0;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer order) {
        this.num = order;
    }

    public void addScore(Integer score) {
        this.score += score;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id) && name.equals(player.name) && score.equals(player.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, score);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}