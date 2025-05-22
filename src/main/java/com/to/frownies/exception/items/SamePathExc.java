package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class SamePathExc  extends CustomExc  {
    public SamePathExc() {
        super(50, "Source & destination are same");
    }
}
