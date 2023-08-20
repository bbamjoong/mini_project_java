package com.example.CharacterManagementProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 파일에서 List<User>정보를 저장(SaveUsers) 또는 읽어오는 기능(LoadUsers)
 */
public class UserDao {
    private String filename;

    public UserDao(String filename) {
        this.filename = filename;
    }

    //메모리 상의 users 배열을 저장한다.
    public void saveUsers(List<User> users){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(users);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //파일로부터 User배열을 메모리로 가져온다.
    public List<User> getUsers(){
        File file = new File(filename);
        if(!file.exists()){ //파일이 존재하지 않을 경우 빈 배열 반환
            return new ArrayList<>();
        }
        else{
            List<User> list = null;
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
                list = (List<User>)in.readObject();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return list; //파일이 존재할 경우 User배열 반환
        }
    }
}
