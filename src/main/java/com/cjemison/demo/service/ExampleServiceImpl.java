package com.cjemison.demo.service;

import com.cjemison.demo.service.hystrix.ExampleCommand;
import com.cjemison.demo.service.hystrix.ExampleObserverCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import rx.Observable;

/**
 * Created by cjemison on 5/16/16. <p>Wrapper in front of the command.</p>
 */
@Service
public class ExampleServiceImpl implements ExampleService {
  private static final Logger logger = LoggerFactory.getLogger(ExampleServiceImpl.class);

  @Override
  public Observable<List<String>> findAll() {
    logger.info("started");
    Observable<List<String>> observable = new ExampleCommand("Cornelius").toObservable();
    logger.debug("done");
    return observable;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Observable<String> findAllObservable(final int cnt) {
    logger.info("started");
    Observable<String> observable = new ExampleObserverCommand("exampleGroup", cnt).observe();
    logger.debug("done");
    return observable;
  }
}
