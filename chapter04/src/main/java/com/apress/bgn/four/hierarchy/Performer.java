/*
Freeware License, some rights reserved

Copyright (c) 2024 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.four.hierarchy;

import com.apress.bgn.four.classes.Gender;

import java.util.List;
import java.util.Objects;

/**
 * Created by iuliana.cosmina on 08/04/2024
 */
public class Performer extends Human implements Musician, Actor {

    private String school;
    private String genre;
    private List<String> songs;
    private List<String> films;

    public Performer(String name, int age, float height, Gender gender) {
        super(name, age, height, gender);
    }

    public Performer(String name, int age, float height, Gender gender, List<String> films) {
        super(name, age, height, gender);
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        if (o == null || !(o instanceof Performer)) return false;
        //Performer other = (Performer) o;
        return (o instanceof Performer other)
                && Objects.equals(school, other.school)
                && Objects.equals(genre, other.genre)
                && Objects.equals(songs, other.songs)
                && Objects.equals(films, other.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(school, genre, songs, films);
    }

    @Override
    public String toString() {
        return "Performer{" +
                "school='" + school + '\'' +
                ", genre='" + genre + '\'' +
                ", songs=" + songs +
                ", films=" + films +
                '}';
    }

    public String getCapitalizedName() {
        return Artist.capitalize(this.name);
    }

    @Override
    public int getTimeToLive() {
        return (LIFESPAN - getAge()) / 2;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String musicSchool) {
        this.school = musicSchool;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public void addSong(String song) {
        this.songs.add(song);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public void addFilm(String filmName) {
        this.films.add(filmName);
    }
}

