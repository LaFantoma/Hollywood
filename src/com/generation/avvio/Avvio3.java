package com.generation.avvio;

import java.sql.Connection;

import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class Avvio3 {
    public static void main(String[] args) throws Exception {
        
        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);

        List<Film> res = db.readOrderFilm("budget DESC LIMIT 1");
        Console.print(res);
    }

}
