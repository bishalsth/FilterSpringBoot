package com.example.demo.global.generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericExcelPojo {


    List<String>heading;
    List<BodyData>bodyData;
}


