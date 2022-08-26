package com.food.food_app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.food.food_app.dao.FoodDao;
import com.food.food_app.dao.FoodOrderDao;
import com.food.food_app.dto.Food;
import com.food.food_app.dto.FoodOrder;
import com.food.food_app.exception.FoodNotAvailableException;
import com.food.food_app.exception.FoodNotFoundException;
import com.food.food_app.exception.FoodOrderNotFoundException;
import com.food.food_app.exception.FoodOrderPhoneNotFoundException;
import com.food.food_app.util.ResponceStructure;

@Service
public class FoodOrderService {

	@Autowired
	FoodOrderDao foodOrderDao;
	@Autowired
	FoodDao foodDao;
	@Autowired
    JavaMailSender javaMailSender;

	// FoodOrder
	public ResponseEntity<ResponceStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder) {
		List<Food> foods = foodOrder.getFoods();
		Date date = new Date();
		int price = 0;
		for (Food i : foods) {
			Optional<Food> food = foodDao.findFoodById(i.getId());
			if(food.isEmpty()) {
				throw new FoodNotFoundException(i.getId());
			}
			else if (!food.get().isAvailability()) {
				throw new FoodNotAvailableException(i.getId());
			} else {
				price += food.get().getPrice();
			}
		}
		foodOrder.setQuantity(foods.size());
		foodOrder.setPrice(price);
		foodOrder.setDate(date);
		ResponceStructure<FoodOrder> structure = new ResponceStructure<FoodOrder>();
		structure.setMessage("FoodOrder Saved Successfully!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(foodOrderDao.saveFoodOrder(foodOrder));
		return new ResponseEntity<ResponceStructure<FoodOrder>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponceStructure<FoodOrder>> updateFoodOrder(FoodOrder foodOrder, int id) {
		List<Food> foods = foodOrder.getFoods();
		Date date = new Date();
		int price = 0;
		for (Food i : foods) {
			Optional<Food> food = foodDao.findFoodById(i.getId());
			if(food.isEmpty()) {
				throw new FoodNotFoundException(i.getId());
			}
			else if (!food.get().isAvailability()) {
				throw new FoodNotAvailableException(i.getId());
			} else {
				price += food.get().getPrice();
			}
		}
		foodOrder.setQuantity(foods.size());
		foodOrder.setPrice(price);
		foodOrder.setDate(date);
		FoodOrder temp = foodOrderDao.updateFoodOrder(foodOrder, id);
		ResponceStructure<FoodOrder> structure = new ResponceStructure<FoodOrder>();
		if (temp != null) {
			structure.setMessage("FoodOrder Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<FoodOrder>>(structure, HttpStatus.OK);
		} else {
			throw new FoodOrderNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<FoodOrder>> findFoodOrderById(int id) {
		Optional<FoodOrder> optional = foodOrderDao.findFoodOrderById(id);
		if (optional.isEmpty()) {
			throw new FoodOrderNotFoundException(id);
		} else {
			ResponceStructure<FoodOrder> structure = new ResponceStructure<FoodOrder>();
			structure.setMessage("FoodOrder found Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponceStructure<FoodOrder>>(structure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponceStructure<FoodOrder>> deleteFoodOrder(int id) {
		FoodOrder temp = foodOrderDao.deleteFoodOrder(id);
//		System.out.println(temp);
		ResponceStructure<FoodOrder> structure = new ResponceStructure<FoodOrder>();
		if (temp != null) {
			structure.setMessage("FoodOrder Deleted Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(temp);
			return new ResponseEntity<ResponceStructure<FoodOrder>>(structure, HttpStatus.OK);
		} else {
			throw new FoodOrderNotFoundException(id);
		}
	}

	public ResponseEntity<ResponceStructure<List<FoodOrder>>> findAllFoodOrder() {
		List<FoodOrder> list = foodOrderDao.findAllFoodOrder();
		ResponceStructure<List<FoodOrder>> structure = new ResponceStructure<List<FoodOrder>>();
		if (list.size() == 0) {
			structure.setMessage("No Food Order available!");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponceStructure<List<FoodOrder>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setMessage("FoodOrders found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(list);
		return new ResponseEntity<ResponceStructure<List<FoodOrder>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponceStructure<String>> findBillByPhone(long phone) {
		List<FoodOrder> foodOrder = foodOrderDao.findFoodOrderByPhone(phone,Sort.by(Sort.Direction.DESC, "id"));
		if(foodOrder.isEmpty()) {
			throw new FoodOrderPhoneNotFoundException(phone);
		}
		FoodOrder lastOrder = foodOrder.get(0);
		
		int gross_amount = lastOrder.getPrice();
        double gst = gross_amount * 0.12;
        double service_charge = gross_amount * 0.05;
        double net_amount = gross_amount + gst + service_charge;
        
        StringBuilder foodString = new StringBuilder();
        int temp = 1;
        for(Food i:lastOrder.getFoods()) {
        	foodString.append(temp +  "||" + i.getName() + "|" + i.getPrice() + "||");
            temp++;
        }
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Order Id: " + lastOrder.getId() + "||" +
		        "Date: " + lastOrder.getDate() + "||" +
		        "Branch: " + lastOrder.getBranch().getName() + ", " + lastOrder.getBranch().getCity() + ", " + lastOrder.getBranch().getPhone() + "||" +
		        "Name: " + lastOrder.getName() + "||" +
		        "Sr no." + "|" + "Food Name" + "|" + "Price||" +
		        "||---" +
		        foodString +
		        "---||" +
		        "Gross Amount: " + gross_amount + "||" +
		        "GST @12%: " + gst + "||" +
		        "Service Charge @5%: " + service_charge + "||" +
		        "Net Payable Amount: (" + gross_amount + "+" + gst + "+" + service_charge + ") = " + net_amount + "||" +
		        "Thank you!");
		
		ResponceStructure<String> structure = new ResponceStructure<String>();
		structure.setMessage("FoodOrder found Successfully!");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(stringBuilder.toString());
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponceStructure<String>> findBillByOrderId(int id, String email) {
        Optional<FoodOrder> foodOrder = foodOrderDao.findFoodOrderById(id);
        if(foodOrder.isEmpty()) {
        	throw new FoodOrderNotFoundException(id);
        }
        int gross_amount = foodOrder.get().getPrice();
        double gst = gross_amount * 0.12;
        double service_charge = gross_amount * 0.05;
        double net_amount = gross_amount + gst + service_charge;
        
        StringBuilder stringBuilder = new StringBuilder();
        int temp = 1;
        for(Food i:foodOrder.get().getFoods()) {
            stringBuilder.append(temp +  "\t" + i.getName() + "\t" + i.getPrice() + "\n");
            temp++;
        }
        
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@foodapp.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Bill for your order from Food-App is here");
        simpleMailMessage.setText("Order Id: " + id + "\n" +
                "Date: " + foodOrder.get().getDate() + "\n" +
                "Branch: " + foodOrder.get().getBranch().getName() + ", " + foodOrder.get().getBranch().getCity() + ", " + foodOrder.get().getBranch().getPhone() + "\n" +
                "Name: " + foodOrder.get().getName() + "\n\n" +
                "Sr no." + "\t" + "Food Name" + "\t" + "Price\n" +
                "-------------------------------\n" +
                stringBuilder +
                "-------------------------------\n\n" +
                "Gross Amount: " + gross_amount + "\n" +
                "GST @12%: " + gst + "\n" +
                "Service Charge @5%: " + service_charge + "\n" +
                "Net Payable Amount: (" + gross_amount + "+" + gst + "+" + service_charge + ") = " + net_amount + "\n\n" +
                "Thank you!"
                );



        javaMailSender.send(simpleMailMessage);
        
//        String message = simpleMailMessage.getText();
        
        ResponceStructure<String> structure = new ResponceStructure<String>();
        structure.setMessage("FoodOrder found!");
        structure.setStatus(HttpStatus.OK.value());
        structure.setT("Bill generated and sent to email!");
        return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.OK);
    }
	
}
