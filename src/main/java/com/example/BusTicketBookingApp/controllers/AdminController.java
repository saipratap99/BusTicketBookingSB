package com.example.BusTicketBookingApp.controllers;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.utils.BasicUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BasicUtil basicUtil;
	
	@GetMapping("/users")
	public String getUsers(Model model, Principal principal) {
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		List<User> users = userRepo.findAll();
		List<Map<String, String>> usersMap = new LinkedList<>();
		
		for(User user: users) {
			Map<String, String> userMap = new LinkedHashMap<>();
			userMap.put("User Id", String.valueOf(user.getId()));
			userMap.put("Email", user.getEmail());
			userMap.put("Name", user.getFirstName() + " " + user.getLastName());
			userMap.put("Role", user.getRole());
			userMap.put("Oparator Name", user.getOperator() == null ? "Not an Operator" : user.getOperator());
			userMap.put("Update", "/admin/" + user.getId() + ("ROLE_OPERATOR".equals(user.getRole()) ? "/remove_as_operator" : "/make_as_operator"));
		
			if(!"ROLE_ADMIN".equals(user.getRole()))
				usersMap.add(userMap);
		}
		
		List<String> colHeaders = new LinkedList<>();
		if(!usersMap.isEmpty()) 
			colHeaders.addAll(usersMap.get(0).keySet());
		
		
		model.addAttribute("usersMap", usersMap);
		model.addAttribute("colHeaders", colHeaders);
		
		return "/admin/users.jsp";
	}
	
	@GetMapping("/{id}/make_as_operator")
	public String makeOperatorForm(@PathVariable int id, Model model, Principal principal, @ModelAttribute("msg") String msg, @ModelAttribute("show") String show, @ModelAttribute("status") String status) {
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		basicUtil.addMsgToModel(msg, status, show, model);
		
		Optional<User> user = userRepo.findById(id);
		model.addAttribute("id", id);
		return "/admin/make_operator.jsp";
	}
	
	@PostMapping("/{id}/make_as_operator")
	public String makeOperator(@PathVariable int id, String operatorName, Model model, Principal principal, RedirectAttributes redirectAttributes) {
		
		Optional<User> currentUser = basicUtil.getUser(principal);
		Optional<User> user = userRepo.findById(id);
		if("ROLE_ADMIN".equals(currentUser.get().getRole()) && user.isPresent() && !operatorName.isEmpty()) {
			user.get().setRole("ROLE_OPERATOR");
			user.get().setOperator(operatorName.toUpperCase());
			userRepo.save(user.get());
			basicUtil.addMsgToRedirectFlash(user.get().getFirstName() + " Role has been changed to operator", "success", "show", redirectAttributes);
		}
		
		return "redirect:/admin/users";
	}
	
	@GetMapping("/{id}/remove_as_operator")
	public String removeOperatorForm(@PathVariable int id, Model model, Principal principal, @ModelAttribute("msg") String msg, @ModelAttribute("show") String show, @ModelAttribute("status") String status) {
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		basicUtil.addMsgToModel(msg, status, show, model);
		
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			model.addAttribute("id", id);
			model.addAttribute("name", user.get().getFirstName() + " " + (user.get().getLastName() != null ? user.get().getLastName() : ""));
		}else 
			return "redirect:/admin/users";
		
		return "/admin/remove_as_operator.jsp";
	}
	
	@PostMapping("/{id}/remove_as_operator")
	public String removeOperator(@PathVariable int id, Model model, Principal principal, RedirectAttributes redirectAttributes) {
		
		Optional<User> currentUser = basicUtil.getUser(principal);
		Optional<User> user = userRepo.findById(id);
		if("ROLE_ADMIN".equals(currentUser.get().getRole()) && user.isPresent()) {
			user.get().setRole("ROLE_USER");
			user.get().setOperator(null);
			userRepo.save(user.get());
			basicUtil.addMsgToRedirectFlash(user.get().getFirstName() + " Role has been changed to user", "success", "show", redirectAttributes);
		}
		
		return "redirect:/admin/users";
	}
}
