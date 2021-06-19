package com.dopoil.gomokuserver.hibernate.custom;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;

@AllArgsConstructor
@Data
public class BoardObject implements Serializable {

    private Integer[][] data;

}
