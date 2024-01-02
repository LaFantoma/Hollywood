package com.generation.avvio;

import java.sql.Connection;

import com.generation.repositories.Database;
import com.generation.util.DbUtil;

public class AvvioCreazione {
    public static void main(String[] args)throws Exception {
        
        Connection con = DbUtil.connectToDB("hollywood");
        Database db = new Database(con);

        db.createFilm();
    }

}
