package com.generation.avvio;

import java.sql.Connection;

import com.generation.entities.Director;
import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class EsempioAvvio 
{
    public static void main(String[] args) throws Exception
    {
        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);

        //Input da utente, qui non ne chiedo

        //Lettura da db
        //Prendiamo quello che serve dal database, ad esempio qui prendo tutti i libri
        List<Director> directors = db.readAllDirectors();
        Console.print(directors);

        //faccio i calcoli, ad esempio qui calcolo il prezzo totale
        //int totalPrice = 0;
        // for(Book b : allBooks)
           // totalPrice+=b.getPrice();

        //Stampo richiesta
        //Console.print("Il prezzo totale di tutti i libri è "+totalPrice);
    }
}
