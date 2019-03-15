package com.lcc.demo.controllertest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author lcc
 * @version 2019/3/6
 */
@RestController
public class WebfluxController {

  @GetMapping("/hello1/{name}")
  public <T> Mono<T> controllerTest1(@PathVariable T name) {
    return Mono.just(name);
  }

  @GetMapping("/hello2/{name}")
  public Mono<String> controllerTest2(@PathVariable String name) {
    return Mono.just("hello-" + name + "!");
  }
}
