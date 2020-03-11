package com.everyfarm.signup.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.everyfarm.member.biz.MemberBiz;
import com.everyfarm.member.biz.MemberBizImpl;
import com.everyfarm.member.dto.MemberDto;


@WebServlet("/signup.do")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;    
   
    public SignupController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doPost(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command= request.getParameter("command");
		HttpSession session = request.getSession();
		MemberBiz biz = new MemberBizImpl();
		
		if(command.equals("emailChk")) {   //이메일 인증 버튼 클릭 시
	        //이메일+이름으로 이메일 전송할 객체
	        String mem_email =    request.getParameter("mem_email");
	        
	        //세션저장으로 새창 열려도 값유지
	        session.setAttribute("mem_email", mem_email);
	        
	        MemberDto dto = biz.emailJunbokCh(mem_email);
			boolean emailnotused = true;
			// 아이디가 이미 있으면

			 try {
		            String mail_from =  "<" + mem_email + ">"; //보낸사람 
		           // String mail_to =    "admin<admin@83rpm.com>";
		            String mail_to =    mem_email;     //메일 받는 사람
		            String title =      "EveryFarm 인증번호 확인 메일 입니다. "; //제목
		            String contents =   "보낸 사람 : EveryFarm ";
		            String authNum=getRamdomPassword(7);
		            
		            session.setAttribute("pre_authNum", authNum);
		           
		            
		            System.out.println("222");
		            mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
		            mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");
		 
		            Properties props = new Properties();
		            props.put("mail.transport.protocol", "smtp");
		            props.put("mail.smtp.host", "smtp.gmail.com");
		            props.put("mail.smtp.port", "465");
		            props.put("mail.smtp.starttls.enable", "true");
		            props.put("mail.smtp.socketFactory.port", "465");
		            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		            props.put("mail.smtp.socketFactory.fallback", "false");
		            props.put("mail.smtp.auth", "true");
		 
		            Authenticator auth = new SMTPAuthenticator();
		 
		            Session sess = Session.getDefaultInstance(props, auth);
		 
		            MimeMessage msg = new MimeMessage(sess);
		 
		            msg.setFrom(new InternetAddress(mail_from));
		            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
		            msg.setSubject(title, "UTF-8");
		            msg.setContent(contents, "text/html; charset=UTF-8");
		            msg.setText(new StringBuffer().append("<fieldset style='text-align:center; height: 1100px;'>")
		            			.append("<legend><h1>회원가입을 환영합니다.</h1></legend><br/>")
		            			.append("<img alt='png01;' src='https://kr.seaicons.com/wp-content/uploads/2015/09/Mail-icon1.png' />")
		            			.append("<p style='font-weight:bold; font-size:12px; '>이 메일은 EveryFarm에서 보낸 메일로 회원가입 절차를 진행하기 위한 메일입니다.</p><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; color:blue;'>인증번호 : ["+authNum+"]</p><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; '>아래 버튼 클릭한 후 인증번호를 입력해 주세요.</p><br/><hr/><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; '>This email was sent by EveryFarm to proceed with the signup process.</p><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; color:blue;'>Verification Code : ["+authNum+"]</p><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; '>Please click the button below and enter the verification code.</p><br/><hr/><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; '>このメールはサインアップためのEveryFarmから送られたメールです。</p><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; color:blue;'>認証番号 : ["+authNum+"]</p><br/>")
		            			.append("<p style='font-weight:bold; font-size:12px; '>下のボタンを押した後、認証番号を入力してください。</p><br/>")
		            			.append("<button style=\"color:white; background:black; font-weight:bold; width:150px; height:40px; font-size:18px; border-radius:10px;\"><a style=\"color:white; text-decoration: none;\" href=\"http://localhost:8787/EveryFarm/signup.do?command=keysend\">Click</a></button>")
		            			.append("</fieldset>").toString()
		            		);
		            msg.setHeader("Content-type", "text/html; charset=UTF-8");
		 
		            Transport.send(msg);
		            System.out.println("이메일 전송 성공****************");
		            response.sendRedirect("signup/signupform.jsp?emailnotused="+emailnotused);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        	System.out.println("[ERROR]:전송에러");
		        } finally {
		    }
			
			}else if(command.equals("keysend")) {  //사용자가 메일에서 링크 클릭 시
				System.out.println("들어왓따 키샌드*****************************************");
		    	session.setAttribute("authNum", session.getAttribute("pre_authNum"));//인증키
		    	System.out.println("authNum요기는 와야해 "+session.getAttribute("pre_authNum"));
		    	jsResponse("홈페이지로 이동합니다.","signup.do?command=signupform",response);
		    	
		    }
	}
		
		public static String getRamdomPassword(int len) {   //인증번호 생성 함수
		  	  char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
		  			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
		  			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
		  			'U', 'V', 'W', 'X', 'Y', 'Z' };

		  	  int idx = 0;
		  	  StringBuffer sb = new StringBuffer();
		  	  
		  	  System.out.println("charSet.length :::: "+charSet.length);
		  	  
		  	  for (int i = 0; i < len; i++) {
		  		
		  		  idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를  Int로 추출 (소숫점제거)
		  		  System.out.println("idx :::: "+idx);
		  		  sb.append(charSet[idx]);
		  	  }

		  	  return sb.toString();
		  	}
			 private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					RequestDispatcher dispatch = request.getRequestDispatcher(url);
					dispatch.forward(request, response);
				}
				private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException { //스크립트 메시지를 편하게 사용하기 위한 메서드(out.println으로 사용해도 된다.)
					String res = "<script type='text/javascript'>"
							   + " alert('"+msg+"');"
							   + " location.href='"+url+"';"
							   + "</script>";
					PrintWriter out = response.getWriter();
					out.println(res);
				}

}
