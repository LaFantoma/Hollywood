package com.generation.avvio;

import java.sql.Connection;

import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class Avvio4e5 {
    public static void main(String[] args) throws Exception {
        
        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);

        List<Film> res = db.readOrderFilm("revenue-budget DESC LIMIT 1");
        Console.print(res);

        List<Film> res2 = db.readOrderFilm("score LIMIT 1");
        Console.print(res2);
    }

}
