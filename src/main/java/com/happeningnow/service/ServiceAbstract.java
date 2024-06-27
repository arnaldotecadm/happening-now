package com.happeningnow.service;

import com.happeningnow.configurations.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public abstract class ServiceAbstract<T, R extends JpaRepository<T, UUID>> implements IBaseService<T, UUID>{

    protected final R repository;

    public ServiceAbstract(R repository) {
        this.repository = repository;
    }

    public T save(T entity){
        return repository.save(entity);
    }

    public T findById(UUID uuid){
        return repository.findById(uuid)
                .orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    public Page<T> list(PageRequest pageAble){
        return repository.findAll(pageAble);
    }

    public void deleteById(UUID uuid){
        repository.deleteById(uuid);
    }
}