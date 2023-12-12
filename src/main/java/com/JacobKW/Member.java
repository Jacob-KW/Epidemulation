package com.JacobKW;

public class Member {
    int state = 0;

    // A member only needs to know whether it is alive dead or infected
    // and only needs 3 methods to update its state

    public void save() {
        state = 0;
    }

    public void infect() {
        state = 1;
    }

    public void kill() {
        state = 2;
    }

}
