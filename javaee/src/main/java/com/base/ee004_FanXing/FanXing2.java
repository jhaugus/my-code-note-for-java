package com.base.ee004_FanXing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FanXing2 extends FanXing1{

    public FanXing2() {
        this.setName(2);
        this.setType(2);
    }

    public FanXing2(int name, int type) {
        super(name, type);
        this.setName(name);
        this.setName(type);
    }

    @Override
    public String toString() {
        return "FanXing2{}" + getName() + ":" + getType();
    }
}
