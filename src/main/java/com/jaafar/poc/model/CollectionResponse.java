package com.jaafar.poc.model;

import java.util.Collection;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 9/27/2019 1:41 PM
 */
public class CollectionResponse extends BaseResponse<Collection> {

    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public CollectionResponse total(Long total) {
        this.total = total;
        return this;
    }
}
