package com.generation.repositories;

import java.sql.Connection;

import com.generation.entities.Director;
import com.generation.entities.Film;
import com.generation.library.Console;
import com.generation.library.List;

public class Database {


    private DirectorRepository dRepo;
    private FilmRepository fRepo;

    public Database(Connection con){

        this.dRepo = new DirectorRepository(con);
        this.fRepo = new FilmRepository(con);
    }

    public Film readFilmById(int filmId)throws Exception{

        Film res = fRepo.readOne(filmId);                   //Trovo il film con l'id e lo assegno a res
        
        Director d = dRepo.readOne(res.getId_director());   //Vado a cercare il regista usando la foreign key contenuta su film
        res.setMyDirector(d);                               //Associo il regista al film 

        List<Film> movie = new List<Film>();                //Creo una lista di film dove vado ad aggiungere res
        movie.add(res);
        d.setMyFilms(movie);                                //Associo la lista di film al regista DUBBIO:quando leggo un altro film di quel regista, la lista si sovrascrive?

        return res;
    }

    public List<Film> readAllFilms()throws Exception{

        List<Film> res = fRepo.readAll();

        for(Film f : res){      //Per ogni film presente faccio associazione regista-a-film e film-a-regista

            Director d = dRepo.readOne(f.getId_director());
            f.setMyDirector(d);

            List<Film> movie = new List<Film>();                
            movie.add(f);
            d.setMyFilms(movie);
        }

        return res;
    }

    public List<Director> readAllDirectors()throws Exception{

        List<Director> res = dRepo.readAll();

        for(Director d : res){          //Per ogni regista faccio associazione film-a-regista e regista-a-film

            List<Film> movie = fRepo.readFilmDirectedBy(d.getId());
            d.setMyFilms(movie);

            for(Film f : movie)
                f.setMyDirector(d);
        }

        return res;
    }

    public Director readDirectorById(int dirId)throws Exception{

        Director res = dRepo.readOne(dirId);                    //Trovo il regista con l'id passato
        List<Film> movie = fRepo.readFilmDirectedBy(dirId);     //Creo una lista di film dove metto tutti i film diretti da res

        res.setMyFilms(movie);                                  //Associo la lista di film al regista

        for(Film f : movie)                                     //Ad ogni film della lista associo il regista
            f.setMyDirector(res);

        return res;
    }

    public void createFilm()throws Exception{

        boolean isValid = false;

        do{
            try {
                
                fRepo.askFilm();
                isValid = true;

            } catch (RuntimeException e) {
                
                Console.print(e.getMessage());
            }

        }while(!isValid);
    }

    public void creareDirector()throws Exception{

        boolean isValid = false;

        do{
            try {
                
                dRepo.askDirector();
                isValid = true;

            } catch (RuntimeException e) {
                
                Console.print(e.getMessage());
            }

        }while(!isValid);
    }

    public List<Film> readFilteredFilm(String condition)throws Exception{

        return fRepo.readFiltered(condition);
    }

    public List<Director> readFilteredDirector(String condition)throws Exception{

        return dRepo.readFiltered(condition);
    }

    public List<Film> readFilmDirectedBy(int id)throws Exception{

        return fRepo.readFilmDirectedBy(id);
    }

    public void updateFilm(Film f)throws Exception{

        fRepo.updateFilm(f);
    }

    public void updateDirectror(Director d)throws Exception{

        dRepo.updateDirectror(d);
    }

    public void deleteFilm(Film f)throws Exception{

        fRepo.deleteFilm(f);
    }

    public void deleteDirector(Director d)throws Exception{

        dRepo.deleteDirector(d);
    }
}
