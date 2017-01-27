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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            subscribe(a, b);
        }

        private void subscribe(Observable<Double> a, Observable<Double> b) {
            // combineLatest creates an Observable, sending notifications on changes of either of its sources.
            // This notifications are formed using a Func2.
            this.subscription = Observable.combineLatest(a, b, new Func2<Double, Double, Double>() {
                public Double call(Double a, Double b) {
                    return a + b;
                }
            }).subscribeOn(Schedulers.io()).subscribe(this);
        }

        public void unsubscribe() {
            this.subscription.unsubscribe();
            this.latch.countDown();
        }

        public void onCompleted() {
            System.out.println("Exiting last sum was : " + this.sum);
            this.latch.countDown();
        }

        public void onError(Throwable e) {
            System.err.println("Got an error!");
            e.printStackTrace();
        }

        public void onNext(Double sum) {
            this.sum = sum;
            System.out.println("update : a + b = " + sum);
        }

        public CountDownLatch getLatch() {
            return latch;
        }
    }

    /*
    The Observable returned by this method, only reacts to values in the form
    <varName> = <value> or <varName> : <value>.
    It emits the <value>.
     */

    public static Observable<Double> varStream(final String varName,
                                               Observable<String> input) {
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

    public static ConnectableObservable<String> from(final InputStream stream) {
        return from(new BufferedReader(new InputStreamReader(stream)));
    }

    public static ConnectableObservable<String> from(final BufferedReader reader) {
        return Observable.create((Subscriber<? super String> subscriber) -> {
            try {
                String line;

                if (subscriber.isUnsubscribed()) {
                    return;
                }

                while (!subscriber.isUnsubscribed() && (line = reader.readLine()) != null) {
                    if (line.equals("exit")) {
                        break;
                    }

                    subscriber.onNext(line);
                }
            } catch (IOException e) {
                subscriber.onError(e);
            }

            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        }).publish();
    }

    public static void main(String... args) {

        System.out.println("Reactive Sum. Type 'a: <number>' and 'b: <number>' to try it");

        ConnectableObservable<String> input = from(System.in);

        Observable<Double> a = varStream("a", input);
        Observable<Double> b = varStream("b", input);

        ReactiveSum sum = new ReactiveSum(a, b);

        input.connect();

        try {
            sum.getLatch().await();
        } catch (InterruptedException e) {}
    }
}
/*
output:
Reactive Sum. Type 'a: <number>' and 'b: <number>' to try it
a: 2
b: 3
update : a + b = 5.0
 */

