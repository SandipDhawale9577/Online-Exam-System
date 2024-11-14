package com.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Answer;
import com.entity.Questions;
import com.entity.User;

@Controller
public class LoginController {
	@Autowired

	SessionFactory factory;

	// localhost:8080/login
	@RequestMapping("login")
	public String login() {
		return "login"; // here login is jsp page name
	}

	@RequestMapping("admin")
	public String questionmanegment() {
		return "questionmanegment"; // here questionmanegment is jsp page name
	}

	@RequestMapping("validate")
	public ModelAndView validate(String username, String password, HttpServletRequest request) {
		ModelAndView modelandview = new ModelAndView();

		Session session = factory.openSession();

		User userfromdb = session.get(User.class, username);
		// userfromdb it contain whole data from the database

		if (userfromdb == null) {
			modelandview.setViewName("login"); // view page name means JSP page name
			modelandview.addObject("message", "wrong username"); // here message attribute represents data which will be
																	// displayed on view page . here message is called
																	// model attribute

		}

		else if (userfromdb.password.equals(password)) {
			modelandview.setViewName("welcome"); // view page name means JSP page name
			HttpSession httpsesssion = request.getSession();
			httpsesssion.setAttribute("username", username);// used to kept data into session
		}

		else {
			modelandview.setViewName("login"); // view page name means JSP page name
			modelandview.addObject("message", "wrong password"); // here message attribute represents data which will be
																	// displayed on view page . here message is called
																	// model attribute
		}

		return modelandview;

	}
	// whatever subject user will select from login page that will come into below
	// selectsubject

	@RequestMapping("startExam")
	public ModelAndView startExam(String selectedSubject, HttpServletRequest request)

	// we took HttpServletRequest here because we we use its getSession() method
	// below used to store user specific data
	{
		System.out.println(selectedSubject);

		ModelAndView modelAndView = new ModelAndView();

		if (selectedSubject == null) {
			modelAndView.setViewName("login");
		} else {
			Session session = factory.openSession();

			/*
			 * Criteria is for only fetching records ( select query) HQL is for all
			 * operations (insert , update,delete,select )
			 */
			// using add() we add condition , based on which records are fetched from
			// database
			// e.g. we want only those records which are having value maths for subject
			// column ( assume selectedsubject is maths)
			// select * from questions where subject='maths'
			// List
			// listOfQuestions=session.createCriteria(Questions.class).add(Restrictions.eq("subject",selectedSubject)).list();

			HttpSession httpsession = request.getSession();
			// we use httpsession here because for each user question will be different here
			// we kept 0 and if we click on next buttion it will updated to 1
			httpsession.setAttribute("qno", 0);
			httpsession.setAttribute("timeremaining", 121);
			// Query query=session.createQuery("from Questions where subject=:subject order
			// by rand()");
//to load the subject on basis of subject we wrote below query
			Query query = session.createQuery("from Questions where subject=:subject");
			query.setParameter("subject", selectedSubject);
			List<Questions> listOfQuestions = query.list();
			// to select list of question we use list over here

			modelAndView.setViewName("questions");
			modelAndView.addObject("listOfQuestions", listOfQuestions);
			// listOfQuestions.get(0)); is question class object
			// to display 0 number question
			modelAndView.addObject("question", listOfQuestions.get(0));
			// allquestion have value as list
			httpsession.setAttribute("allquestions", listOfQuestions);
			// we kept all data of answer class to hashmap
			// hashmap replaces oldvalue with new value do not take duplicate key
			HashMap<Integer, Answer> hashmap = new HashMap<Integer, Answer>();
			httpsession.setAttribute("submittedDetails", hashmap);
			// we kept score and subject here so that it will get us on all pages
			httpsession.setAttribute("score", 0);

			httpsession.setAttribute("subject", selectedSubject);
		}
		return modelAndView;

	}

	@RequestMapping("register")
	public String register() {

		return "register";

	}

	@RequestMapping("saveUserData")
	public ModelAndView saveUserData(User userfrombrowser, HttpServletRequest request) {

		// HttpServletRequest used to images folder path
		MultipartFile filedata = userfrombrowser.getImages();

		// MultipartFile object contains image data

		String filename = filedata.getOriginalFilename();

		System.out.println(filename);// xyz.jpg\
		HttpSession httpsession = request.getSession();
		httpsession.setAttribute("imagename", filename);

		File file = new File(request.getServletContext().getRealPath("/images"), filename);

		try {
			// transfer to is method of MultipartFile
			filedata.transferTo(file);

			System.out.println("File uploaded successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView modelandview = new ModelAndView();

		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();

		userfrombrowser.setImagepath(filename);

		session.save(userfrombrowser);

		tx.commit();

		modelandview.addObject("message", "Registration successful . pls login now");

		modelandview.setViewName("login"); // view page name means JSP page name

		return modelandview;
	}
}
