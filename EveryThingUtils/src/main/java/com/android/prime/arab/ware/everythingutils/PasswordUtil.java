package com.android.prime.arab.ware.everythingutils;

import java.util.Random;

public class PasswordUtil {
				
		public static boolean validate(String password) {
//			return password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");
			return validate2(password);
		}
		
		public static boolean symbol(String password) {
			return password.matches(".*[@#$%].*");
		}
		
		public static boolean uppercase(String password) {
			return password.matches(".*[A-Z].*");
		}
		
		public static boolean lowerCase(String password) {
			return password.matches(".*[a-z].*");
		}
		
		public static boolean digit(String password) {
			return password.matches(".*\\d.*");
		}
		
		public static boolean validate2(String pass) {
				boolean flag = true;
				
				if (pass.length() < 8) {
						//Password must have more than 8 chars
						flag = false;
				} else if (!digit(pass)) {
						//Password must contains digit
						flag = false;
				} else if (!symbol(pass)) {
						//Password must contains symbol
						flag = false;
				} else if (!uppercase(pass)) {
						//Password must contains Upper case
						flag = false;
				} else if (!lowerCase(pass)) {
						//Password must contains Lower case
						flag = false;
				}
				
				return flag;
		}
		
		public static String generatePassword(int len) {
				Random rnd = new Random();
				String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%&";
				StringBuilder sb = new StringBuilder(len);
				for (int i = 0; i < len; i++) {
					sb.append(AB.charAt(rnd.nextInt(AB.length())));
				}
				if(validate2(sb.toString())){
				    return sb.toString();
				}else{
				   return generatePassword(12);
				}
		}


		
}
