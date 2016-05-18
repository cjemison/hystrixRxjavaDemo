package com.cjemison.demo.view.controller;

import com.cjemison.demo.service.ExampleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by cjemison on 5/16/16. <p>View Controller</p>
 */
@RestController
@RequestMapping(value = "/v1/")
public class ExampleControllerImpl implements ExampleController {
  private final static Logger logger = LoggerFactory.getLogger(ExampleControllerImpl.class);
  private final ExampleService exampleService;

  @Autowired
  public ExampleControllerImpl(final ExampleService exampleService) {
    this.exampleService = exampleService;
  }

  @Override
  @RequestMapping(value = "example")
  public DeferredResult<ResponseEntity<?>> findAll() {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
    logger.info("started");
    Observable<List<String>> listObservable = exampleService.findAll();
    listObservable.subscribe(c -> {
      deferredResult.setResult(ResponseEntity.ok(c));
    });
    logger.info("done");
    return deferredResult;
  }

  @Override
  @RequestMapping(value = "example/{cnt}")
  public DeferredResult<?> findAll(@PathVariable("cnt") final int cnt) {
    return createDeferredResult(exampleService.findAllObservable(cnt));
  }

  private List<String> createList(final Observable<String> listObservable) {
    logger.info("started");
    List<String> stringList = new ArrayList<>();
    listObservable.subscribe(stringList::add);
    logger.info("done");
    return stringList;
  }

  private DeferredResult<ResponseEntity<?>> createDeferredResult(Observable<String> observable) {
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
    deferredResult.setResult(ResponseEntity.ok(createList(observable)));
    return deferredResult;
  }

}
