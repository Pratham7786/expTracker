package com.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dao.userDao;
import com.entity.loginUserEntity;
import com.entity.userEntity;
import com.service.MailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class userController {

    @Autowired
    userDao udao;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;
    
    @Autowired
    Cloudinary cloudinary;
    
    @GetMapping("/signup")
    public String signup() {
        return "signup"; // show signup.html page
    }
    
    @GetMapping("/login")
    public String login()
    {
    	return "login";
    }
    
    @PostMapping("/login")
	public String signin()
	{
    	System.out.println("login module1");
		return "login";
	}
    
    @PostMapping("/saveuser")
	public String saveUser(@Validated userEntity user, BindingResult result, Model model,@RequestParam MultipartFile profilePic)
	{
		System.out.println("Without encryption: " + user.getPassword());
		
		String encrypt_pass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encrypt_pass);
		System.out.println("After Encryption: "+user.getPassword());
		
		if(result.hasErrors())
		{
			model.addAttribute("result", result);
			model.addAttribute("user", user);
			return "signup";
		}
		else
		{
			System.out.println(profilePic.getOriginalFilename());
			System.out.println(profilePic.getSize());
			
			try {			
				Map Result = cloudinary.uploader().upload(profilePic.getBytes(),ObjectUtils.emptyMap());
				System.out.println(Result);
				String imagePath = Result.get("secure_url").toString();
				System.out.println(imagePath);
				user.setProfilepicpath(imagePath);
			}catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(user);
			udao.addUsers(user);
		}
				
//		mailService.sendWelcomeEmail(user.getEmail(),user.getFirstname());
		return "login";
	}
  
    @PostMapping("/checkuser")
	public String checkUser(@Validated loginUserEntity user, BindingResult result, Model model, HttpSession session)
	{
    	System.out.println("check user");
		if(result.hasErrors())
		{
			model.addAttribute("result", result);
			model.addAttribute("user", user);
			
			System.out.println("Errors hai");
			return "login";
		}
		else
		{
			String email = user.getEmail();
			String password = user.getPassword();
			userEntity user2 = udao.getbyEmail(email);
			System.out.println(email);
			System.out.println(password);
			if(user2!=null)
			{
				if(password.equals(user2.getPassword()))
				{
					System.out.println("Correct Password..!");
					session.setAttribute("userEmail", user2.getEmail());
					session.setAttribute("userid",user2.getUserid());
					return "home";
				}
				else
				{
					System.out.println("incorrect Password..!");
					return "login";
				}
			}
			else
			{
				System.out.println("incorrect Email..!");
				return "login";
			}
		}
	}
    
    @PostMapping("authenticate")
    public String authenticate(String email,String password,Model model,HttpSession httpSession)
    {
    	userEntity dbuser = udao.getbyEmail(email);
    	if(dbuser==null)
    	{
    		model.addAttribute("invalid","Invlaid credential!");
    		return "login";
 
    	}
    	else {
    		boolean ans = passwordEncoder.matches(password, dbuser.getPassword());
    		if(ans==true)
    		{
    			httpSession.setAttribute("user", dbuser);
    			return "home";
    		}    		
    		model.addAttribute("invalid","Invlaid credential!");
    		return "login";
    	}
    }
    
}

