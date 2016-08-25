package com.king.base.model;

import java.io.Serializable;

/**
 * @author Jenly
 * @date 2016-4-28
 */
public class EventMessage implements Serializable{

    public int what;

    public int num;

    public String str;

    public Object obj;


    public EventMessage(int what) {
        super();
        this.what = what;
    }


    public EventMessage(int what, Integer num) {
        super();
        this.what = what;
        this.num = num;
    }

    public EventMessage(int what,String str) {
        super();
        this.what = what;
        this.str = str;
    }

    public EventMessage(int what, Object obj) {
        super();
        this.what = what;
        this.obj = obj;
    }

    public EventMessage(int what, Integer num, String str) {
        super();
        this.what = what;
        this.num = num;
        this.str = str;
    }
    public EventMessage(int what, Integer num, Object obj) {
        super();
        this.what = what;
        this.num = num;
        this.obj = obj;
    }
    public EventMessage(int what, Integer num, String str, Object obj) {
        super();
        this.what = what;
        this.num = num;
        this.str = str;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "what=" + what +
                ", num=" + num +
                ", str='" + str + '\'' +
                ", obj=" + obj +
                '}';
    }
}
