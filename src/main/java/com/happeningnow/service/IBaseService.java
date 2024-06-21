package com.happeningnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBaseService<T, UUID> {
    T save(T t);
    T findById(UUID id);
    Page<T> list(PageRequest pageRequest);
    void deleteById(UUID id);
}