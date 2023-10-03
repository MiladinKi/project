package com.iktpreobuka.project.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iktpreobuka.project.entities.CategoryEntity;
import com.iktpreobuka.project.entities.EOfferStatus;
import com.iktpreobuka.project.entities.EUserRole;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.repository.CategoryRepository;
import com.iktpreobuka.project.repository.OfferRepository;
import com.iktpreobuka.project.repository.UserRepository;
import com.iktpreobuka.project.services.BillService;
import com.iktpreobuka.project.services.OfferService;

@RestController
@RequestMapping(path = "/api/v1/offers")
public class OfferController {
	
	private static String UPLOAD_DIRECTORY = "C:\\Temp\\";
	List<OfferEntity> offers = new ArrayList<>();

	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private BillService billService;
	
//	private List<OfferEntity> getDB() {

//		if (offers.size() == 0) {
//
//			Calendar cal = Calendar.getInstance();
////			cal.setTime(new Date(0));
//			cal.add(Calendar.DATE, 5);
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date offerCreatedDate = null;
//			
//			try {
//			    offerCreatedDate = (Date) dateFormat.parse("2000-01-01");
//			} catch (ParseException e) {
//			    e.printStackTrace();
//			}
//
//			OfferEntity o1 = new OfferEntity(1, "Dinner", "Pizza", offerCreatedDate, cal.getTime(), 11.99, 7.99, " ", 10, 3,
//					EOfferStatus.WAIT_FOR_APPROVING);
//			OfferEntity o2 = new OfferEntity(2, "TV", "LG", new Date(), cal.getTime(), 299.99, 249.99, " ", 7, 0,
//					EOfferStatus.WAIT_FOR_APPROVING);
//			OfferEntity o3 = new OfferEntity(3, "2 tickets to Zanzibar", "Travel", new Date(0), cal.getTime(), 1499.99,
//					1099.99, " ", 5, 1, EOfferStatus.WAIT_FOR_APPROVING);
//
//			offers.add(o1);
//			offers.add(o2);
//			offers.add(o1);
//		}
//		return offers;
//	}

	@RequestMapping(method = RequestMethod.GET)
	public List<OfferEntity> getAllOffers() {
		return (List<OfferEntity>) offerRepository.findAll();
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public OfferEntity newOffer(@RequestParam String offerName, @RequestParam String offerDescription,
//			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date offerCreated, @RequestParam Double regularPrice,
//			@RequestParam Double actionPrice, @RequestParam String imagePath, @RequestParam Integer availableOffers,
//			@RequestParam Integer boughtOffers, @RequestParam EOfferStatus offerStatus) {
//
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		cal.add(Calendar.DATE, 5);
//		Date offerExpires = cal.getTime();
//
//		OfferEntity offer = new OfferEntity();
//		offer.setOfferName(offerName);
//		offer.setOfferDescription(offerDescription);
//		offer.setOfferCreated(new Date());
//		offer.setOfferExpires(cal.getTime());
//		offer.setRegularPrice(regularPrice);
//		offer.setActionPrice(actionPrice);
//
//		offer.setAvailableOffers(availableOffers);
//		offer.setBoughtOffers(0);
//		offer.setOfferStatus(offerStatus);
//		offer.setImagePath(imagePath);
//		offerRepository.save(offer);
//		return offer;
//	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{categoryId}/seller/{sellerId}")
	public OfferEntity addOffer(@RequestBody OfferEntity newOffer, @PathVariable Integer categoryId,
			@PathVariable Integer sellerId, @PathVariable EUserRole userRole) {
		OfferEntity offer = new OfferEntity();
		CategoryEntity category = categoryRepository.findById(categoryId).get();
		UserEntity seller = userRepository.findById(sellerId).get();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 10);
		
		if(seller.getUserRole().equals(userRole.ROLE_SELLER)) {
			if(newOffer.getOfferName() != null) {
				offer.setOfferName(newOffer.getOfferName());
			}
			if (newOffer.getOfferDescription() != null)
				offer.setOfferDescription(newOffer.getOfferDescription());
			if (newOffer.getImagePath() != null)
			offer.setImagePath(newOffer.getImagePath());
			offer.setAvailableOffers(newOffer.getAvailableOffers());
			offer.setActionPrice(newOffer.getActionPrice());
			offer.setRegularPrice(newOffer.getRegularPrice());
			offer.setOfferCreated(new Date());
			offer.setOfferExpires(cal.getTime());
			offer.setBoughtOffers(0);
			offer.setOfferStatus(EOfferStatus.WAIT_FOR_APPROVING);
			offer.setUser(seller);
			offer.setCategory(category);
			offerRepository.save(offer);
		}
		return offer;
	}
	

//	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//	public OfferEntity changeOffer(@RequestBody OfferEntity updatedOffer, @PathVariable Integer id) {
//		OfferEntity offer = offerRepository.findById(id).get();
//		
//		
//		
//		if (updatedOffer.getOfferName() != null) {
//			offer.setOfferName(updatedOffer.getOfferName());
//		}
//		if (updatedOffer.getOfferDescription() != null) {
//			offer.setOfferDescription(updatedOffer.getOfferDescription());
//		}
//		if (updatedOffer.getOfferCreated() != null) {
//			offer.setOfferCreated(updatedOffer.getOfferCreated());
//			 Calendar cal = Calendar.getInstance();
//		        cal.setTime(updatedOffer.getOfferCreated());
//		        cal.add(Calendar.DATE, 5);
//		        Date offerExpires = cal.getTime();
//		        offer.setOfferExpires(offerExpires);
//		}
//
//		if (updatedOffer.getRegularPrice() != null) {
//			offer.setRegularPrice(updatedOffer.getRegularPrice());
//		}
//		if (updatedOffer.getActionPrice() != null) {
//			offer.setActionPrice(updatedOffer.getActionPrice());
//		}
//		if (updatedOffer.getImagePath() != null) {
//			offer.setImagePath(updatedOffer.getImagePath());
//		}
//		if (updatedOffer.getAvailableOffers() != null) {
//			offer.setAvailableOffers(updatedOffer.getAvailableOffers());
//		}
//		if (updatedOffer.getBoughtOffers() != null) {
//			offer.setBoughtOffers(updatedOffer.getBoughtOffers());
//		}
//		if (updatedOffer.getOfferStatus() != null) {
//			offer.setOfferStatus(updatedOffer.getOfferStatus());
//		}
//
//		offerRepository.save(offer);
//
//		return offer;
//	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/category/{categoryId}")
	public OfferEntity changeOffer(@RequestBody OfferEntity updatedOffer, @PathVariable Integer id,
			@PathVariable Integer categoryId) {
		
		OfferEntity offer = offerRepository.findById(id).get();
		CategoryEntity category = categoryRepository.findById(categoryId).get();
		if (updatedOffer.getOfferName() != null) {
			offer.setOfferName(updatedOffer.getOfferName());
		}
		if (updatedOffer.getOfferDescription() != null) {
			offer.setOfferDescription(updatedOffer.getOfferDescription());
		}
		
		if(updatedOffer.getOfferCreated() != null) {
			offer.setOfferCreated(updatedOffer.getOfferCreated());
			Calendar cal = Calendar.getInstance();
			cal.setTime(updatedOffer.getOfferCreated());
			cal.add(Calendar.DATE, 5);
			Date offerrExpires = cal.getTime();
			offer.setOfferExpires(offerrExpires);
		}
		if (updatedOffer.getRegularPrice() != null) {
			offer.setRegularPrice(updatedOffer.getRegularPrice());
		}
		if (updatedOffer.getActionPrice() != null) {
			offer.setActionPrice(updatedOffer.getActionPrice());
		}
		if (updatedOffer.getImagePath() != null) {
			offer.setImagePath(updatedOffer.getImagePath());
		}
		if (updatedOffer.getAvailableOffers() != null) {
			offer.setAvailableOffers(updatedOffer.getAvailableOffers());
		}
		if (updatedOffer.getBoughtOffers() != null) {
			offer.setBoughtOffers(updatedOffer.getBoughtOffers());
		}
		if (updatedOffer.getOfferStatus() != null) {
			offer.setOfferStatus(updatedOffer.getOfferStatus());
		}
		if(updatedOffer.getCategory() != null) {
			offer.setCategory(category);
		}
		
		offerRepository.save(offer);
		return offer;
		
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteOffer(@PathVariable Integer id) {
		offerRepository.deleteById(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public OfferEntity getOfferById(@PathVariable Integer id) {
		return offerRepository.findById(id).get();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/changeOffer/{id}/status/{status}")
	public OfferEntity changeOfferStatus(@PathVariable Integer id, @PathVariable EOfferStatus status) {
		OfferEntity offer = offerRepository.findById(id).get();
		offer.setOfferStatus(status);
		offerRepository.save(offer);
		return offer;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findByPrice/{lowerPrice}/and/{upperPrice}")
	public Iterable<OfferEntity> getAllOffersToPrice(@PathVariable Double lowerPrice, @PathVariable Double upperPrice) {
		Iterable<OfferEntity> priceOffers = new ArrayList<>();
		priceOffers = offerRepository.findAllByActionPriceBetween(lowerPrice, upperPrice);
		offerRepository.saveAll(priceOffers);
		return priceOffers;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{offerId}/update-offers")
	public OfferEntity updateBoughtOffers(@PathVariable Integer offerId, @RequestParam Integer boughtOffers) {
		return offerService.updateAvailableBoughtOffer(offerId, boughtOffers);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/uploadImage/{id}")
	public OfferEntity uploadImage(@PathVariable Integer id, @RequestParam ("imageFile") MultipartFile imageFile) throws IOException {
		OfferEntity offer = offerRepository.findById(id).get();
		if(offer == null) {
			return null;
		}
		try {
			byte[] bytes = imageFile.getBytes();
			Path path = Paths.get(UPLOAD_DIRECTORY + imageFile.getOriginalFilename());
			Files.write(path, bytes);
			
			offer.setImagePath(path.toString());
			offerRepository.save(offer);
		}
		catch (IOException e) {
            e.printStackTrace();
            return null;
        }
		return offer;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/changeStatus/{id}")
	public ResponseEntity<OfferEntity> changeOfferStatus(@PathVariable Integer id){
		OfferEntity offer = offerRepository.findById(id).get();
		if(offer == null) {
			return null;
		}
		
		offer.setOfferStatus(EOfferStatus.EXPIRED);
		
		if(offer.getOfferStatus().equals(EOfferStatus.EXPIRED)) {
			billService.cancelBillsByOffer(offer);
		}
		offer = offerService.updateOffer(offer);
		return ResponseEntity.ok(offer);
	}
}
