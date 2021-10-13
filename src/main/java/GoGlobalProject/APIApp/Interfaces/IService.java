package GoGlobalProject.APIApp.Interfaces;

import java.util.List;

public interface IService<T> {

    List<T> GetAll();

    boolean Create(T t);

    boolean Update(long tId, T tDetails);

    void Delete(long id);
}
