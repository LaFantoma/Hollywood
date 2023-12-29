package com.generation.avvio;

import static com.generation.util.DbUtil.quota;

import java.sql.Connection;

import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class Avvio2 {

    public static void main(String[] args) throws Exception {

        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);
        
        Console.print("Inserisci titolo film:");
        String film = Console.readString();

        List<Film> res = db.readFilteredFilm(" title= " + quota(film));

        if(res.isEmpty())
            Console.print("404 Film Not Found");
        Console.print(res);
    }

}
