package com.iktpreobuka.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.VoucherEntity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

//	@Autowired
//	private JavaMailSender emailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;

//	public void EmailService(JavaMailSender emailSender) {
//		this.emailSender = emailSender;
//	}
	@Override
	public String createVoucherTableHtml(VoucherEntity voucher) {
	    StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html><head>");
        sb.append("<title>Voucher</title>");
        sb.append("<style>table { border:4px double #161601; border-collapse:collapse; padding:10px;}");
        sb.append("table th { border:4px double #161601; padding:10px; background: #f0f0f0; color: #313030;}");
        sb.append("table td { border:4px double #161601; text-align:center; padding:10px; background: #ffffff; color: #313030;}</style></head>");
        sb.append("<body><table><thead><tr>");
        sb.append("<th>Buyer</th>");
        sb.append("<th>Offer</th>");
        sb.append("<th>Price</th>");
        sb.append("<th>Expiries date</th>");
        sb.append("</tr></thead><tbody><tr>");
        sb.append("<td>" + voucher.getUser().getFirstName() + " " + voucher.getUser().getLastName() + "</td>");
        sb.append("<td>" + voucher.getOffer().getOfferName() + "</td>");
        sb.append("<td>" + voucher.getOffer().getActionPrice() + "</td>");
        sb.append("<td>" + voucher.getExpirationDate()+ "</td>");
        sb.append("</tr></tbody></table></body></html>");
        return sb.toString();
	}

	@Override
	public void sendVoucherEmail(String userEmail, VoucherEntity voucher) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(userEmail);
			helper.setSubject("Your Voucher");
			String voucherTable = createVoucherTableHtml(voucher);
			helper.setText(voucherTable, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException("An error occurred while sending the email", e);
		}

	}
	  public void sendVoucherEmail(UserEntity user, VoucherEntity voucher) {
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(user.getEmail()); // Postavite e-mail adresu kupca
	        mailMessage.setSubject("Vaš vaučer je spreman!");
	        mailMessage.setText("Dragi " + user.getFirstName() + ",\n\n"
	                + "Vaš vaučer je spreman za korišćenje. Evo detalja:\n\n"
	                + "Kod vaučera: " + voucher.getId() + "\n"
	                + "Datum isteka: " + voucher.getExpirationDate() + "\n\n"
	                + "Hvala vam što koristite naše usluge!\n\n"
	                + "Srdačan pozdrav.");

	        javaMailSender.send(mailMessage);
	    }
//	@Override
//	public void sendVoucherEmail(UserEntity userEntity, VoucherEntity voucher) {
//		// TODO Auto-generated method stub
//		
//	}




}
