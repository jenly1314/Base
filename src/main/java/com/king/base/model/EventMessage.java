package com.king.base.model;

/*Copyright 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
import java.io.Serializable;

/**
 * @author Jenly
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
