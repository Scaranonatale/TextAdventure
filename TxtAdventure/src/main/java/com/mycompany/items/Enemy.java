package com.mycompany.items;
/**
 * La classe Enemy contiene le caratteristiche attribuite al "nemico",
 * (uomo presente nella stanza "mensa").
 * */
public class Enemy {

    private int health;

    public Enemy() {
        health = 10;
    }

    public void dealDamageEnemy(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return health <= 0;
    }

    public int showHealth() {
        return health;
    }

}

