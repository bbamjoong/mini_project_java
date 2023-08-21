package com.example.CharacterManagementProgram2;

public class UserMain {
    public static void main(String[] args) {
        UserUI userUI = new UserUI();
        UserDao userDao = new UserDao("users.dat");
        UserService userService = new UserServiceinMemory(userDao.getUsers());


        while(true){
            int menuId = userUI.menu();
            if(menuId == 1){
                User user = userUI.regUser();
                userService.addUser(user);
                System.out.println("캐릭터가 생성되었습니다.");
            }
            else if(menuId == 2){
                userUI.printUserList(userService.getUsers());
            }
            else if(menuId == 3){
                String name = userUI.inputName();
                boolean isFindName = userService.exists(name);
                if(isFindName){
                    User updateUser = userUI.inputUser(name);
                    userService.updateUser(updateUser);
                    System.out.println("수정이 완료되었습니다.");
                    System.out.println();
                }else{
                    System.out.println("수정할 캐릭터 정보가 없습니다.");
                    System.out.println();
                }
            }
            else if(menuId == 4) {
                String name = userUI.inputName();
                boolean isFindName = userService.exists(name);
                if(isFindName){
                    userService.deleteUser(name);
                    System.out.println("캐릭터 삭제가 완료 되었습니다.");
                    System.out.println();
                }else{
                    System.out.println("삭제할 캐릭터 정보가 없습니다.");
                    System.out.println();
                }
            }
            else if(menuId == 5){
                System.out.println("프로그램을 종료합니다.");
                userDao.saveUsers(userService.getUsers());
                break;
            }
        }
    }
}