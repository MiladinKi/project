package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.OfferEntity;

public interface OfferService {
	public OfferEntity updateAvailableBoughtOffer(Integer offerId, Integer boughtOffers);
	public void cancelOffer(Integer offerId);
	public boolean hasActiveOffersInCategory(Integer categoryId);
	public OfferEntity updateOffer(OfferEntity offer);
}
