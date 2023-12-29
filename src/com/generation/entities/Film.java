package com.generation.entities;

import static com.generation.util.DbUtil.quota;
import java.time.LocalDate;

public class Film {

    private int id;
    private String title;
    private LocalDate release_date;
    private double score;
    private int budget;
    private int revenue;
    private int number_of_oscars;
    private int pegi;

    private Director myDirector;
    private int director_id;
    
    //GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        
        if(id<1)
            throw new RuntimeException("Impossibile inserire valore negativo");
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        
        if(title==null || title.isBlank())
            throw new RuntimeException("Impossibile lasciare campo vuoto");
        this.title = title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        
        if(release_date.isAfter(LocalDate.now()))
            throw new RuntimeException("Data non valida");
        this.release_date = release_date;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        
        if(score<0.1 || score>10)
            throw new RuntimeException("Score non valido");
        this.score = score;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        
        if(budget<0)
            throw new RuntimeException("valore non valido");
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        
        if(revenue<0)
            throw new RuntimeException("Valore non valido");
        this.revenue = revenue;
    }

    public int getNumber_of_oscars() {
        return number_of_oscars;
    }

    public void setNumber_of_oscars(int number_of_oscars) {
        
        if(number_of_oscars<0)
            throw new RuntimeException("Valore non valido");
        this.number_of_oscars = number_of_oscars;

    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        
        if(pegi<3 || pegi>18)
            throw new RuntimeException("Valore non valido");
        this.pegi = pegi;

    }

    public Director getMyDirector() {
        return myDirector;
    }

    public void setMyDirector(Director myDirector) {
        
        if(myDirector==null)
            throw new RuntimeException("Director non valido");
        this.myDirector = myDirector;
        this.director_id = myDirector.getId();
    }

    public int getId_director() {
        return director_id;
    }

    public void setId_director(int director_id) {
        
        if(director_id<0)
            throw new RuntimeException("Valore non valido");
        this.director_id = director_id;
    }

    //CRUD
    public String toInsertQuery() {
        
        return  "INSERT INTO film (director_id, title, release_date, score, budget, revenue, number_of_oscars, pegi) VALUES "+
                "("+
                    this.director_id + ", " +
                    quota(this.title) + ", " +
                    quota(this.release_date.toString()) + ", " +
                    this.score + ", " +
                    this.budget + ", " +
                    this.revenue + ", " +
                    this.number_of_oscars + ", " +
                    this.pegi +         
                ")";
    }

    public String toUpdateQuery(){

        return  "UPDATE film set director_id=" + this.director_id + ", " +
                "title=" + quota(this.title) + ", " +
                "release_date=" + quota(this.release_date.toString()) + ", " +
                "score=" + this.score + ", " +
                "budget=" + this.budget + ", " +
                "revenue=" + this.revenue + ", " +
                "number_of_oscars=" + this.number_of_oscars + ", " +
                "pegi=" + this.pegi + " " +   
                "WHERE id="+this.id;
    }

    public String toDeleteQuery(){

        return "DELETE FROM film WHERE id=" + this.id;
    }

    @Override
    public String toString() {
        return "ID film = " + id + ", titolo = " + title + ", data release = " + release_date + "\nvalutazione = " + score
                + ", budget = " + budget + ", incasso = " + revenue + "\noscar vinti = " + number_of_oscars + ", pegi = "
                + pegi + ", regista = " + this.myDirector.getName() + " " + this.myDirector.getSurname() + "\n";
    }



}
