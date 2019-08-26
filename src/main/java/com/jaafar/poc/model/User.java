package com.jaafar.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 7/11/2019 9:19 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel{
    private String name;
    private Long id;
}
