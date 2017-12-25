package com.example.books.utils;

/**
 * Created by VINH_IT on 12/22/2017.
 */
public class EnumApp {
	
	public enum EnumRole {
		ROLE_ADMIN("ROLE_ADMIN", 1), ROLE_USER("ROLE_USER", 2);
		
		private Integer value;
		
		private String name;
		
		EnumRole(String name, Integer value) {
			this.value = value;
			this.name = name;
		}
		
		public Integer getValue() {
			return this.value;
		}
		
		public String getName() {
			return this.name;
		}
	}
}
