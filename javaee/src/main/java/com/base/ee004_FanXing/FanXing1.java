package com.base.ee004_FanXing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FanXing1 {


    private int name = 1;
    private int type = 1;

    @Override
    public String toString() {
        return "FanXing1{}" + getName() + ":" + getType();
    }
}

