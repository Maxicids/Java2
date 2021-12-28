package by.malinka.service;

public interface IUserService<T> extends IService<T> {
    T findByEmail(String email);
}
