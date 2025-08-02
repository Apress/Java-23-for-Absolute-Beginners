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
package com.apress.bgn.twelve.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

/**
 * @author iuliana.cosmina on 27/08/2024
 */
public class FluxDemo {
    static RandomGenerator randomGenerator = RandomGenerator.of("SecureRandom");
    void main() {
        String[] names0 = {"Joy", "John", "Anemona", "Takeshi"};
        Flux.fromArray(names0).subscribe(new GenericSubscriber<>());

        Flux<Integer> intFlux = Flux.empty();
        intFlux.subscribe(new GenericSubscriber<>());
        // ----
        Flux<String> dummyStr = Flux.just("one", "two", "three");
        Flux<Integer> dummyInt = Flux.just(1,2,3);

        Mono<Integer> one = Mono.just(1);
        Mono<String> empty = Mono.empty();
        // ----
        String[] names1 = {"Joy", "John", "Anemona", "Takeshi"};
        Flux<String> namesFlux = Flux.fromArray(names1);

        String[] names2 = {"Hanna", "Eugen", "Anthony", "David"};
        Flux<String> names2Flux = Flux.fromArray(names2);
        Flux<String> combined = Flux.concat(namesFlux, names2Flux);
        combined.subscribe(new GenericSubscriber<>());
        //----
        Flux<Integer> infiniteFlux = Flux.fromStream(
                Stream.generate(() -> randomGenerator.nextInt(150))
        );
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));
        Flux<Integer> delayedInfiniteFlux = infiniteFlux.zipWith(delay, (s,l) -> s);
        delayedInfiniteFlux.subscribe(new GenericSubscriber<>());
    }
}
