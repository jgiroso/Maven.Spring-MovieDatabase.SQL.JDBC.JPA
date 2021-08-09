package io.zipcoder;

import io.zipcoder.persistenceapp.Movie;
import org.junit.Assert;
import org.junit.Test;

public class MovieTest {


    Movie movie = new Movie(1L, "Starship Troopers", 129, "Sci-Fi", 7.2, "PG-13");

    @Test
    public void testGetID() {
        //given
        Long expected = 1L;
        //when
        Long actual = movie.getId();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetID() {
        //given
        Long expected = 2L;
        //when
        movie.setId(2L);
        Long actual = movie.getId();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetTitle() {
        //given
        String expected = "Starship Troopers";
        //when
        String actual = movie.getTitle();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetTitle() {
        //given
        String expected = "Monsters Inc.";
        //when
        movie.setTitle("Monsters Inc.");
        String actual = movie.getTitle();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetRuntime() {
        //given
        Integer expected = 129;
        //when
        Integer actual = movie.getRuntime();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetRuntime() {
        //given
        Integer expected = 92;
        //when
        movie.setRuntime(92);
        Integer actual = movie.getRuntime();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetGenre() {
        //given
        String expected = "Sci-Fi";
        //when
        String actual = movie.getGenre();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetGenre() {
        //given
        String expected = "Animation";
        //when
        movie.setGenre("Animation");
        String actual = movie.getGenre();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetImdbScore() {
        //given
        Double expected = 7.2;
        //when
        Double actual = movie.getImdbScore();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetImdbScore() {
        //given
        Double expected = 8.1;
        //when
        movie.setImdbScore(8.1);
        Double actual = movie.getImdbScore();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetRating() {
        //given
        String expected = "PG-13";
        //when
        String actual = movie.getRating();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetRating() {
        //given
        String expected = "G";
        //when
        movie.setRating("G");
        String actual = movie.getRating();
        //then
        Assert.assertEquals(expected, actual);
    }
}

