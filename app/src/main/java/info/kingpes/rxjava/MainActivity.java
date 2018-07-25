package info.kingpes.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observableForm();
            }
        });

    }

    private void observableCreate(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("OnNext", String.valueOf(integer));
            }
        });
    }

    private void observableInterval(){
        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                if (aLong == 5)
                    unsubscribe();

                Log.d("OnNext", String.valueOf(aLong));

            }
        });
    }

    private void observable(){
        Movie movie = new Movie("Film1");
        rx.Observable<Movie> movieObservable = rx.Observable.just(movie);
        movie = new Movie("Film2");
        movieObservable.subscribe(new Subscriber<Movie>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Movie movie) {
                Log.d("OnNext", movie.getName());
            }
        });
    }

    private void observableDefer(){
       movie = new Movie("Film1");
        rx.Observable<Movie> movieObservable = rx.Observable.defer(new Func0<rx.Observable<Movie>>() {
            @Override
            public rx.Observable<Movie> call() {
                return rx.Observable.just(movie);
            }
        });

        movie = new Movie("Film2");
        movieObservable.subscribe(new Subscriber<Movie>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Movie movie) {
                Log.d("OnNext", movie.getName());
            }
        });
    }

    private void observableJust(){
        rx.Observable.just(1,2,3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("OnNext", String.valueOf(integer));
            }
        });
    }

    private void observableForm() {
        rx.Observable.from(new Integer[]{1,2,3}).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("OnNext", String.valueOf(integer));
            }
        });
    }
}
