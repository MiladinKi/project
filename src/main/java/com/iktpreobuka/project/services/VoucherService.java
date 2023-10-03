package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.VoucherEntity;

public interface VoucherService {
	public VoucherEntity createVoucherFromBill(BillEntity bill);
}
