package com.example.CharacterManagementProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserUI {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //프로그램 시작시 메뉴를 출력하고 입력받은 menuID를 반환한다.
    public int menu(){
        System.out.println("1. 캐릭터 생성");
        System.out.println("2. 캐릭터 조회");
        System.out.println("3. 캐릭터 닉네임 변경");
        System.out.println("4. 캐릭터 삭제");
        System.out.println("5. 프로그램 종료");
        System.out.println();

        int menuID = 0;
        try {
            String line = br.readLine();
            menuID = Integer.parseInt(line);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return menuID;
    }

    //캐릭터 이름 입력시 캐릭터 이름을 반환한다.
    public String inputName(){
        System.out.println("캐릭터의 닉네임을 입력하세요.");
        String name = "";
        try {
            name =  br.readLine();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return name;
    }

    // inputName을 통해 반환받은 캐릭터 닉네임을 입력값으로 이용. 캐릭터 정보를 수정 후 User배열을 반환한다.
    public User inputUser(String name){
        try{
            System.out.println(name + " 캐릭터의 정보를 수정합니다.");
            System.out.println("어떤 직업으로 바꾸시겠습니까?");
            String characterClass = br.readLine();
            System.out.println("어떤 헤어로 바꾸시겠습니까?");
            String hair = br.readLine();
            System.out.println("어떤 얼굴로 바꾸시겠습니까?");
            String face = br.readLine();
            System.out.println("하루 플레이타임을 입력하세요.");
            String strPlayTime = br.readLine();
            int playTime = Integer.parseInt(strPlayTime);

            User user = new User(name,characterClass,hair,face,playTime);
            return user;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    //캐릭터 생성 시 정보를 입력받고 User배열을 반환한다.
    public User regUser() {
        try{
            System.out.println("생성할 캐릭터 닉네임을 입력하세요.");
            String name = br.readLine();
            System.out.println("어떤 직업을 선택하시겠습니까?");
            String characterClass = br.readLine();
            System.out.println("어떤 헤어를 선택하시겠습니까?");
            String hair = br.readLine();
            System.out.println("어떤 얼굴을 선택하시겠습니까?");
            String face = br.readLine();
            System.out.println("하루 플레이타임을 입력하세요.");
            String strPlayTime = br.readLine();
            int playTime = Integer.parseInt(strPlayTime);

            User user = new User(name,characterClass,hair,face,playTime);
            return user;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    //users배열을 입력값으로 이용.이후 users배열의 user정보를 출력한다.
    public void printUserList(List<User> users){
        System.out.printf("%-25s %-15s %-15s %-15s %s \n", "Character Name", "Class", "Hair", "Face", "PlayTime");
        System.out.println("==================================================================================");
        for(int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.printf("%-25s %-15s %-15s %-15s %d \n", user.getCharacterName(),
                    user.getGameClass(), user.getCharacterHair(), user.getCharacterFace(), user.getPlayTime());
        }
    }
}
