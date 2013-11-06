package br.net.woodstock.facesflow.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "testBean")
@SessionScoped
public class TestBean implements Serializable {

    public TestBean() {
    }

}
