package com.example.jokes.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jokes.entities.Joke;
import com.example.jokes.entities.JokeResponse;
import com.example.jokes.interfaces.IJokesRepository;
import com.example.jokes.remote.RemoteClient;
import com.example.jokes.remote.RemoteService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class JokesRepository implements IJokesRepository {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<JokeResponse> listMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Joke> jokeMutableLiveData = new MutableLiveData<>();
    private static volatile JokesRepository jokesRepository;
    private RemoteClient remoteClient;

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
        remoteClient = RemoteService.getInstance();
    }

    @Override
    public MutableLiveData<JokeResponse> OnSearchJokes(String keyword) {

        compositeDisposable.add(
                remoteClient.OnSearchJokes(keyword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<JokeResponse>() {
                            @Override
                            public void onNext(JokeResponse response) {
                                try{
                                    listMutableLiveData.setValue(response);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("TAGS", e.toString());
                                listMutableLiveData.setValue(null);
                            }

                            @Override
                            public void onComplete() {
                                Log.d("TAGS", "completed");
                            }
                        })
        );
        return listMutableLiveData;
    }

    @Override
    public MutableLiveData<Joke> OnGetRandomJoke() {
        compositeDisposable.add(
                remoteClient.OnGetRandomJoke()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<Joke>() {
                            @Override
                            public void onNext(Joke joke) {
                                try{
                                    jokeMutableLiveData.setValue(joke);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("TAGS", e.toString());
                                jokeMutableLiveData.setValue(null);
                            }

                            @Override
                            public void onComplete() {
                                Log.d("TAGS", "completed");
                            }
                        })
        );
        return jokeMutableLiveData;
    }

    public void onDisposeObservable() {
        compositeDisposable.clear();
    }
}
