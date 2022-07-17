package com.mateacademy.books.info.service;

public interface GenericService<T> {
    T create(T model);

    T update(T model, Long modelId);

    void delete(Long modelId);
}
