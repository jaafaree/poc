package com.jaafar.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 9/27/2019 2:22 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager {

    private Long per;
    private Long cur;

    public Pager per(Long per) {
        this.per = per;
        return this;
    }

    public Pager cur(Long cur) {
        this.cur = cur;
        return this;
    }

}
