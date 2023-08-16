package com.maike.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
@WebFilter(filterName="CheckLoginFilter",/*value= {"/AdminUserLogin"},*/asyncSupported=true/*urlPatterns={""}*/,dispatcherTypes= {DispatcherType.ASYNC,DispatcherType.REQUEST})
public class CheckLoginFilter implements Filter {
	private FilterConfig config;
	
    /**
     * Default constructor. 
     */
    public CheckLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		// 过滤器销毁，一般是释放资源
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		//chain.doFilter(request, response);
		PrintWriter out=res.getWriter();
		/*if (!(req instanceof HttpServletRequest) || !(res instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}*/
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String noLoginPaths = config.getInitParameter("noLoginPaths");
		
		String charset = config.getInitParameter("charset");
		if(charset==null){
			charset = "UTF-8";
		}
		request.setCharacterEncoding(charset);
        //response.setHeader("Cache-Control","no-cache"); 
        //response.setHeader("Pragma","no-cache"); 
        //response.setDateHeader ("Expires", -1); 
        //response.setHeader("P3P","CP=CAO PSA OUR");
        //request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8"); //--解决响应乱码
        /*String[] notFilter = new String[] { "userLogin","js","xml","css","demo","img","images","fonts","common","gateway","payCallback","toOrderPage","show_order"};//过滤字段、路径。。。。。。
        String urlPath = request.getServletPath();
        Boolean flg = false;
        for (String url : notFilter) {
            if ((urlPath.contains(url))) {
                flg = true;
            }
        }
        if(flg){
            chain.doFilter(req, res);
        }else{
            HttpSession session = request.getSession();
            String UID = (String) session.getAttribute("UID"); //登录成功将登录ID放入session中，这里将session取出对比
            if (null == UID||"".equals(UID)) {
                //logger.warn("用户登录超时或未登录，请重新登录！");
                java.io.PrintWriter out = response.getWriter();  
                out.println("<html>");  
                out.println("<script>");  
                out.println("window.open ('"+request.getContextPath()+"/mslogin.html','_top')");  
                out.println("</script>");  
                out.println("</html>");  
                return;
                
            }else {
                chain.doFilter(req, res);
            }
        }*/
        HttpSession session = request.getSession();
		String uri = request.getRequestURI();
        // 登陆页面无需过滤
        /*if(uri.indexOf("/mslogin.html") > -1||uri.indexOf("/stulogin.html") > -1) {
            chain.doFilter(request, response);
            return;
        }*/
		// 除了登录页面以外的页面和登录action以外，检查登录情况，未登录的需要重定向并且不通过过滤
		//if (uri.indexOf("mslogin.html") == -1 /*&& uri.indexOf("loginProcess.action") == -1*/) {
        //判断session是否过期
        if (session.getAttribute("username") == null) {
          //String errors = "您还没有登录，或者session已过期。请先登陆!";
          out.print("<script>alert('登录失效，请重新登陆！');window.location='mslogin.html';</script>");
          //request.setAttribute("Message", errors);
          //跳转至登录页面
          //request.getRequestDispatcher("/mslogin.html").forward(request, response);
        //} 
        }else {
          chain.doFilter(request, response);
        }
	}

	/**
	* 判断是否为Ajax请求
	* 
	* @param request
	*            HttpServletRequest
	* @return 是true, 否false
	*/
	public static boolean isAjaxRequest(HttpServletRequest request) {
	return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		// 初始化操作，读取web.xml中过滤器配置的初始化参数，满足你提的要求不用此方法
		config = fConfig;
	}

}
