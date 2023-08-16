package com.maike.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.maike.entity.AdminOnline;
import com.maike.util.SessionUtil;

/**
 * Application Lifecycle Listener implementation class AdminRequestListener
 *
 */
@WebListener
public class AdminRequestListener implements ServletRequestListener, ServletRequestAttributeListener, ServletContextListener, ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public AdminRequestListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    @SuppressWarnings("unchecked")
	public void requestInitialized(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    	ArrayList<AdminOnline> userlist=null; //获取全局变量中的链表，若不存在，则创建一个新的链表 
    	userlist= (ArrayList<AdminOnline>) sre.getServletContext().getAttribute("userlist"); 
    	if (userlist == null) { userlist = new ArrayList<AdminOnline>(); 
    	} //获取request对象
    	HttpServletRequest request = (HttpServletRequest) sre.getServletRequest(); //获取sessionID 
    	String sessionID = request.getSession().getId(); //通过sessionID在链表中查找对象，若没有此对象，则创建加入到链表中 
    	if (SessionUtil.getUserBySessionID(userlist, sessionID) == null) { 
    		AdminOnline user = new AdminOnline(); 
    		user.setSessionID(sessionID); 
    		user.setFirstTime(new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").format(new Date())); 
    		user.setIp(request.getRemoteAddr()); userlist.add(user); 
    		} //将链表设置为全局变量 
    	sre.getServletContext().setAttribute("userlist", userlist);
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
