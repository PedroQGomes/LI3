package com.grupo19;

import com.grupo19.Interfaces.ITuple;

import java.io.Serializable;

public class Tuple<A,B> implements Serializable, ITuple {
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
