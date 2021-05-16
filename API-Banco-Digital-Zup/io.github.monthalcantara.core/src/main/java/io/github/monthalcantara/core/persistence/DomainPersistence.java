package io.github.monthalcantara.core.persistence;

public interface DomainPersistence<T> {

    T save(T object);

    T update(T object);

    T find(T object);

    T findById(String id);

}
