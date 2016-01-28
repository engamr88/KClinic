/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AbdallahGaber
 */
public class Authorize implements Filter {

    

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //@@ln("start fILTER ");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;


        HttpSession httpsession = httpReq.getSession(false);
        //@@ln("Session :: " + httpsession);
       

        ////@@ln("Session auth  :: " + httpsession.getAttribute("auth"));


        if ( httpsession==null || httpsession.getAttribute("auth")==null || (Boolean)httpsession.getAttribute("auth") == false) {

            //@@ln("noooooooooo session --------------- ");


//            String appName= ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getContextPath();

            httpRes.sendRedirect(httpReq.getContextPath()+"/login/loginIndex.xhtml");
        }

        else {
            
            if (request.getCharacterEncoding() == null) {
    request.setCharacterEncoding("UTF-8");
}

            chain.doFilter(request, response);

        }




    }

    /**
     * Return the filter configuration object for this filter.
     */
    /**
     * Destroy method for this filter 
     */
    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
    /**
     * Init method for this filter 
     */
    /**
     * Return a String representation of this object.
     */
}
