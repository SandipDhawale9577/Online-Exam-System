package com.controller;

import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Answer;
import com.entity.Questions;

@Controller
public class QuestionController {

	// instead of taking all like qno, submitted answer ,question we directly take
	// answer object
	@RequestMapping("saveresponse")
	public void saveResponse(Answer answer, HttpServletRequest request) {

		System.out.println(answer);
		// we have to put all this in session object and session object is present into
		// the session class
		HttpSession httpsession = request.getSession();
		List<Questions> listofquestions = (List<Questions>) httpsession.getAttribute("allquestions");

		// we require index which we get only from qno
		Questions question = listofquestions.get((int) httpsession.getAttribute("qno"));
		// it will give orignal answer
		String orignalAnswer = question.getAnswer();
		// we are putting updated origanl answer here which we calculate later and then
		// we are putting it so that we will get updated answer
		answer.setOriginalAnswer(orignalAnswer);
		System.out.println(orignalAnswer);

		HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
		hashmap.put(answer.qno, answer);

		/**
		 * here we take submittedDetails beacuse in startexam HashMap<Integer,Answer>
		 * hashmap=new HashMap<Integer, Answer>();
		 * httpsession.setAttribute("submittedDetails",hashmap) we taken this which will
		 * give us get vwill give hashmap in which whole Integer as key and answer as
		 * object present
		 **/

		System.out.println(httpsession.getAttribute("submittedDetails"));

	}

	@RequestMapping("endexam")
	public ModelAndView endExam(HttpServletRequest request) {
		HttpSession httpsession = request.getSession();
// we took hashmap here beacuse if user change the submitted answer hashmap help to update it.
		HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("score");

		if (hashmap != null) {
			Collection<Answer> collection = hashmap.values();
			for (Answer answer : collection) {
				// we are checking if orignal answer=submitted answer then increase score
				if (answer.originalAnswer.equals(answer.submittedAnswer)) {
					httpsession.setAttribute("score", (int) httpsession.getAttribute("score") + 1);
				}
			}
			// modelandview.addObject("score",httpsession.getAttribute("score"));
			// allanswer has submitted answer and orignal answer which we get from
			// collection
			httpsession.setAttribute("allanswer", collection);
// we are insetad of adding it into addobject  model and view we putting it in session so that it will available us on all pages after refreshing also

			// we are removing it because if not do like that then if we clicked on refresh
			// then it increses the score and we removed it so whenever first time method
			// will get called all code get executed and
//and hashmap have null value because we removed the submitted answer and in oreder to avoid null pointer exception we give condition that if (hashmap!=null)
			httpsession.removeAttribute("submittedDetails");
		}
		return modelandview;

	}

//this code for moving to next page and if we are last page and click on next it will show question are over message
	@RequestMapping("next")
	public ModelAndView next(HttpServletRequest request) {
		ModelAndView modelandview = new ModelAndView();
		HttpSession httpsession = request.getSession();

		// listquestionsize is 3 then increase till 1 index at 2 dont increase
		List<Questions> listofquestions = (List<Questions>) httpsession.getAttribute("allquestions");
		// getattribute give list of questions
		// here if there are 3 question then we have to increase size till one only so
		// we have to minus 3 -2=1 will come so
		// we taken qno<=length of list-2 means at last we no need to increment we just
		// increment till last o.w array index out of bound come
		if ((int) httpsession.getAttribute("qno") <= listofquestions.size() - 2) {

			httpsession.setAttribute("qno", (int) httpsession.getAttribute("qno") + 1);
			// to take data from list we use the get method we will get question having no
			// is 1
			Questions question = listofquestions.get((int) httpsession.getAttribute("qno"));
			int qno = question.getQno();
			// code to retrieve previous answer
			HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");

			Answer answer = hashmap.get(qno);
			String previousAnswer = "";
			if (answer != null) {
				previousAnswer = answer.submittedAnswer;
			}

			modelandview.addObject("previousAnswer", previousAnswer);
			modelandview.setViewName("questions");
			modelandview.addObject("question", question);
		} else {
			modelandview.setViewName("questions");

			modelandview.addObject("message", "questions are over");
// to display questions are over on last page we did following
			modelandview.addObject("question", listofquestions.get(listofquestions.size() - 1));// 3-2=1
		}
		return modelandview;

	}

	@RequestMapping("previous")
	public ModelAndView previous(HttpServletRequest request) {
		ModelAndView modelandview = new ModelAndView();
		HttpSession httpsession = request.getSession();

		List<Questions> listofquestions = (List<Questions>) httpsession.getAttribute("allquestions");
//012=3
//012
		// 2>=1 //1>=1 //0>=1 false
		if ((int) httpsession.getAttribute("qno") > 0) { // if index 1 then go previous
			httpsession.setAttribute("qno", (int) httpsession.getAttribute("qno") - 1);

			Questions question = listofquestions.get((int) httpsession.getAttribute("qno"));
			// code to retrieve previous answer
			HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");

			int qno = question.getQno();
			Answer answer = hashmap.get(qno);
			// we are try to taking qno from session if present then we will get submitted
			// answer
			// to display it previous submit answer on question page it is code
			String previousAnswer = "";
			if (answer != null) {
				previousAnswer = answer.submittedAnswer;
			}

			modelandview.setViewName("questions");
			modelandview.addObject("question", question);
			modelandview.addObject("previousAnswer", previousAnswer);
		} else {
			modelandview.addObject("message", "questions are over");
			modelandview.addObject("question", listofquestions.get(0)); // on first page we have to show this message

		}
		return modelandview;

	}

}
