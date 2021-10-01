package GoGlobalProject.APIApp.Interfaces;

import java.util.List;

public interface IService<T> {

    T GetById(long id);

    List<T> GetAll();

    boolean Create(T t);

    boolean Update(T tOriginal, T tDetails, long id);

    void Delete(long id);
}
