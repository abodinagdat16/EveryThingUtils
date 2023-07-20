package com.android.prime.arab.ware.everythingutils;

import java.util.Random;
import java.util.Scanner;

public class PasswordValidator {
				
		public static boolean validate(String password) {
				if (password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})"))
				return true;
				
				return false;
		}
		
		public static boolean symbol(String password) {
				if (password.matches(".*[@#$%].*"))
				return true;
				
				return false;
		}
		
		public static boolean uppcase(String password) {
				if (password.matches(".*[A-Z].*"))
				return true;
				
				return false;
		}
		
		public static boolean lowerCase(String password) {
				if (password.matches(".*[a-z].*"))
				return true;
				
				return false;
		}
		
		public static boolean digit(String password) {
				if (password.matches(".*\\d.*"))
				return true;
				
				return false;
		}
		
		public static boolean validate2(String pass) {
				boolean flag = true;
				
				if (pass.length() < 8) {
						System.out.println("Password must more than 8");
						flag = false;
				} else if (!digit(pass)) {
						System.out.println("Password must contains digit");
						flag = false;
				} else if (!symbol(pass)) {
						System.out.println("Password must contains symbol");
						flag = false;
				} else if (!uppcase(pass)) {
						System.out.println("Password must contains Upper case");
						flag = false;
				} else if (!lowerCase(pass)) {
						System.out.println("Password must contains Lower case");
						flag = false;
				}
				
				return flag;
		}
		
		public static String generatePassword(int len) {
				Random rnd = new Random();
				String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%&";
				StringBuilder sb = new StringBuilder(len);
				for (int i = 0; i < len; i++)
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
				
				if(validate2(sb.toString())){
				    return sb.toString();
				}else{
				   return generatePassword(12);
				}
				
				//return sb.toString();
		}
		
}
