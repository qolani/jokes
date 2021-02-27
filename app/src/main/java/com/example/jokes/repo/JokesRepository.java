package com.example.jokes.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.interfaces.IJokesRepository;
import com.example.jokes.remote.RemoteClient;
import com.example.jokes.remote.RemoteService;
import com.example.jokes.room.AppDao;
import com.example.jokes.room.AppDatabase;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class JokesRepository implements IJokesRepository {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Long> jokesId = new MutableLiveData<>();
    private static volatile JokesRepository jokesRepository;
    private RemoteClient remoteClient;
    private AppDatabase appDatabase;
    private Long createdId;
    private AppDao appDao;

    public static JokesRepository getJokesRepository(Application application) {
        if (jokesRepository == null) {
            synchronized (JokesRepository.class) {
                if (jokesRepository == null) {
                    jokesRepository = new JokesRepository(application);
                }
            }
        }
        return jokesRepository;
    }

    public JokesRepository(Application application) {
        appDatabase = AppDatabase.getAppDatabase(application);
        remoteClient = RemoteService.getInstance();
        appDao = appDatabase.getDao();
    }

    @Override
    public MutableLiveData<Long> OnGetSearchJokes(String keyword) {
        appDao.OnDeleteJokes();

        compositeDisposable.add(
                remoteClient.OnSearchJokes(keyword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<Joke>>() {
                            @Override
                            public void onNext(List<Joke> response) {
                                try{
                                    for (Joke item : response) {
                                        Joke joke = new Joke(item.getJokeId(), item.getIcon_url(), item.getUrl(), item.getValue());
                                        createdId = appDao.OnCreateJokes(joke);
                                    }

                                    if (createdId > 0) {
                                        jokesId.setValue(createdId);
                                    }
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("TAGS", e.toString());
                                jokesId.setValue(null);
                            }

                            @Override
                            public void onComplete() {
                                Log.d("TAGS", "completed");
                            }
                        })
        );
        return jokesId;
    }

    public void onDisposeObservable() {
        compositeDisposable.clear();
    }
}
