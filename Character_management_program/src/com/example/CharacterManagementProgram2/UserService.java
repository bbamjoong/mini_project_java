package com.example.CharacterManagementProgram2;

import java.util.Iterator;

public interface UserService {
    //캐릭터 정보 등록
    public void addUser(User user);
    //캐릭터 정보 수정 -> users.getEmail()로부터 리턴받은 이름의 캐릭터 수정
    public boolean updateUser(User user);
    //캐릭터 정보 삭제
    public boolean deleteUser(String name);
    //캐릭터 정보 리턴
    public Iterator<User> getUsers();
    //캐릭터이름에 해당하는 캐릭터가 존재할 경우 true 리턴
    public boolean exists(String name);
}
