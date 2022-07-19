package com.mateacademy.books.info.service;

public interface GenericService<T> {
    T create(T model);

    T update(T model);

    void delete(Long modelId);
}
