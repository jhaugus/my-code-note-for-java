package com.base.ee004_FanXing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FanXing3 extends FanXing2{

    public FanXing3() {
        this.setName(2);
        this.setType(2);
    }

    @Override
    public String toString() {
        return "FanXing3{}" + getName() + ":" + getType();
    }

}