package com.project.reactor.flux;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example04 {

  public static void main(String[] args) {
    /*
      defaultIfEmpty() -> Provide a default unique value if this sequence is
                          completed without any data
      switchIfEmpty() -> Switch to an alternative Publisher if this sequence is completed without
                         any data. So you can return a Mono or Flux with it.
     */
    Flux<String> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .filter(name -> name.length() > 8)
        .defaultIfEmpty("Hello world!")
        .log();
    f1.subscribe(System.out::println);

    Flux<String> f2 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .filter(name -> name.length() > 8)
        .switchIfEmpty(Flux.fromIterable(List.of("Mongo", "Apple")))
        .log();
    f2.subscribe(System.out::println);

  }

}
