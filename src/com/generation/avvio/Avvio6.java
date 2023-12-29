package com.generation.avvio;

import java.sql.Connection;

import com.generation.entities.Director;
import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class Avvio6 {
    public static void main(String[] args) throws Exception {
        
        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);

        List<Director> all = db.readAllDirectors();
        
        int maxOscar = 0;
        Director bravo = null;

        for(Director d : all){

            List<Film> films = db.filmDirectedBy(d);
            int oscar = 0;

            for(Film f : films)
                oscar += f.getNumber_of_oscars();
            

            if(oscar>maxOscar){
                maxOscar = oscar;
                bravo = d;
            }
        }

        Console.print(bravo);
    }

}
