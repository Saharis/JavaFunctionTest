package com.virgil.mail;

public class FunctionEntrance {
	public static void main(String[] args){   
        //�������Ҫ�������ʼ�   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.163.com");    
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("saharis@163.com");    
     mailInfo.setPassword("1225_tanor,");//������������    
     mailInfo.setFromAddress("saharis@163.com");    
     mailInfo.setToAddress("liuwj@ctrip.com");    
     mailInfo.setSubject("����������� ��http://www.guihua.org �й�����");    
     mailInfo.setContent("������������ ��http://www.guihua.org �й����� ���й�������վ==");    
        //�������Ҫ�������ʼ�   
     SimpleMailSender sms = new SimpleMailSender();   
         sms.sendTextMail(mailInfo);//���������ʽ    
         sms.sendHtmlMail(mailInfo);//����html��ʽ   
   }  
}
