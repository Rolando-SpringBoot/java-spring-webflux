package com.project.reactor.mono;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

public class Example03 {

  public static void main(String[] args) {
    /*
      zip(static) -> Merge given monos into a new Mono that will be fulfilled when all the given Monos
                     have produced an item, aggregating their values into a Tuple2.
                     An error or empty completion of any source will cause other sources to be cancelled and
                     the resulting Mono to immediately error or complete, respectively.
       withZip(instance) -> Combine the result from this mono and another into a Tuple2.
                            An error or empty completion of any source will cause the other source to be
                            cancelled and the resulting Mono to immediately error or complete, respectively.
     */
    Mono<String> m1 = Mono.just("Learn Code With Durgesh");
    Mono<String> m2 = Mono.just("Subscribe to this channel");
    Mono<Integer> m3 = Mono.just(12345);

    Mono<String> m4 = Mono.zip(m1, m3, (cad, num) -> cad + ":" + num)
        .log();

    m4.subscribe(System.out::println);

    Mono<Tuple3<String, String, Integer>> zipMono =  Mono.zip(m1, m2, m3);

    zipMono.subscribe(System.out::println); // [Learn Code With Durgesh,Subscribe to this channel,12345]
    zipMono.subscribe(data -> {
      System.out.println(data.getT1()); // Learn Code With Durgesh
      System.out.println(data.getT2()); // Subscribe to this channel
      System.out.println(data.getT3()); // 12345
    });

    Mono<Tuple2<Tuple2<String, String>, Integer>> zipWithMono = m1.log().zipWith(m2).log().zipWith(m3).log();
    zipWithMono.subscribe(System.out::println);
    zipWithMono.subscribe(data -> {
      System.out.println(data.getT1().getT1()); // Learn Code With Durgesh
      System.out.println(data.getT1().getT2()); // Subscribe to this channel
      System.out.println(data.getT2()); // 12345
    });

  }

}
