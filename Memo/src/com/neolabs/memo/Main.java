package com.neolabs.memo;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //System.out.println("Hello goorm!");
        //Memo객체 가져오기
        Memo memo = new Memo();
        memo.showCommand();
        
        //할 작업 받아오기
        Scanner scanner = new Scanner(System.in);
        
        boolean runFlag = true;
        
        //작업을 받아와 매모 객체에 실행
        while(runFlag)
        {
            String cmd = scanner.nextLine();
            switch(cmd)
            {
                case "1" :
                        memo.write(scanner);
                        break;
                case "2" :
                        memo.list(scanner);
                        break;
                case "3" :
                        memo.edit(scanner);
                        break;
                case "4" :
                        memo.delete(scanner);
                        break;
                case "0" :
                        runFlag = false;
                        break;
                default :
                        System.out.println("명령어가 잘못 되었습니다");
            }
        }
        
        //프로그램 종료
        System.out.println("프로그램이 종료되었습니다");
    }

}

