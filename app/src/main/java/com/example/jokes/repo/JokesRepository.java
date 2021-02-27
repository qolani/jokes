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

    /****
     * Property declaration
     */
    private MutableLiveData<JokeResponse> listMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Joke> jokeMutableLiveData = new MutableLiveData<>();
    private static volatile JokesRepository jokesRepository;
    private RemoteClient remoteClient;

    /****
     * Singleton for JokesRepository
     * @param application
     * @return
     */
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

    /****
     * Constructor
     * @param application
     */
    public JokesRepository(Application application) {
        remoteClient = RemoteService.getInstance();
    }

    /*****
     * Search for jokes using rxJava/rxAndroid.
     * Used CompositeDisposable tom group all the disposable objects and dispose them at once.
     * @param keyword
     * @return
     */
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
                                    listMutableLiveData.setValue(null);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
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

    /*****
     * Get a random joke using rxJava/rxAndroid.
     * Used CompositeDisposable tom group all the disposable objects and dispose them at once.
     * @return
     */
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
                                    jokeMutableLiveData.setValue(null);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
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

    /****
     * Dispose all objects at once
     */
    public void onDisposeObservable() {
        compositeDisposable.clear();
    }
}
