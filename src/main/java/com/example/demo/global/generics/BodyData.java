package com.example.demo.global.generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyData   {

    List<String> data;
}
