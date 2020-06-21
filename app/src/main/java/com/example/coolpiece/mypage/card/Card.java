package com.example.coolpiece.mypage.card;

import java.util.ArrayList;

public class Card {
    ArrayList<String> card;
    int cardnum;

    public Card() {
        this.cardnum=0;
    }

    public int getcard(){
        return this.cardnum;
    }//자격증 개수 return
    public ArrayList<String> getwholecard(){
        return this.card;
    }//자격증 리스트 return
    public void addcard(String newone){
        this.cardnum++;
        this.card.add(newone);
    }//새로운 자격증 추가
}
