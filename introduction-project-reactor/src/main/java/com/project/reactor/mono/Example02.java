package com.project.reactor.mono;

import reactor.core.publisher.Mono;

public class Example02 {

  public static void main(String[] args) {
    /*
      error() -> Create a Mono that terminates with the specified error immediately after being subscribed to.
      then() -> Let this Mono complete then play another Mono. In other words ignore element from this Mono
                and transform its completion signal into the emission and completion signal of a provided Mono<V>.
                Error signal is replayed in the resulting Mono<V>.
     */
    Mono<Object> errorMono = Mono.error(new RuntimeException("Error !!"));

    errorMono.subscribe(System.out::println,
        throwable -> System.out.println("Error: " + throwable.getMessage()));

    Mono<String> m1 = Mono.just("good morning");
    Mono<String> then = Mono.just("Hello World!!")
        .log().then(errorMono).log().then(m1).log();

    then.subscribe(System.out::println, System.out::println);

  }

}
