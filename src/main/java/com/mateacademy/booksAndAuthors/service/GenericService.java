package com.mateacademy.booksAndAuthors.service;

public interface GenericService<T> {
    public T create(T model);

    public T update(T model, Long modelId);

    public void delete(Long modelId);
}
