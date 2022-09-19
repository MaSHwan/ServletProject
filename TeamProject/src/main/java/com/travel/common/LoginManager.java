package com.travel.common;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.travel.action.member.MemberLogoutAction;

public class LoginManager implements HttpSessionBindingListener {

	private static Hashtable loginUsers = new Hashtable();

    //������ �� �־�� ��.
    public LoginManager() {
        super();
    }
    public static LoginManager getInstance(){
        return LoginManager.LazyHolder.INSTANCE;
    }

    //�̱������� �ν��Ͻ� ����
    private static class LazyHolder{
        private static final LoginManager INSTANCE = new LoginManager();
    }
    
    //���� ��ü�� ���ǿ� ���ε� �ɶ� ȣ���
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		 loginUsers.put(event.getSession(),event.getName());

	}

	//���� ��ü�� ���ǿ��� ����ε�(����)�ɶ� ȣ��
    //���� �α׾ƿ� �ȴ����� ��ǻ������,���Ǹ��� ��� �α׾ƿ��Ǳ� ������ ���� ����
	public void valueUnbound(HttpSessionBindingEvent event) {
		Action action = new MemberLogoutAction();
        ((MemberLogoutAction)action).logoutProc(getMemberId(event.getSession()));
        loginUsers.remove(event.getSession()); //������ħ
	}
	
	//�α׾ƿ� ��ư ������ ��, �ؽ����̺� ���������� ��ġ��Ŵ,while Ű ���� �����ϴµ���,ession�� Ű �� �޾ƿ�
    public void removeSession(String id){
        Enumeration e = loginUsers.keys();
        HttpSession session = null;
        while(e.hasMoreElements()){
            session=(HttpSession)e.nextElement();

            if(loginUsers.get(session).equals(id)){
                session.invalidate();
            }
        }
    }

    public void setSession(HttpSession session,String id){
        session.setAttribute(id,this);
    }
    public String getMemberId(HttpSession session){
        return (String)loginUsers.get(session);
    }

}
