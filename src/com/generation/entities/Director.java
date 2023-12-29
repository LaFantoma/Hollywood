package com.generation.entities;

import java.time.LocalDate;
import static com.generation.util.DbUtil.quota;

import com.generation.library.List;

public class Director {

    private int id;
    private String name, surname;
    private LocalDate dob;
    private String nationality;

    private List<Film> myFilms;

    //GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        
        if(id<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        
        if(name==null || name.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        
        if(surname==null || surname.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        
        if(dob.isAfter(LocalDate.now()))
            throw new RuntimeException("Data non valida");
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        
        if(nationality==null || nationality.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.nationality = nationality;

    }

    public List<Film> getMyFilms() {
        return myFilms;
    }

    public void setMyFilms(List<Film> myFilms) {
        
        if(myFilms==null)
            throw new RuntimeException("Lista vuota");
        this.myFilms = myFilms;
    }

    //CRUD
    public String toInsertQuery() {
        
        return  "INSERT INTO director (name, surname, dob, nationality) VALUES "+
                "("+
                    quota(this.name) + ", " +
                    quota(this.surname) + ", " +
                    quota(this.dob.toString()) + ", " +
                    quota(this.nationality) +
                ")";
    }

    public String toUpdateQuery(){

        return  "UPDATE director set name=" + quota(this.name) + ", " +
                "surname=" + quota(this.surname) + ", " +
                "dob=" + quota(this.dob.toString()) + ", " +
                "nationality=" + quota(this.nationality) + " " +     
                "WHERE id="+this.id;
    }
 
    public String toDeleteQuery(){

        return "DELETE FROM director WHERE id=" + this.id;
    }

    @Override
    public String toString() {
        return "Director [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + ", nationality="
                + nationality + "]\n" +
                "Lista film: \n" + this.myFilms;
                
    }

}
