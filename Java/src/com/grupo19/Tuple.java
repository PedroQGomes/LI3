package com.grupo19;

public class Tuple<A,B> {
    private final A a;
    private final B b;

    public Tuple(A mA,B mB) {
        this.a = mA;
        this.b = mB;
    }

    public A getFirstElem() {
        return this.a;
    }
    public B getSecondElem() {
        return this.b;
    }
}
