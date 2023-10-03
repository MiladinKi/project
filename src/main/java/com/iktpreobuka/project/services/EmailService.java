package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.VoucherEntity;

public interface EmailService {
	
	String createVoucherTableHtml(VoucherEntity voucher);

	void sendVoucherEmail(UserEntity userEntity, VoucherEntity voucher);

	void sendVoucherEmail(String userEmail, VoucherEntity voucher);

}
