package com.fruitbasket.pcmmanager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	private static final Main main=new Main();
	
	//error codes
	private static final int ERROR=-1;
	private static final int FILE_NOT_EXIST=-2;
	private static final int FILE_TYPE_ERROR=-3;
	
	private Main(){}
	
	public Main getInstance(){
		return main;
	}
	
	public static void main(String args[]){
		System.out.println("PCM processer version 1.0 Developed by XueJin.");
		System.out.println();
		
		File pcmFile =new File(Condition.getPcmFileName());
		File excelFile=new File(Condition.getExcelFileName());
		String command;
		String[] parameters;
		Scanner input=new Scanner(System.in);
		
		while(true){
			System.out.print("PCM Processer>");
			command=input.nextLine();
			command.trim();
			parameters=command.split(" ");
			
			switch(parameters[0]){
			case "":
				break;
				
			case "pcm":
			case "PCM":
				if(parameters.length!=2){
					showError(ERROR);
				}
				else{
					pcmFile=new File(parameters[1]);
					if(pcmFile.exists()==false){
						showError(FILE_NOT_EXIST);
					}
					else if(pcmFile.isFile()==false||pcmFile.getName().endsWith(".pcm")==false){
						showError(FILE_TYPE_ERROR);
					}
					else{
						Condition.setPcmFileName(parameters[1]);
					}
				}
				break;
				
			case "excel":
			case "EXCEL":
				if(parameters.length!=2){
					showError(ERROR);
				}
				else{
					excelFile=new File(parameters[1]);
					Condition.setExcelFileName(parameters[1]);
				}
				break;
				
			case "do":
				if(pcmFile!=null&&pcmFile.exists()==true){
					try {
						PCMHelper.shortPCMToExcel(pcmFile, excelFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					showError(ERROR);
				}
				break;
				
			case "help":
				showHelp();
				break;
			case "exit":
				input.close();
				System.exit(0);
				break;
				
			default:
					showError(ERROR);
			}
		}
	}
	
	private static void showError(int errorCode){
		switch(errorCode){
		case ERROR:
			System.out.println("error : command error, please input again");
			break;
		case FILE_NOT_EXIST:
			System.out.println("error : the file do not exist , please input again");
			break;
		case FILE_TYPE_ERROR:
			System.out.println("error : please choose a correct file");
		}
	}
	
	private static void showHelp(){
		System.out.println("\nFor example:\n"+
	"1. uses 'pcm pcmFileName' to specifies the pcm file;\n"+
				"2. uses 'do' to transfer the pcm file to a excel file;\n"+
	"3. then you will find new file 'excel.xls'\n");
		
		System.out.println("\nthe commands and the usage :\n"+
	"1. pcm pcmFileName : pcmFile is the name of the name of the pcm file\n"+
				"2. excel excelFileName : excelFile is the name of the name of the excel file\n"+
	"3. do : to do the transfer\n"+
				"4. help: to ask for help\n"+
	"5. exit :to exit this program\n");
	}
}
