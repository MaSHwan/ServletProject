package com.travel.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.action.member.MemberCheckEmailAction;
import com.travel.action.member.MemberCheckIdAction;
import com.travel.action.member.MemberDeleteAction;
import com.travel.action.member.MemberJoinAction;
import com.travel.action.member.MemberJoinProcAction;
import com.travel.action.member.MemberLoginAction;
import com.travel.action.member.MemberLoginProcAction;
import com.travel.action.member.MemberLogoutAction;
import com.travel.action.member.ProfileAction;
import com.travel.action.member.ProfileImgUpdateAction;
import com.travel.action.member.ProfileUpdateAction;
import com.travel.common.Action;
import com.travel.common.ActionForward;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String RequestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = RequestURI.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;
        
        if(command.equals("/join.do")) {
        	action = new MemberJoinAction();
        	try {
        		forward = action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	} 
        } else if (command.equals("/joinProc.do")) { // end if join.do
        	action = new MemberJoinProcAction();
        	try {
        		forward = action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if (command.equals("/login.do")) {
        	action = new MemberLoginAction();
        	try {
        		forward = action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if (command.equals("/loginProc.do")) {
        	action = new MemberLoginProcAction();
        	try {
        		forward = action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if (command.equals("/logout.do")) {
            action = new MemberLogoutAction();
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if (command.equals("/checkId.do")) {
        	action = new MemberCheckIdAction();
        	try {
        		forward = action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if (command.equals("/checkEmail.do")) {
        	action = new MemberCheckEmailAction();
        	try {
        		forward = action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(command.equals("/memberDelete.do")){
            action = new MemberDeleteAction();
            try{
                forward = action.execute(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
        else if(command.equals("/profile.do")){
            action = new ProfileAction();
            try{
                forward = action.execute(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
        else if(command.equals("/profileUpdate.do")){
            action = new ProfileUpdateAction();
            try{
                forward = action.execute(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
        else if(command.equals("/profileImgUpdate.do")){
            action = new ProfileImgUpdateAction();
            try{
                forward = action.execute(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
        
        
        
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
