package com.informatica.zapi.model;

public enum Status{
    PASS(1),
    FAIL(2),
    WIP(3),
    BLOCKED(4),
    SOME(5),
    UNEXECUTED(-1),
            ;

    public final int id;

    public int getId()
    {
        return this.id;
    }
    private Status(int id) {
        this.id = id;
    }
}