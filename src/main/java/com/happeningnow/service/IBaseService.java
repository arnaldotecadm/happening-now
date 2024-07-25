package com.happeningnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBaseService<T, U> {
    T save(T t);
    T findById(U id);
    Page<T> list(PageRequest pageRequest);
    void deleteById(U id);
}