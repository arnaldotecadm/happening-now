package com.happeningnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBaseService<T, K> {
    T save(T t);
    T findById(K id);
    Page<T> list(PageRequest pageRequest);
    void deleteById(K id);
}