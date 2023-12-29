package com.generation.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;

public class FilmRepository {

    private Connection con;

    public FilmRepository(Connection tubo){

        this.con = tubo;
    }

    public void askFilm()throws Exception{

        Film f = new Film();

        Console.print("Inserisci titolo:");
        f.setTitle(Console.readString());

        Console.print("Inserisci id regista:");
        f.setId_director(Console.readInt());

        Console.print("Inserisci release date: (yyyy-MM-dd)");
        f.setRelease_date(LocalDate.parse(Console.readString()));

        Console.print("Inserisci score:");
        f.setScore(Console.readDouble());

        Console.print("Inserisci budget:");
        f.setBudget(Console.readInt());

        Console.print("Inserisci incasso:");
        f.setRevenue(Console.readInt());

        Console.print("Inserisci numero di oscar:");
        f.setNumber_of_oscars(Console.readInt());

        Console.print("Inserisci PEGI:");
        f.setPegi(Console.readInt());

        insertFilm(f);
    }

    public void insertFilm(Film f)throws Exception{

        Statement s = con.createStatement();
        s.execute(f.toInsertQuery());
        s.close();
    }

    public void updateFilm(Film f)throws Exception{

        Statement s = con.createStatement();
        s.execute(f.toUpdateQuery());
        s.close();
    }

    public void deleteFilm(Film f)throws Exception{

        Statement s = con.createStatement();
        s.execute(f.toDeleteQuery());
        s.close();
    }

    public Film readOne(int id)throws Exception{

        Film res = new Film();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM film WHERE id="+id);

        if(rs.next())
        {            
            res=_rsToFilm(rs);
            s.close();
            return res;
        }
        s.close();
        return null;
    }

    public List<Film> readAll()throws Exception{

        List<Film> res = new List<Film>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM film");

        while(rs.next())
            res.add(_rsToFilm(rs));

        s.close();
        return res;
    }

    public List<Film> readFiltered(String condition)throws Exception{

        List<Film> res = new List<Film>();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM film WHERE " + condition);

        while(rs.next())
            res.add(_rsToFilm(rs));

        s.close();
        return res;
    }

    public List<Film> readFilmDirectedBy(int id)throws Exception{

        return readFiltered("director_id="+id);
    }

    private Film _rsToFilm(ResultSet rs) throws Exception{

        Film f = new Film();
        f.setId(rs.getInt("id"));
        f.setId_director(rs.getInt("director_id"));
        f.setTitle(rs.getString("title"));
        f.setRelease_date(LocalDate.parse(rs.getString("release_date")));
        f.setScore(rs.getInt("score"));
        f.setBudget(rs.getInt("budget"));
        f.setRevenue(rs.getInt("revenue"));
        f.setNumber_of_oscars(rs.getInt("number_of_oscars"));
        f.setPegi(rs.getInt("pegi"));
        return f;
    }

    public List<Film> readOrder(String condition) throws Exception {
        
        List<Film> res = new List<Film>();

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM film ORDER BY " + condition);

        while(rs.next())
            res.add(_rsToFilm(rs));

        s.close();
        return res;
    }
}
