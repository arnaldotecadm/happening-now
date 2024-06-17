package com.happeningnow.controller;

import com.happeningnow.service.ServiceAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public abstract class AbstractController<T, S extends ServiceAbstract<T, ?>> {

    @Autowired
    protected S service;

    @PostMapping("/save")
    public ResponseEntity<T> save(@RequestBody T entity){
        T save = service.save(entity);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<T> findByid(@PathVariable UUID id){
        T entity = service.findById(id);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<T>> findAll(Pageable pageAble){
        PageRequest pageRequest = PageRequest.of(pageAble.getPageNumber(),
                pageAble.getPageSize());
        Page<T> entityList = service.list(pageRequest);
        return new ResponseEntity<>(entityList,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.deleteById(id);
    }
}