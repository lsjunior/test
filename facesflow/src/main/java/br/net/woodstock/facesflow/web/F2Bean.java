package br.net.woodstock.facesflow.web;

import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named(value = "f2Bean")
@FlowScoped(value = "flow2")
public class F2Bean implements Serializable {

    private int count;

    public F2Bean() {
    }

    public String next() {
        this.count++;
        if (this.count % 2 == 0) {
            return "step-1";
        }
        return "step-2";
    }

    public String end() {
        this.count = 0;
        return "success";
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
