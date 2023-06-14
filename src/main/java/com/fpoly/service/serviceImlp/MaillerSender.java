package com.fpoly.service.serviceImlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fpoly.service.MailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MaillerSender implements MailService {
	@Autowired
	JavaMailSender sender;

	@Override
	public void send(String to, String body) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom("Glasses-Shop");
		helper.setTo(to);
		helper.setSubject("Glasses Shop - Cửa Hàng Mắt Kính Thời Trang");
		helper.setText("Welcome To Glasses Shop <br> Đây là mã xác thực của bạn: " + "<b>" + body + "</b> <br>"
				+ "<p style='color:red'>" + "Lưu ý mã xác thực chỉ thời hạn trong 2 phút !" + "</p>", true);
		helper.setReplyTo("Glasses-Shop");
		sender.send(message);
	}
}
