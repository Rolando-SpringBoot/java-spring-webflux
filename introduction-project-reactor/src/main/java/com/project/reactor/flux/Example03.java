package com.project.reactor.flux;

import java.util.function.Function;
import reactor.core.publisher.Flux;

public class Example03 {

  public static void main(String[] args) {
    /*
      transform() -> Transform this Flux in order to generate a target Flux.
     */
    Function<Flux<String>, Flux<Integer>> funInterface = fluxName -> fluxName.map(String::length);

    Flux<Integer> f1 = Flux.just("Ankit", "Durgesh", "Ravi", "Gautam")
        .transform(funInterface).log();
    f1.subscribe(System.out::println);
  }

}
