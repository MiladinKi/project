package com.iktpreobuka.project.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.EOfferStatus;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.repository.OfferRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Override
	public OfferEntity updateAvailableBoughtOffer(Integer id, Integer boughtOffers) throws EntityNotFoundException, IllegalArgumentException {
		OfferEntity offer = offerRepository.findById(id).get();
		if(offer == null) {
			throw new EntityNotFoundException("Offer with id " + id + " not found!");
		}
		if(offer.getAvailableOffers()<= 0) {
			throw new IllegalArgumentException("Offer with id " + id + " is not available!");
		}
		Integer ao = offer.getAvailableOffers();
		Integer bo = offer.getBoughtOffers();
		
		offer.setAvailableOffers(ao - boughtOffers);
		offer.setBoughtOffers(bo + boughtOffers);
		offerRepository.save(offer);
		return offer;
		
	}

	@Override
	public void cancelOffer(Integer offerId) {
		OfferEntity offer = offerRepository.findById(offerId).get();
		offer.setOfferStatus(EOfferStatus.DECLINED);
		Integer availableOffers = offer.getAvailableOffers();
		Integer boughtOffers = offer.getBoughtOffers();
		offer.setAvailableOffers(availableOffers + 1);
		offer.setBoughtOffers(boughtOffers - 1);
		offerRepository.save(offer);
	}

	@Override
	public boolean hasActiveOffersInCategory(Integer categoryId) {
		LocalDate currentDate = LocalDate.now();
		Iterable<OfferEntity> activeOffers = offerRepository.findAllByCategory_IdAndOfferExpiresAfter(categoryId, currentDate);
		return activeOffers.iterator().hasNext();
	}

	@Override
	public OfferEntity updateOffer(OfferEntity offer) {
		return offerRepository.save(offer);
	}

}
