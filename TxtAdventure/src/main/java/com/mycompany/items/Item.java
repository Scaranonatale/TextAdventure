/**
* La classe Item rappresenta gli "oggetti" che possono essere raccolti e lasciati,
* é rappresentata come una classe astratta che verrà ereditata da ogni singolo oggetto(coltello,pala...).
**/
package com.mycompany.items;

import com.mycompany.parser.Command;
import com.mycompany.type.Player;


public abstract class Item {

    public String itemDescription;

    public Item(String itemDescription, int weigth) {

        this.itemDescription = itemDescription;

    }

    public abstract void useItem(Command command, Player player);
    
    public abstract String getDesc();
}
