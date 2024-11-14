<style>
div
{
	text-align:center;
	border:1px solid grey;
}
</style>

<h1>questionmanegment.jsp</h1>

<div>
<form>

	Qno:- <input type="text" name="qno" placeholder="Enter question number" value="${question.qno}"><br><br>
	
	Question:- <input type="text" name="question" placeholder="Enter question" value="${question.question}"><br><br>
		
	option1 :- <input type="text" name="option1" placeholder="Enter option" value="${question.option1}"><br><br>
	
	option2 :- <input type="text" name="option2" placeholder="Enter option" value="${question.option2}"><br><br>
		
	option3 :- <input type="text" name="option3" placeholder="Enter option" value="${question.option3}"><br><br>
	
	option4 :- <input type="text" name="option4" placeholder="Enter option" value="${question.option4}"><br><br>
		
	Answer :- <input type="text" name="answer" placeholder="Enter Answer" value="${question.answer}"><br><br>
	
	subject :- <input type="text" name="subject" placeholder="Enter subject" value="${question.subject}"><br><br>
		
	<input type="submit" value="addQuestion" formaction="addQuestion">
	<!-- whenever we click on viewQuestion it wiil take above qno and subject and goes to viewQuestion() method -->
	<input type="submit" value="viewQuestion" formaction="viewQuestion">
	<input type="submit" value="deleteQuestion" formaction="deleteQuestion">
	<input type="submit" value="updateQuestion" formaction="updateQuestion">
	
</form>
</div>

<!-- In order to display message qno deleted, update we take below message here -->
${message}