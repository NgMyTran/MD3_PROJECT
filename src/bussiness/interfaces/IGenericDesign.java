package bussiness.interfaces;

import java.util.List;

public interface IGenericDesign<T,E> {
    boolean create(T t);

    List<T> findAll();

    boolean update(T t);

    boolean deleteById(E id);

    T findById(E id);
}
