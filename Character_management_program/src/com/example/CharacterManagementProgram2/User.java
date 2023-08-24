package com.example.CharacterManagementProgram2;

import java.io.Serializable;

//User class는 불변객체로 지정
public class User implements Serializable { //직렬화를 위한 User class 생성
    private String characterName;
    private String gameClass;
    private String characterHair;
    private String characterFace;
    private int playTime;


    public User(String characterName, String gameClass, String characterHair, String characterFace, int playTime) {
        this.characterName = characterName;
        this.gameClass = gameClass;
        this.characterHair = characterHair;
        this.characterFace = characterFace;
        this.playTime = playTime;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getGameClass() {
        return gameClass;
    }

    public String getCharacterHair() {
        return characterHair;
    }

    public String getCharacterFace() {
        return characterFace;
    }

    public int getPlayTime() {
        return playTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "캐릭터 이름='" + characterName + '\'' +
                ", 직업='" + gameClass + '\'' +
                ", 헤어='" + characterHair + '\'' +
                ", 성형='" + characterFace + '\'' +
                ", 플레이어 나이=" + playTime +
                '}';
    }
}
