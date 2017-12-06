package com.youdfu.basecorehelper.rxjava;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class RxHelper {
    public static <T> Observable.Transformer<T, T> io2ui() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> Observable<T> defer(T value) {
        return Observable.defer(() ->
                Observable.just(value));
    }
}
