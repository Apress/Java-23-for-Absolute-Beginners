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
package com.apress.bgn.eight;

import com.apress.bgn.eight.util.AudioType;
import com.apress.bgn.eight.util.Song;
import com.apress.bgn.eight.util.SongTransformer;
import com.apress.bgn.eight.util.StreamMediaLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.System.out;
import static com.apress.bgn.eight.OptionalDemo.isMoreThan3MinsAndLessThenTen;

/**
 * Created by iuliana.cosmina on 26/06/2024
 */
public class MediaStreamTester {
    public static Consumer<Song> myConsumer = song -> {
        song.setSinger(song.getSinger().toUpperCase());
       out.println(song);
    };

    public static Function<Song, Integer> processDuration = song -> song.getDuration()/60;

    void main(){
        out.println("Listing 8-27. Using a stream to print `Song` instances.");
        Stream<Song> songs = StreamMediaLoader.loadSongs();
        songs.forEach(song -> out.println(song));

        out.println("Listing 8-29. Using a consumer instance to print `Song` instances.");
        songs = StreamMediaLoader.loadSongs();
        songs.forEach(song -> myConsumer.accept(song));

        songs = StreamMediaLoader.loadSongs();
        songs.forEachOrdered(song -> myConsumer.accept(song));

        out.println("Listing 8-30. Using a consumer instance to print `Song` instances.");
        songs = StreamMediaLoader.loadSongs();
        Song[] sarray = songs.filter(s -> s.getAudioType() == AudioType.MP3).toArray(Song[]::new);
        Arrays.stream(sarray).forEach(out::println);

        out.println("Listing 8-31. Using `map(..)` and `collect(..)` methods.");
        songs = StreamMediaLoader.loadSongs();
        List<Integer> durationAsMinutes = songs
                .map(SongTransformer::processDuration) // method reference
                .toList();
        durationAsMinutes.forEach(System.out::println);

        out.println("Listing 8-32. Using a `Function<T,R>` instance to process stream elements.");
        songs = StreamMediaLoader.loadSongs();
        durationAsMinutes = songs
                .map(processDuration)
                .toList();
        durationAsMinutes.forEach(out::println);

        out.println("Listing 8-33. Using `filter(..)` and `map(..)` to avoid writing `if` statements.");
        Song song0 = new Song("Ben Barnes", "You find me", 420, AudioType.FLAC);
        if(isMoreThan3MinsAndLessThenTen(song0)) {
            out.println("This song is just right!");
        }

        out.println("Listing 8-39. Adding the elements of a stream.");
        songs = StreamMediaLoader.loadSongs();
        Integer totalDuration0 = songs
                .mapToInt(Song::getDuration)
                .sum();
        out.println("Total duration: " + totalDuration0);

        out.println("----- Testing reduce(..) -----");
        songs = StreamMediaLoader.loadSongs();
        Integer totalDuration1 = songs
                .mapToInt(Song::getDuration)
                .reduce(0, (a, b) -> a + b);
        out.println("Total duration: " + totalDuration1);

        out.println("Listing 8-40. Calling `peek(..)` on stream elements.");
        songs = StreamMediaLoader.loadSongs();
        List<String> result = songs.filter(s -> s.getDuration() > 300)
                .peek(e -> out.println("\t Filtered value: " + e))
                .map(Song::getTitle)
                .peek(e -> out.println("\t Mapped value: " + e))
                .toList();


        out.println("Listing 8-41. Example using `findAny()` on a parallel stream.");
        songs = StreamMediaLoader.loadSongs();
        Optional<Song> optSong = songs.parallel().findAny();
        optSong.ifPresent(out::println);

        out.println("Listing 8-42. Example using `anyMatch(..)`.");
        songs = StreamMediaLoader.loadSongs();
        boolean b0 = songs.anyMatch(s -> s.getTitle().contains("Paper"));
        out.println("Are there songs with title containing ’Paper’? " + b0);

        out.println("Listing 8-43. Example using `skip(..)` and `anyMatch(..)`.");
        songs = StreamMediaLoader.loadSongs();
        boolean b1 = songs.parallel()
                .skip(6)
                .anyMatch(s -> s.getTitle().contains("Paper"));
        out.println("Are there songs with title containing `Paper`? " + b1);


        out.println("Listing 8-44. Showing what `allMatch(..)` can do.");
        songs = StreamMediaLoader.loadSongs();
        boolean b2 = songs.allMatch(s -> s.getDuration() > 300); // <1>
        out.println("Are all songs longer than 5 minutes? " + b2);

        out.println("Listing 8-44. Showing what `noneMatch(..)` can do.");
        songs = StreamMediaLoader.loadSongs();
        boolean b3 = songs.noneMatch(s -> s.getDuration() > 300); // <1>
        out.println("Are all songs shorter than 5 minutes? " + b3);
    }
}
