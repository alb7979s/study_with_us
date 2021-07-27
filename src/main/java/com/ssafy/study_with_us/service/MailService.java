package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.util.MailUtil;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
//    private int size;

    //인증키 생성
//    private String getKey(int size) {
//        this.size = size;
//        return getAuthCode();
//    }

    //인증코드 난수 발생
    private String getAuthCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    //인증메일 보내기
    public String sendAuthMail(String email) {
        //6자리 난수 인증번호 생성
        String authKey = getAuthCode();

        //인증메일 보내기
        try {
            MailUtil sendMail = new MailUtil(mailSender);
            sendMail.setSubject("이메일 인증");
            sendMail.setText(new StringBuffer()
                    .append(" <div" 																																																	+
                            "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid red; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+
                            "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+
                            "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">Sweet Tomato</span><br />"																													+
                            "		<span style=\"color: red\">임시 비밀번호</span> 안내입니다."																																				+
                            "	</h1>\n"																																																+
                            "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+
                            "		안녕하세요.<br />"																																														+
                            "		발급된 임시비밀번호는 <b style=\"color: red\">" + authKey + "</b> 입니다. <br />"																											+
                            "		위의 비밀번호로 로그인 후 비밀번호를 변경해주시기 바랍니다.<br/>"                                                                                                                                 +
                            "        감사합니다.<br/>"                                                                                                                                                                           +
                            "	</p>"																																																	+
                            "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\""																																	+
                            "	href='http://localhost:5001\" 'target=\"_blank\">"																							+
                            "		<p"																																																	+
                            "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: red; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							+
                            "			Sweet Tomato 바로가기</p>"																																												+
                            "	</a>"																																																	+
                            "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		+
                            " </div>")
                    .toString());
            sendMail.setFrom(email, "이메일 인증");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return authKey;
    }
}
