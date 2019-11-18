package com.jaafar.poc.model;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 9/27/2019 11:24 AM
 */
public class TestListString {

    public TestListString(List<String> strs) {
        this.strs = strs;
    }

    private List<String> strs;

    public List<String> getStrs() {
        return strs;
    }

    public void setStrs(List<String> strs) {
        this.strs = strs;
    }
}
