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

    public void associaDirector(Director d)throws Exception{

        List<Film> movie = fRepo.readFilmDirectedBy(d.getId());     //Creo una lista di film dove metto tutti i film diretti da res

            d.setMyFilms(movie);                                  //Associo la lista di film al regista

            for(Film f : movie)                                     //Ad ogni film della lista associo il regista
                f.setMyDirector(d);

    }

    public void associaFilm(Film f)throws Exception{

        Director d = dRepo.readOne(f.getId_director());         //Vado a cercare il regista usando la foreign key contenuta su film
        f.setMyDirector(d);                                 //Associo il regista al film 

        List<Film> movie = new List<Film>();                //Creo una lista di film dove vado ad aggiungere res
        movie.add(f);
        d.setMyFilms(movie);
    }

    public Film readFilmById(int filmId)throws Exception{

        Film res = fRepo.readOne(filmId);                   //Trovo il film con l'id e lo assegno a res
        
        associaFilm(res);

        return res;
    }

    public List<Film> readAllFilms()throws Exception{

        List<Film> res = fRepo.readAll();

        for(Film f : res)      //Per ogni film presente faccio associazione regista-a-film e film-a-regista
            associaFilm(f);

        return res;
    }

    public List<Film> readOrderFilm(String condition)throws Exception{

        List<Film> res = fRepo.readOrder(condition);

        for(Film f : res)
            associaFilm(f);
        
        return res;
    }

    public List<Director> readOrderDirector(String condition)throws Exception{

        List<Director> res = dRepo.readOrder(condition);

        for(Director d : res)
            associaDirector(d);
        
        return res;
    }

    public List<Director> readAllDirectors()throws Exception{

        List<Director> res = dRepo.readAll();

        for(Director d : res)          //Per ogni regista faccio associazione film-a-regista e regista-a-film
            associaDirector(d);

        return res;
    }

    public List<Film> filmDirectedBy(Director d)throws Exception{

        List<Film> res = fRepo.readFilmDirectedBy(d.getId());

        for(Film f : res)
            associaFilm(f);

        return res;
    }

    public List<Director> directorQuery(String query)throws Exception{

        List<Director> res = dRepo.queryGenerica(query);

        for(Director d : res)          //Per ogni regista faccio associazione film-a-regista e regista-a-film
            associaDirector(d);

        return res;
    }

    public Director readDirectorById(int dirId)throws Exception{

        Director res = dRepo.readOne(dirId);                    //Trovo il regista con l'id passato
        
        associaDirector(res);

        return res;
    }

    public void createFilm()throws Exception{

        boolean isValid = false;

        do{
            try {
                
                fRepo.askFilm();
                Console.print("Film aggiunto con successo");
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
                Console.print("Regista aggiunto con successo");
                isValid = true;

            } catch (RuntimeException e) {
                
                Console.print(e.getMessage());
            }

        }while(!isValid);
    }

    public List<Film> readFilteredFilm(String condition)throws Exception{

        List<Film> res = fRepo.readFiltered(condition);

        for(Film f : res)
            associaFilm(f);

        return res;
    }

    public List<Director> readFilteredDirector(String condition)throws Exception{

        List<Director> res = dRepo.readFiltered(condition);

        for(Director d : res)
            associaDirector(d);

        return res;
    }

    public List<Film> readFilmDirectedBy(int id)throws Exception{

        List<Film> res =  fRepo.readFilmDirectedBy(id);

        for(Film f : res)
            associaFilm(f);

        return res;
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
