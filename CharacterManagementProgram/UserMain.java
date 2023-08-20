package com.example.CharacterManagementProgram;

import org.w3c.dom.UserDataHandler;

import java.util.List;

public class UserMain {
    public static void main(String[] args) {
        UserUI userUI = new UserUI();
        UserDao userDao = new UserDao("../UserManagementProgram1/users.dat");
        List<User> users = userDao.getUsers();

        while(true){
            int menuId = userUI.menu();
            if(menuId == 1){
                User user = userUI.regUser();
                users.add(user);
                System.out.println("캐릭터가 생성되었습니다.");
            }
            else if(menuId == 2){
                userUI.printUserList(users);
            }
            else if(menuId == 3){
                String name = userUI.inputName();
                int findIndex = -1;
                for(int i = 0 ; i < users.size(); i++){
                    User u = users.get(i);
                    if(u.getCharacterName().equals(name)){
                        findIndex = i;
                        break;
                    }
                }
                if(findIndex != -1){
                    User updateUser = userUI.inputUser(name);
                    users.remove(findIndex);
                    users.add(updateUser);
                    System.out.println("수정이 완료되었습니다.");
                    System.out.println();
                }else{
                    System.out.println("수정할 캐릭터 정보가 없습니다.");
                    System.out.println();
                }
            }
            else if(menuId == 4) {
                String name = userUI.inputName();
                int findIndex = -1;
                for(int i = 0 ; i < users.size(); i++){
                    User u = users.get(i);
                    if(u.getCharacterName().equals(name)){
                        findIndex = i;
                        break;
                    }
                }
                if(findIndex != -1){
                    users.remove(findIndex);
                    System.out.println("캐릭터 삭제가 완료 되었습니다.");
                    System.out.println();
                }else{
                    System.out.println("삭제할 캐릭터 정보가 없습니다.");
                    System.out.println();
                }
            }
            else if(menuId == 5){
                System.out.println("프로그램을 종료합니다.");
                userDao.saveUsers(users);
                break;
            }
        }
    }
}
