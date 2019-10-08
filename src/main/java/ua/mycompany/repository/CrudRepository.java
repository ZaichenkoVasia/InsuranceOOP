package ua.mycompany.repository;

import java.util.ArrayList;
import java.util.Optional;

public interface CrudRepository<T, E> {

    T save(T user);

    Optional<T> findById(E id);

    void update(T user);

    Optional<T> deleteById(E id);

    ArrayList<T> findAll();
}
