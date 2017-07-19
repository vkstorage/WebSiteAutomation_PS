package com.phelps.ps.com.autotest.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Reporter;
public class FileOperations
{
	public static void checkFileExists(String path)
	{
		 boolean flag=false;
		 Path filePath = Paths.get(path);
		 if(Files.exists(filePath))
			 flag=true;
		 if(flag==false)
			{
				Reporter.log("File does not exist");
			throw new AssertionError();
		}
		 				
	}
}