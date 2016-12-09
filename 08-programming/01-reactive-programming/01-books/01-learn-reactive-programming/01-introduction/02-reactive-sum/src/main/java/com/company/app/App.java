package com.company.app;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static final class ReactiveSum implements Observer<Double> {

        private CountDownLatch latch = new CountDownLatch(1);
        private double sum;
        private Subscription subscription = null;

        public ReactiveSum(Observable<Double> a, Observable<Double> b) {
            this.sum = 0;
        }

        private void subscribe(Observable<Double> a, Observable<Double> b) {
            this.subscription = Observable.combineLatest(a, b, new Func2<Double, Double, Double>() {
                @Override
                public Double call(Double a, Double b) {
                    return a + b;
                }
            }).subscribeOn(Schedulers.io()).subscribe(this);

        }

        public void unsubscribe() {
            this.subscription.unsubscribe();
            this.latch.countDown();
        }

        @Override
        public void onCompleted() {
            System.out.println("Exiting last sum was: " + this.sum);
            this.latch.countDown();
        }

        @Override
        public void onError(Throwable e) {
            System.err.println("Got an error!");
            e.printStackTrace();
        }

        @Override
        public void onNext(Double aDouble) {
            this.sum = sum;
            System.out.println("update : a + b = " + sum);
        }

        public CountDownLatch getLatch() {
            return latch;
        }
    }

    public static Observable<Double> varStream(final String varName, Observable<String> input) {
        final Pattern pattern = Pattern.compile("^\\s*" + varName
                + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)$");

        return input.map(new Func1<String, Matcher>() {
            public Matcher call(String str) {
                return pattern.matcher(str);
            }
        }).filter(new Func1<Matcher, Boolean>() {
            public Boolean call(Matcher matcher) {
                return matcher.matches() && matcher.group(1) != null;
            }
        }).map(new Func1<Matcher, String>() {
            public String call(Matcher matcher) {
                return matcher.group(1);
            }
        }).filter(new Func1<String, Boolean>() {
            public Boolean call(String str) {
                return str != null;
            }
        }).map(new Func1<String, Double>() {
            public Double call(String str) {
                return Double.parseDouble(str);
            }
        });
    }

    public static ConnectableObservable<String> from(final InputStream stream){
        return null;
    }

    public static ConnectableObservable<String> from(final BufferedReader reader){
        return Observable.create((Subscriber<? super String> subscriber) -> {

        }).publish();
    }

    public static void main(String... args) {
        System.out.println("Reactive Sum. Type 'a: <number>' and 'b: <number>' to try ti");

        ConnectableObservable<String> input = CreateObservable.from(System.in);
    }


}
