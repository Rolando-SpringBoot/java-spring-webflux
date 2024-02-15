package com.project.reactor.mono;

import java.util.Arrays;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example04 {

  public static void main(String[] args) {
    /*
      map() -> Transform the item emitted by this Mono by applying a synchronous function to it.
      flatMap() -> Transform the item emitted by this Mono asynchronously, returning the value emitted
                   by another Mono (possibly changing the value type).
      flatMapMany() -> Transform the item emitted by this Mono into a Publisher,
                       then forward its emissions into the returned Flux
     */
    Mono<String> m1 = Mono.just("Learn Code With Durgesh");
    Mono<String> m2 = Mono.just("Subscribe to this channel");
    Mono<Integer> m3 = Mono.just(12345);
    Mono<Object> monoError = Mono.error(new RuntimeException("Error!!!"));

    Mono<String> resultMapMono = m1.map(String::toUpperCase);
    resultMapMono.subscribe(System.out::println); // LEARN CODE WITH DURGESH

    Mono<String[]> resultFlatMapMono = m1.flatMap(valueM1 -> Mono.just(valueM1.split(" ")));
    resultFlatMapMono.subscribe(data -> System.out.println(Arrays.toString(data))); // [Learn, Code, With, Durgesh]

    Flux<String> stringFlux = m1.flatMapMany(valueM1 -> Flux.just(valueM1.split(" "))).log();
    stringFlux.subscribe(System.out::println, System.out::println);
  }

}
