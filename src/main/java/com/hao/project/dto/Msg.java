package com.hao.project.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Msg implements Serializable {

    private static final long serialVersionUID = 1498408079547897189L;

    private String message;
}
