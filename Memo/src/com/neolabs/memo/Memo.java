package com.neolabs.memo;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class Memo {
    
    public static final String MEMO_DIR = "/temp/memo/";
    
    public static final String EXIT = "/exit";
    
    public void showCommand() {
        System.out.println("수행할 명령을 입력하세요");
        System.out.println("1. 쓰기 2. 읽기. 3. 수정. 4. 삭제 0. 프로그램 종료");
    }
    
	public void write(Scanner scanner) {
		// TODO Auto-generated method stub
		 System.out.println("쓰기모드");
	        StringBuilder content = new StringBuilder();
	        
	        while(true)
	        {
	            String line = scanner.nextLine();
	            if(line.equals(EXIT)) {
	                break;
	            } else {
	                content.append(line + "\r\n");
	            }      
	        }
	        
	        
            if(!content.toString().equals(""))
            {
                long now = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
                String filename = sdf.format(now) + ".txt";
                Path path = Paths.get(MEMO_DIR, filename);
                try {
                    Files.write(path, content.toString().getBytes());
                } catch(IOException e) {
                    e.printStackTrace();
                }
                System.out.println("메모를 등록 하였습니다");
            }
	}
	
	public void list(Scanner scanner) {
		File file = new File(MEMO_DIR);
		String fileList[] = file.list();
		for(String filename : fileList) {
			System.out.println(filename);
		}
        
        System.out.println("읽을 파일을 고르시오. 예 : 첫번째 파일은 1, 두번째 파일은 2, 세번째 파일은 3....");
        
        String number = scanner.nextLine();
        
        File editFile = new File(MEMO_DIR + fileList[Integer.parseInt(number) - 1]);
	    //파일이 존재하지 않으면 알려준다.
	    if(!editFile.exists())
             System.out.println("파일이 존재하지 않습니다.");
        
        String filetext = readFileText(editFile);
	    System.out.println(filetext);
	}
	
    public void edit(Scanner scanner) {
		File file = new File(MEMO_DIR);
		String fileList[] = file.list();
		for(String filename : fileList) {
			System.out.println(filename);
		}
	        
	    System.out.println("수정할 파일을 고르시오. 예 : 첫번째 파일은 1, 두번째 파일은 2, 세번째 파일은 3....");
	    
	    String number = scanner.nextLine();
	    
	    File editFile = new File(MEMO_DIR + fileList[Integer.parseInt(number) - 1]);
	    //파일이 존재하지 않으면 알려준다.
	    if(!editFile.exists())
             System.out.println("파일이 존재하지 않습니다.");
	    
	    //파일을 읽어온다.
	    String filetext = readFileText(editFile);
	    System.out.println(filetext);
	   
	    //원래는 완전한 수정을 구현하고 싶지만, CUI환경에서 안드로이드의 editText와 같은 것을 구현하는 방법은 잘 모르겠습니다....
	    System.out.println("수정(추가)할 내용을 적어주세요.");
	    
	    StringBuilder content = new StringBuilder();
        
        while(true)
        {
            String line = scanner.nextLine();
            if(line.equals(EXIT)) {
                break;
            } else {
                content.append(line + "\r\n");
            }      
        }
        
        
        if(!content.toString().equals(""))
        {
            //long now = System.currentTimeMillis();
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
            String filename = fileList[Integer.parseInt(number) -1];
            String finalsave = filetext + content.toString();
            Path path = Paths.get(MEMO_DIR, filename);
            try {
            	editFile.delete();
                Files.write(path, finalsave.getBytes());
            } catch(IOException e) {
                e.printStackTrace();
            }
            System.out.println("메모를 등록 하였습니다");
        }
	}
	
    private String readFileText(File editFile) {
		// TODO Auto-generated method stub
    	String text = "";
    	int Buffer;
    	try { 
    		 BufferedReader buffRead = new BufferedReader(new FileReader(editFile));
    		 while((Buffer = buffRead.read()) != -1)
    		 {
    			 text += (char)Buffer;
    		 }
    		 buffRead.close();
    	}
    	catch(Exception ex) 
    	{
    		System.out.println(ex.getMessage());
    	}
		return text;
	}


	public void delete(Scanner scanner) {
		File file = new File(MEMO_DIR);
		String fileList[] = file.list();
		for(String filename : fileList) {
			System.out.println(filename);
		}
        
        System.out.println("삭제할 파일을 고르시오. 예 : 첫번째 파일은 1, 두번째 파일은 2, 세번째 파일은 3....");
        String number = scanner.nextLine();
        
        File deleteFile = new File(MEMO_DIR + fileList[Integer.parseInt(number) - 1] );
        if( deleteFile.exists() ){
            if(deleteFile.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }    
	}
}

