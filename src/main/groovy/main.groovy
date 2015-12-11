import rx.Observable
import rx.Subscriber
import rx.subjects.AsyncSubject
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject
import rx.subjects.ReplaySubject

import java.util.concurrent.TimeUnit

import static java.lang.String.format;

o1 = rx.Observable.just("Marek", "Maciej")

def Subscriber<String> subscriber(final String name)  {
    return new Subscriber<String>() {
        void onNext(String onNext) {
            println(name + ".onNext " + onNext)
        }

        void onError(Throwable onError) {
            println(name + ".onError " + onError)
        }

        void onCompleted() {
            println(name + ".onCompleted")
        }
    }
}

o2 = Observable.defer({ -> o1 })

o3 = Observable.create({ subscriber ->
    Observable.interval(1, TimeUnit.SECONDS).map({ i -> subscriber.onNext(String.valueOf(System.currentTimeMillis() / 1000)) }).subscribe()
})

//o2.zipWith(Observable.interval(1, TimeUnit.SECONDS), { s, i -> format("%d %s", i, s) }).toBlocking().subscribe(subscriber)

//o3.take(5).toBlocking().subscribe(subscriber)

//Observable<String> bigLetters =
//        Observable.just("Maciej", "Marek").zipWith(Observable.interval(1, TimeUnit.SECONDS), { b, i -> b });
//Observable<String> smallLetters =
//        Observable.just("Przybylski", "Defeciński").zipWith(Observable.interval(750, TimeUnit.MILLISECONDS), { b, i -> b });
//
//bigLetters
//        .mergeWith(smallLetters)
//        .toBlocking()
//        .subscribe(subscriber);

//Observable.repeat().scan({ l1, l2 -> l1 + l2 }).take(5).map({ l -> String.valueOf(l) }).toBlocking().subscribe(subscriber)

//psubject = AsyncSubject.<String>create()
//
//psubject.subscribe(subscriber("1"))
//
//psubject.onNext("one")
//psubject.onNext("two")
//
//psubject.subscribe(subscriber("2"))
//
//psubject.onNext("three")
//psubject.onNext("four")
//
//psubject.onCompleted();
//
//psubject.toBlocking().subscribe(subscriber("3"))



