package com.cjemison.demo.service;

import java.util.List;

import rx.Observable;

/**
 * Created by cjemison on 5/16/16.
 */
public interface ExampleService {

  Observable<List<String>> findAll();

}
