package com.augus.controller;

import java.util.List;

public class AddPrizeListReq {
        int userId;
        List<ViewPrize> viewPrizeList;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<ViewPrize> getViewPrizeList() {
            return viewPrizeList;
        }

        public void setViewPrizeList(List<ViewPrize> viewPrizeList) {
            this.viewPrizeList = viewPrizeList;
        }
    }