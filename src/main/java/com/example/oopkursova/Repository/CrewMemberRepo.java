package com.example.oopkursova.Repository;

import com.example.oopkursova.Entity.Actors;
import com.example.oopkursova.Entity.FilmCrewMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewMemberRepo extends JpaRepository<FilmCrewMembers, Long> {
    @Query("SELECT fcm FROM FilmCrewMembers fcm JOIN FETCH fcm.movies")
    List<FilmCrewMembers> findAllWithMovies();

    @Query(value = "SELECT f.* FROM public.film_crew_member f INNER JOIN public.film_crew_member_movies fm " +
            "ON f.crew_member_id = fm.film_crew_member_id WHERE fm.movie_id = :id", nativeQuery = true)
    List<FilmCrewMembers> findCrewMembersByMovieId(Long id);

}
