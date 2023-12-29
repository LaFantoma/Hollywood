package com.generation.avvio;

import static com.generation.util.DbUtil.quota;

import java.sql.Connection;

import com.generation.entities.Director;
import com.generation.library.Console;
import com.generation.library.List;
import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class Avvio1 {

    public static void main(String[] args) throws Exception {
        
        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);

        Console.print("Inserisci nome regista:");
        String name = Console.readString();
        Console.print("Inserisci cognome");
        String surname = Console.readString();

        List<Director> res = db.readFilteredDirector("name= " +quota(name) + " AND surname= " + quota(surname));
            
        if(res.isEmpty())
            Console.print("404 Director Not Found");
        Console.print(res);
     
        
    }

}
