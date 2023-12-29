package com.generation.avvio;

import java.sql.Connection;

import com.generation.entities.Director;
import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class AvvioEPoiBasta {

    public static void main(String[] args) throws Exception {
        
        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);

        List<Director> all = db.readAllDirectors();

        for(Director d : all){
            
            List<Film> films = db.filmDirectedBy(d);

            int budget = 0;
            int revenue = 0;

            for(Film f : films){
                budget += f.getBudget();
                revenue += f.getRevenue();
            }
            
            Console.print(d.getName() + " " + d.getSurname() + " => Budget totale: " + budget + " Incasso totale: " + revenue);          
        }
    }

}
