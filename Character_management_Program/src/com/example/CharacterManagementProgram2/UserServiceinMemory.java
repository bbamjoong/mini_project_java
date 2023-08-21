package com.example.CharacterManagementProgram2;

import java.util.Iterator;
import java.util.List;

// 메모리상의 User정보를 관리한다.
public class UserServiceinMemory implements UserService{
    private List<User> users;

    public UserServiceinMemory(List<User> users) {
        this.users = users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public boolean updateUser(User user) {
        boolean deleteFlag = deleteUser(user.getCharacterName());
        if(deleteFlag = true){
            users.add(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteUser(String name) {
        int findIndex = findIndex(name);
        if(findIndex > -1){
            users.remove(findIndex);
            return true;
        }else{
            return false;
        }
    }

    /* 필드 users정보를 그대로 리턴할것이냐? -> 외부에서 users정보를 조작할 수 있다.
       users정보를 복사후 리턴할것이냐? -> 삭제한 정보가 출력될 수 있다.
       읽기전용으로 리턴하자. -> UserService에서 리턴 형태를 List가 아닌 Iterator를 이용하자.
     */
    @Override
    public Iterator<User> getUsers() {
        return users.iterator();
    }

    private int findIndex(String name) {
        int findIndex = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getCharacterName().equals(name)) {
                findIndex = i;
                break;
            }
        }
        return findIndex;
    }

    @Override
    public boolean exists(String name) {
        if(findIndex(name) >= 0)
            return true;
        else
            return false;
    }
}
/*

 */