package com.jaafar.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 9/27/2019 2:21 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequest {

    private String searchInput;

    private Pager pager;

    private List<String> ids;

    public OptionRequest searchInput(String searchInput) {
        this.searchInput = searchInput;
        return this;
    }

    public OptionRequest pager(Pager pager) {
        this.pager = pager;
        return this;
    }

    public OptionRequest ids(List<String> ids) {
        this.ids = ids;
        return this;
    }
}
