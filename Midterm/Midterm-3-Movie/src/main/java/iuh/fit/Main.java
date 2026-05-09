package iuh.fit;

import iuh.fit.dao.MovieDao;
import iuh.fit.model.Movie;
import iuh.fit.model.Person;

import java.util.List;
import java.util.Map;

/**
 * Author : TrungNguyen
 * Date   : 4/1/2026
 * Description:
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Movie> res = MovieDao.listMoviesContainsName("C");
        res.forEach(System.out::println);

        Movie movie = new Movie("Nguyen La Sieu Anh Hung", 2026, "Nguyen la mot nguoi ban cua chinh nghia");

        boolean result = MovieDao.createMovie(movie);
        System.out.println(result?"Tao phim thanh cong":"Tao phim that bai");

        List<Movie> movies = MovieDao.listMoviesByActorAndReleased("Keanu Reeves", 2003);
        movies.forEach(System.out::println);


        MovieDao.getNoOfMoviePerActorByReleasedRange(2000, 2020).forEach((k, v)-> {
            System.out.println("Dien vien " + k + " da dong: " + v +" bo phim");
        });

        List<Person> res1 = MovieDao.listPersonByKeyword("C");
        res.forEach(System.out::println);

    }
}