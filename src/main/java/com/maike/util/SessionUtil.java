package com.maike.util;

import java.util.ArrayList;

import com.maike.entity.AdminOnline;

public class SessionUtil {
	//工具类，通过session在链表中找到相应的用户对象 
	public static AdminOnline getUserBySessionID(ArrayList<AdminOnline> userlist, String sessionID) { 
		for (int i = 0; i < userlist.size(); i++) { 
			AdminOnline user=userlist.get(i); 
			if (user.getSessionID().equals(sessionID)){ 
				return user; 
				} 
			} 
		return null; 
		}
}
