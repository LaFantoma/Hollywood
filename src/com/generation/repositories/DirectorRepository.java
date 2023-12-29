package com.generation.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.generation.entities.Director;
import com.generation.library.Console;
import com.generation.library.List;

public class DirectorRepository {

    private Connection con;

    
    public DirectorRepository(Connection tubo){

        this.con = tubo;
    }

    public void askDirector()throws Exception{

        Director d = new Director();

        Console.print("Inserisci nome:");
        d.setName(Console.readString());

        Console.print("Inserisci id cognome:");
        d.setSurname(Console.readString());

        Console.print("Inserisci data di nascita: (yyyy-MM-dd)");
        d.setDob(LocalDate.parse(Console.readString()));

        Console.print("Inserisci nazionalità:");
        d.setNationality(Console.readString());

        insertDirector(d);
    }

    public void insertDirector(Director d)throws Exception{

        Statement s = con.createStatement();
        s.execute(d.toInsertQuery());
        s.close();
    }

    public void updateDirectror(Director d)throws Exception{

        Statement s = con.createStatement();
        s.execute(d.toUpdateQuery());
        s.close();
    }

    public void deleteDirector(Director d)throws Exception{

        Statement s = con.createStatement();
        s.execute(d.toDeleteQuery());
        s.close();
    }

    public Director readOne(int id)throws Exception{

        Director res = new Director();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM director WHERE id="+id);

        if(rs.next())
        {            
            res=_rsToDirector(rs);
            s.close();
            return res;
        }
        s.close();
        return null;
    }

    public List<Director> readAll()throws Exception{

        List<Director> res = new List<Director>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM director");

        while(rs.next())
            res.add(_rsToDirector(rs));

        s.close();
        return res;
    }

    public List<Director> readFiltered(String condition)throws Exception{

        List<Director> res = new List<Director>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM director WHERE " + condition);

        while(rs.next())
            res.add(_rsToDirector(rs));

        s.close();
        return res;
    }

    private Director _rsToDirector(ResultSet rs) throws Exception{

        Director d = new Director();
        d.setId(rs.getInt("id"));
        d.setName(rs.getString("name"));
        d.setSurname(rs.getString("surname"));
        d.setDob(LocalDate.parse(rs.getString("dob")));
        d.setNationality(rs.getString("nationality"));
        return d;
    }

}
