package com.maike.listener;

import java.util.HashSet;

import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineCountListener
 *
 */
@WebListener
public class OnlineCountListener implements HttpSessionActivationListener, HttpSessionListener, HttpSessionAttributeListener, HttpSessionBindingListener {
	HttpServletRequest request;
    /**
     * Default constructor. 
     */
    public OnlineCountListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent event)  { 
         // TODO Auto-generated method stub
    	//在线人数+1
    	//CountUtils.add();
    	HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
		/*String ip = request.getRemoteAddr();//获取客户端ip
		session.setAttribute("ip", ip);*/
        // 在application范围由一个HashSet集保存所有的session
        HashSet<HttpSession> sessions = (HashSet<HttpSession>) application.getAttribute("sessions");
        if (sessions == null) {
               sessions = new HashSet<HttpSession>();
               application.setAttribute("sessions", sessions);
        }
        // 新创建的session均添加到HashSet集中
        sessions.add(session);
        // 可以在别处从application范围中取出sessions集合
        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event)  { 
         // TODO Auto-generated method stub
    	//在线人数-1
    	//CountUtils.subtract();
    	HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        @SuppressWarnings("unchecked")
		HashSet<HttpSession> sessions = (HashSet<HttpSession>) application.getAttribute("sessions");
        
        // 销毁的session均从HashSet集中移除
        sessions.remove(session);
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
