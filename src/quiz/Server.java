package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * Servlet implementation class Server
 */
@WebServlet("/admin")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameterMap().containsKey("inboxEditor")){
			String editorUsername=request.getParameter("username");
			System.out.println("INBOX");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
	 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("SELECT u FROM Quiz u where u.editor.username_='"+editorUsername+"'");
				List<Quiz> quizes = q.getResultList();
				try {
					JSONArray json=new JSONArray();
					for(int i=0;i<quizes.size();++i){
						JSONObject quiz = new JSONObject();
						JSONArray players=new JSONArray();
						Query q1 = em.createQuery("SELECT u FROM Player u where u.quiz.quiz_id_='"+quizes.get(i).getQuiz_id_()+"'");
						List<Player> Players = q1.getResultList();
						for(int ii=0;ii<Players.size();++ii){
							JSONObject player = new JSONObject();
							player.append("first_name_", Players.get(ii).getFirst_name_());
							player.append("last_name_", Players.get(ii).getLast_name_());
							player.append("e_mail_", Players.get(ii).getE_mail_());
							Query q2=em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+quizes.get(i).getQuiz_id_()+"'");
							List<Question> questions= q2.getResultList();
							int points=0;
							for(int iii=0;iii<questions.size();++iii){
								Query q3=em.createQuery("SELECT u FROM Answer u where u.question.question_id_='"+questions.get(iii).getQuestion_id_()+"'");
								List<Answer> answers= q3.getResultList();
								int isCorrect=0;
								for(int iiii=0;iiii<answers.size();++iiii){
									PlayersAnswer answer=(PlayersAnswer)em.createQuery("SELECT u FROM PlayersAnswer u where u.question.question_id_='"+questions.get(iii).getQuestion_id_()+"'and u.player.user_id_='"+Players.get(ii).getUser_id_()+"' and u.name_='"+answers.get(iiii).getName_()+"'").getSingleResult();
									if(answer.getCorrect_()==answers.get(iiii).getCorrect_()){
										++isCorrect;
									}
								if(isCorrect==answers.size())
									points=points+questions.get(iii).getPoints_();
								}
							}
							player.append("points", points);
							player.append("id", Players.get(ii).getUser_id_());
							players.put(player);
						}
						
						quiz.append("quiz", quizes.get(i).getName_());
						quiz.append("players", players);
						System.out.println(quiz.toString());
						json.put(quiz);
					}
					out.println(json.toString());
				} 
				catch (JSONException e) {
						e.printStackTrace();
				}
				return;
			}		
		
		if(request.getParameterMap().containsKey("InboxQuiz")){
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
	 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			String quizName=request.getParameter("quizName");
			Quiz quiz =(Quiz) em.createQuery("SELECT u FROM Quiz u where u.name_='"+quizName+"'").getSingleResult();
			try {
				JSONObject json = new JSONObject();
				String idPlayer=request.getParameter("idPlayer");
				Player player1 = (Player)em.createQuery("SELECT u FROM Player u where u.user_id_='"+idPlayer+"'").getSingleResult();
				json.append("first_name_", player1.getFirst_name_());
				json.append("last_name_", player1.getLast_name_());
				json.append("quiz", quiz.getName_());
				Query q2=em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+quiz.getQuiz_id_()+"'");
				List<Question> Questions= q2.getResultList();
				JSONArray questions=new JSONArray();
				for(int i=0;i<Questions.size();++i){
					JSONObject question = new JSONObject();
					question.append("name", Questions.get(i).getName_());
					Query q3=em.createQuery("SELECT u FROM Answer u where u.question.question_id_='"+Questions.get(i).getQuestion_id_()+"'");
					List<Answer> Answers= q3.getResultList();
					JSONArray answers=new JSONArray();
					int isCorrect=0;
					for(int ii=0;ii<Answers.size();++ii){
						JSONObject answerPlayer = new JSONObject();
						PlayersAnswer answer=(PlayersAnswer)em.createQuery("SELECT u FROM PlayersAnswer u where u.question.question_id_='"+Questions.get(i).getQuestion_id_()+"'and u.player.user_id_='"+player1.getUser_id_()+"' and u.name_='"+Answers.get(ii).getName_()+"'").getSingleResult();
						answerPlayer.append("answer", Answers.get(ii).getName_());
						answerPlayer.append("correctAnswer", Answers.get(ii).getCorrect_());
						answerPlayer.append("playerAnswer", answer.getCorrect_());
						if(answer.getCorrect_()==Answers.get(ii).getCorrect_())
							++isCorrect;	
						answers.put(answerPlayer);
					}
					if(isCorrect==Answers.size())
						question.append("correct", true);
					else
						question.append("correct", false);
					question.append("answers",answers);
					questions.put(question);
				}
				json.append("questions", questions);
				System.out.println(json.toString());
				out.println(json.toString());
			} 
			catch (JSONException e) {
					e.printStackTrace();
			}
			return;
		}
		
		if(request.getParameterMap().containsKey("inbox")){
			System.out.println("INBOX");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
	 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("SELECT u FROM Quiz u");
			List<Quiz> quizes = q.getResultList();
			try {
				JSONArray json=new JSONArray();
				for(int i=0;i<quizes.size();++i){
					JSONObject quiz = new JSONObject();
					JSONArray players=new JSONArray();
					Query q1 = em.createQuery("SELECT u FROM Player u where u.quiz.quiz_id_='"+quizes.get(i).getQuiz_id_()+"'");
					List<Player> Players = q1.getResultList();
					for(int ii=0;ii<Players.size();++ii){
						JSONObject player = new JSONObject();
						player.append("first_name_", Players.get(ii).getFirst_name_());
						player.append("last_name_", Players.get(ii).getLast_name_());
						player.append("e_mail_", Players.get(ii).getE_mail_());
						Query q2=em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+quizes.get(i).getQuiz_id_()+"'");
						List<Question> questions= q2.getResultList();
						int points=0;
						for(int iii=0;iii<questions.size();++iii){
							Query q3=em.createQuery("SELECT u FROM Answer u where u.question.question_id_='"+questions.get(iii).getQuestion_id_()+"'");
							List<Answer> answers= q3.getResultList();
							int isCorrect=0;
							for(int iiii=0;iiii<answers.size();++iiii){
								PlayersAnswer answer=(PlayersAnswer)em.createQuery("SELECT u FROM PlayersAnswer u where u.question.question_id_='"+questions.get(iii).getQuestion_id_()+"'and u.player.user_id_='"+Players.get(ii).getUser_id_()+"' and u.name_='"+answers.get(iiii).getName_()+"'").getSingleResult();
								if(answer.getCorrect_()==answers.get(iiii).getCorrect_()){
									++isCorrect;
								}
							if(isCorrect==answers.size())
								points=points+questions.get(iii).getPoints_();
							}
						}
						player.append("points", points);
						player.append("id", Players.get(ii).getUser_id_());
						players.put(player);
					}
					
					quiz.append("quiz", quizes.get(i).getName_());
					quiz.append("players", players);
					System.out.println(quiz.toString());
					json.put(quiz);
				}
				out.println(json.toString());
			} 
			catch (JSONException e) {
					e.printStackTrace();
			}
			return;
		}
		
		if(request.getParameterMap().containsKey("getQuizById")){
			String quiz_id=request.getParameter("id");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query q1 =  em.createQuery("SELECT u FROM Quiz u where u.quiz_id_='"+quiz_id+"'");
			List<Quiz> quizz=q1.getResultList();
			
			if(quizz.isEmpty())
			{
				JSONObject exists=new JSONObject();
				try {
					exists.append("exists", false);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				em.close();
				String json=exists.toString();
				out.println(json);
				return;
			}
			
			Quiz q = quizz.get(0);
			
			int max_points=0;
				
			JSONObject quiz=new JSONObject();
				try {
					quiz.append("name", q.getName_());
					
				quiz.append("photo", q.getPhoto_());
				Query qu = em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+q.getQuiz_id_()+"'");
				List<Question> resultList1 = qu.getResultList();
				JSONArray questions=new JSONArray();
				for(int i=0;i<resultList1.size();++i)
				{
					max_points=max_points+resultList1.get(i).getPoints_();
					JSONObject question=new JSONObject();
					question.append("name", resultList1.get(i).getName_());
					question.append("points", resultList1.get(i).getPoints_());
					question.append("time", resultList1.get(i).getNumber_of_seconds_());
					Query a = em.createQuery("SELECT u FROM Answer u where u.question.question_id_='"+resultList1.get(i).getQuestion_id_()+"'");
					List<Answer> resultList2 = a.getResultList();
					JSONArray answers=new JSONArray();
					for(int ii=0;ii<resultList2.size();++ii){
						JSONObject answer=new JSONObject();
						answer.append("name", resultList2.get(ii).getName_());
						answer.append("isCorrect", resultList2.get(ii).getCorrect_());
						answers.put(answer);
					}
					question.append("answers", answers);
					questions.put(question);
				}
				quiz.append("questions", questions);
				quiz.append("max_points", max_points);
				quiz.append("exists", true);
				em.close();
				String json=quiz.toString();
				//System.out.println(json);
				out.println(json);
				return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		
		if(request.getParameterMap().containsKey("getQuiz")){
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			
			Query q1 =  em.createQuery("SELECT u FROM Quiz u where u.is_active_=1");
			List<Quiz> quizActive=q1.getResultList();
			Quiz q = new Quiz();
			Query q2 =  em.createQuery("SELECT u FROM Quiz u where u.is_active_=0");
			List<Quiz> quizz=q2.getResultList();
			if(quizz.isEmpty()){
				q = quizActive.get(0);
				q.setIs_active_(true);
				em.getTransaction().begin();
				em.persist(q);
				em.getTransaction().commit();
			
			}
			else{
				q = quizz.get(0);
				q.setIs_active_(false);
				em.getTransaction().begin();
				em.persist(q);
				em.getTransaction().commit();
			}	
			int max_points=0;
				
			JSONObject quiz=new JSONObject();
				try {
					quiz.append("name", q.getName_());
				
				quiz.append("photo", q.getPhoto_());
				Query qu = em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+q.getQuiz_id_()+"'");
				List<Question> resultList1 = qu.getResultList();
				JSONArray questions=new JSONArray();
				for(int i=0;i<resultList1.size();++i)
				{
					max_points=max_points+resultList1.get(i).getPoints_();
					JSONObject question=new JSONObject();
					question.append("name", resultList1.get(i).getName_());
					question.append("points", resultList1.get(i).getPoints_());
					question.append("time", resultList1.get(i).getNumber_of_seconds_());
					Query a = em.createQuery("SELECT u FROM Answer u where u.question.question_id_='"+resultList1.get(i).getQuestion_id_()+"'");
					List<Answer> resultList2 = a.getResultList();
					JSONArray answers=new JSONArray();
					for(int ii=0;ii<resultList2.size();++ii){
						JSONObject answer=new JSONObject();
						answer.append("name", resultList2.get(ii).getName_());
						answer.append("isCorrect", resultList2.get(ii).getCorrect_());
						answers.put(answer);
					}
					question.append("answers", answers);
					questions.put(question);
				}
				quiz.append("questions", questions);
				quiz.append("max_points", max_points);
				em.close();
				String json=quiz.toString();
				//System.out.println(json);
				out.println(json);
				return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		if(request.getParameterMap().containsKey("getQuizes")){
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query q =  em.createQuery("SELECT u FROM Quiz u");
			List<Quiz> Quizes=q.getResultList();
			JSONArray quizes=new JSONArray();
			try {
				for(int i=0;i<Quizes.size();++i){	
					int max_points=0;	
					JSONObject quiz=new JSONObject();
					quiz.append("name", Quizes.get(i).getName_());
					quiz.append("photo", Quizes.get(i).getPhoto_());
					Query qu = em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+Quizes.get(i).getQuiz_id_()+"'");
					List<Question> Questions = qu.getResultList();
					JSONArray questions=new JSONArray();
					for(int ii=0;ii<Questions.size();++ii){
						max_points=max_points+Questions.get(ii).getPoints_();
						JSONObject question=new JSONObject();
						question.append("name", Questions.get(ii).getName_());
						question.append("points", Questions.get(ii).getPoints_());
						question.append("time", Questions.get(ii).getNumber_of_seconds_());
						Query a = em.createQuery("SELECT u FROM Answer u where u.question.question_id_='"+Questions.get(ii).getQuestion_id_()+"'");
						List<Answer> Answers = a.getResultList();
						JSONArray answers=new JSONArray();
						for(int iii=0;iii<Answers.size();++iii){
							JSONObject answer=new JSONObject();
							answer.append("name", Answers.get(iii).getName_());
							answer.append("isCorrect", Answers.get(iii).getCorrect_());
							answers.put(answer);
						}
						question.append("answers", answers);
						questions.put(question);
					}
					quiz.append("questions", questions);
					quiz.append("max_points", max_points);
					quizes.put(quiz);
				}
				em.close();
				String json=quizes.toString();
				System.out.println(json);
				out.println(json);
				return;
		
			}	
					 catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
		}
		if(request.getParameterMap().containsKey("quiz")){
			String quizID=request.getParameter("quiz");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			
				Quiz q = (Quiz) em.createQuery("SELECT u FROM Quiz u where u.quiz_id_='"+quizID+"'").getSingleResult();
				JSONObject quiz=new JSONObject();
				try {
					quiz.append("name", q.getName_());
				
				quiz.append("photo", q.getPhoto_());
				System.out.println("quiz1");
				Query qu = em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+q.getQuiz_id_()+"'");
				List<Question> resultList1 = qu.getResultList();
				JSONArray questions=new JSONArray();
				for(int i=0;i<resultList1.size();++i)
				{
					JSONObject question=new JSONObject();
					question.append("name", resultList1.get(i).getName_());
					question.append("points", resultList1.get(i).getPoints_());
					question.append("time", resultList1.get(i).getNumber_of_seconds_());
					Query a = em.createQuery("SELECT u FROM Answer u where u.question.question_id_='"+resultList1.get(i).getQuestion_id_()+"'");
					List<Answer> resultList2 = a.getResultList();
					JSONArray answers=new JSONArray();
					for(int ii=0;ii<resultList2.size();++ii){
						JSONObject answer=new JSONObject();
						answer.append("name", resultList2.get(ii).getName_());
						answer.append("isCorrect", resultList2.get(ii).getCorrect_());
						answers.put(answer);
					}
					question.append("answers", answers);
					questions.put(question);
				}
				quiz.append("questions", questions);
				em.close();
				String json=quiz.toString();
				System.out.println(json);
				out.println(json);
				return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
		}
			
		if(request.getParameterMap().containsKey("quizes")){
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query q;
			q= em.createQuery("SELECT u FROM Quiz u");
			List<Quiz> resultList = q.getResultList();
			JSONArray quizes=new JSONArray();
			for(int i=0;i<resultList.size();++i)
			{
				JSONObject quiz=new JSONObject();
				try {
					quiz.append("id", resultList.get(i).getQuiz_id_());
					quiz.append("name", resultList.get(i).getName_());
					Query qu = em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+resultList.get(i).getQuiz_id_()+"'");
					List<Question> resultList1 = qu.getResultList();
					quiz.append("number_of_questions", resultList1.size());
					int max_points=0;
					for(int ii=0;ii<resultList1.size();++ii)
					{
							max_points=max_points+resultList1.get(ii).getPoints_();
					}
					quiz.append("points", max_points);
					quizes.put(quiz);
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			em.close();
			String json=quizes.toString();
			out.println(json);
			return;
		}
		
		if(request.getParameterMap().containsKey("quizesEditor")){
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			String username=request.getParameter("username");
			
			Query q= em.createQuery("SELECT u FROM Quiz u  where u.editor.username_='"+username+"'");
			List<Quiz> resultList = q.getResultList();	
			JSONArray quizes=new JSONArray();
			for(int i=0;i<resultList.size();++i)
			{
				JSONObject quiz=new JSONObject();
				try {
					quiz.append("id", resultList.get(i).getQuiz_id_());
					quiz.append("name", resultList.get(i).getName_());
					Query qu = em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+resultList.get(i).getQuiz_id_()+"'");
					List<Question> resultList1 = qu.getResultList();
					quiz.append("number_of_questions", resultList1.size());
					int max_points=0;
					for(int ii=0;ii<resultList1.size();++ii)
					{
							max_points=max_points+resultList1.get(ii).getPoints_();
					}
					quiz.append("points", max_points);
					quizes.put(quiz);
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			em.close();
			String json=quizes.toString();
			out.println(json);
			return;
		}
		
		if(request.getParameterMap().containsKey("users")){
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
	 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query q2 = em.createQuery("SELECT u FROM Editor u");
			List<Editor> resultList2 = q2.getResultList();
			JSONArray json=new JSONArray();
			try {
			for(int i=0;i<resultList2.size();++i){
				JSONObject editor=new JSONObject();
				editor.append("editor_id_", resultList2.get(i).getEditor_id());
				editor.append("first_name_", resultList2.get(i).getFirst_name_());
				editor.append("last_name_", resultList2.get(i).getLast_name_());
				editor.append("e_mail_", resultList2.get(i).getE_mail());
				json.put(editor);
				} 
			}
			catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			//String json=gson.toJson(resultList2);
			out.println(json.toString());
			em.close();
	 		return;
		}
		if(request.getParameterMap().containsKey("editor")){
			String editorID=request.getParameter("editor");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Editor editor = (Editor) em.createQuery("SELECT u FROM Editor u where u.editor_id_='"+editorID+"'").getSingleResult();
			em.close();
			JSONObject json=new JSONObject();
			try {
				json.append("editor_id_", editor.getEditor_id());
				json.append("first_name_", editor.getFirst_name_());
				json.append("last_name_", editor.getLast_name_());
				json.append("username_", editor.getUsername_());
				json.append("password_", editor.getPassword_());
				json.append("e_mail_", editor.getE_mail());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(json.toString());
			return;
		}
		if(request.getParameterMap().containsKey("editorLogin")){
			String editorUsername=request.getParameter("Username");
			String editorPassword=request.getParameter("Password");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query query = em.createQuery("SELECT u FROM Editor u where u.username_='"+editorUsername+"'and u.password_='"+editorPassword+"'");
			List<Editor> resultList=query.getResultList();
			em.close();
			JSONObject json=new JSONObject();
			if(resultList.size()>0)
			{
				try {
					json.append("correct", "true");
					json.append("username", resultList.get(0).getUsername_());
					json.append("id", resultList.get(0).getEditor_id());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else{
				try {
					json.append("correct", "false");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String Editor=json.toString();
			System.out.println(Editor);
			out.println(Editor);
			return;
		}
		if(request.getParameterMap().containsKey("quizname")){
			String quizname=request.getParameter("quizname");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query query = em.createQuery("SELECT u FROM Quiz u where u.name_='"+quizname+"'");
			List<Quiz> resultList=query.getResultList();
			em.close();
			System.out.println(quizname+" "+resultList.size());
			
			JSONObject json=new JSONObject();
			try { 
				if(resultList.isEmpty()){
					json.append("exists", "false");
				}
				else{
					json.append("exists", "true");
				}
			}
			catch (JSONException e) {
					e.printStackTrace();
			}
			System.out.println(json.toString());
			out.println(json.toString());
			return;
		}
		if(request.getParameterMap().containsKey("username")){
			String username=request.getParameter("username");
			response.setContentType("application/json");
	 		response.setHeader("cache-control", "no-cache");
	 		PrintWriter out = response.getWriter();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query query = em.createQuery("SELECT u FROM Editor u where u.username_='"+username+"'");
			List<Editor> resultList=query.getResultList();
			em.close();
			System.out.println(username+" "+resultList.size());
			
			JSONObject json=new JSONObject();
			try { 
				if(resultList.isEmpty()){
					json.append("exists", "false");
				}
				else{
					json.append("exists", "true");
				}
			}
			catch (JSONException e) {
					e.printStackTrace();
			}
			System.out.println(json.toString());
			out.println(json.toString());
			return;
		}	
		request.getRequestDispatcher("/admin.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameterMap().containsKey("DeleteEditor")){
			String parameter=request.getParameter("DeleteEditor");
			int idEditor=Integer.parseInt(parameter);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Query q= em.createQuery("SELECT u FROM Quiz u where u.editor.editor_id_='"+idEditor+"'");
			List<Quiz> quizes=q.getResultList();
			for(int i=0;i<quizes.size();++i)
			{
				quizes.get(i).setEditor(null);
			
			}
			em.getTransaction().begin();
	        em.createQuery("Delete from Editor e where e.editor_id_='"+idEditor+"'").executeUpdate();
	        em.getTransaction().commit();
			System.out.println(idEditor);
			return;
		}
		
		if(request.getParameterMap().containsKey("DeleteQuiz")){
			String parameter=request.getParameter("DeleteQuiz");
			int idQuiz=Integer.parseInt(parameter);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			Quiz quiz=(Quiz) em.createQuery("SELECT u FROM Quiz u where u.quiz_id_='"+idQuiz+"'").getSingleResult();
			quiz.setEditor(null);
			em.getTransaction().begin();	
			em.createQuery("DELETE e FROM Player e where e.quiz.quiz_id_='"+idQuiz+"'").executeUpdate();
	        em.getTransaction().commit();
	        
			Query q= em.createQuery("SELECT u FROM Question u where u.quiz.quiz_id_='"+idQuiz+"'");
			List<Question> questions=q.getResultList();
			for(int i=0;i<questions.size();++i){
					em.getTransaction().begin();	
					em.createQuery("DELETE e FROM Answer e where e.question.question_id_='"+questions.get(i).getQuestion_id_()+"'").executeUpdate();
			        em.getTransaction().commit();
			        em.getTransaction().begin();	
					em.createQuery("DELETE e FROM PlayersAnswer e where e.question.question_id_='"+questions.get(i).getQuestion_id_()+"'").executeUpdate();
			        em.getTransaction().commit();
			        
			        em.getTransaction().begin();	
					em.createQuery("Delete e from Question e where e.question_id_='"+questions.get(i).getQuestion_id_()+"'").executeUpdate();
			        em.getTransaction().commit();
			}
			em.getTransaction().begin();
	        em.createQuery("Delete e from Quiz e where e.editor_id_='"+idQuiz+"'").executeUpdate();
	        em.getTransaction().commit();
			//System.out.println(idQuiz);
			return;
		}
		
		
		if(request.getParameterMap().containsKey("PlayerResults")){
			String json=request.getParameter("PlayerResults");
			//System.out.println(json);
			JSONObject PlayerResults;
			try {
				PlayerResults = new JSONObject(json);
				String quizName=PlayerResults.getString("name");
				//System.out.println(quizName);
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
				EntityManager em = emf.createEntityManager();
				JSONObject PlayerObj = PlayerResults.getJSONObject("player");
				Quiz quiz = (Quiz)em.createQuery("SELECT u FROM Quiz u where u.name_='"+quizName+"'").getSingleResult();
				//System.out.println(quiz.getName_());
				Player player=new Player();
				player.setFirst_name_(PlayerObj.getString("first_name_"));
				player.setLast_name_(PlayerObj.getString("last_name_"));
				player.setE_mail_(PlayerObj.getString("e_mail_"));
				JSONArray questions = PlayerResults.getJSONArray("questions");
					for(int i=0;i<questions.length();++i){
						JSONObject questionObject=questions.getJSONObject(i);
						String questionName=questionObject.getString("name");
						Question question = (Question)em.createQuery("SELECT u FROM Question u where u.name_='"+questionName+"' and u.quiz.name_='"+quizName+"'").getSingleResult();
						JSONArray answers = questionObject.getJSONArray("answers");
						for(int ii=0;ii<answers.length();++ii){
							JSONObject answerObject=answers.getJSONObject(ii);
							PlayersAnswer answer=new PlayersAnswer();
							answer.setName_(answerObject.getString("name"));
							if(answerObject.getString("isCorrect").equals("true"))
								answer.setCorrect_(true);
							else
								answer.setCorrect_(false);
							answer.setQuestion(question);
							answer.setPlayer(player);
							player.addPlayersAnswer(answer);
							question.addPlayersAnswer(answer);
							em.getTransaction().begin();
							em.persist(answer);
							em.getTransaction().commit();
						}
						em.getTransaction().begin();
						em.persist(question);
						em.getTransaction().commit();
					}
					quiz.addPlayer(player);
					player.setQuiz(quiz);
					//System.out.println(quiz.getName_());
					
					em.getTransaction().begin();
					em.persist(quiz);
					em.persist(player);
					em.getTransaction().commit();
					
					em.close();
				} 
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if(request.getParameterMap().containsKey("UpdateUser")){
			String json=request.getParameter("UpdateUser");
			//System.out.println(json);
			JSONObject UpdateUser;
			try {
				UpdateUser=new JSONObject(json);
				String username=UpdateUser.getString("username");
				String firstname=UpdateUser.getString("firstname");
				String lastname=UpdateUser.getString("lastname");
				String email=UpdateUser.getString("email");
				String password=UpdateUser.getString("password");
				//System.out.println(username+" "+password+" "+firstname+" "+lastname+" "+email );
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				em.createQuery("Update Editor e set e.password_='"+password+"', e.e_mail_='"+email+"', e.first_name_='"+firstname+"',e.last_name_='"+lastname+"' where e.username_='"+username+"'").executeUpdate();
				em.getTransaction().commit();
				em.close();
				emf.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		if(request.getParameterMap().containsKey("newUser")){
		String a=request.getParameter("newUser");
		JSONObject obj = null;
		try {
			obj = new JSONObject(a);
			String userName=obj.getString("username");
			String password=obj.getString("password");
			String firstName=obj.getString("firstname");
			String lastname=obj.getString("lastname");
			String email=obj.getString("email");
			Editor editor=new Editor();
			editor.SetEditor(userName,password,firstName,lastname,email);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(editor);
			em.getTransaction().commit();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

		//doGet(request, response);
		}
		if(request.getParameterMap().containsKey("newQuiz")){
			String json=request.getParameter("newQuiz");
			//System.out.println(json);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			JSONObject obj = null;
			try {
				obj = new JSONObject(json);
				Quiz quiz=new Quiz();
				quiz.setName_(obj.getString("name"));
				quiz.setPhoto_(obj.getString("photo"));
				JSONArray questions = obj.getJSONArray("question");
				for(int i=0;i<questions.length();++i){
					JSONObject questionObject=questions.getJSONObject(i);
					Question question=new Question();
					question.setName_(questionObject.getString("name"));
					question.setPoints_(questionObject.getInt("points"));
					question.setNumber_of_seconds_(questionObject.getInt("time"));
					JSONArray answers = questionObject.getJSONArray("answers");
					for(int ii=0;ii<answers.length();++ii){
						JSONObject answerObject=answers.getJSONObject(ii);
						Answer answer=new Answer();
						answer.setName_(answerObject.getString("name"));
						answer.setCorrect_(answerObject.getBoolean("isCorrect"));
						answer.setQuestion(question);
						question.addAnswer(answer);
						em.getTransaction().begin();
						em.persist(answer);
						em.getTransaction().commit();
					}
					question.setQuiz(quiz);
					quiz.addQuestion(question);
					em.getTransaction().begin();
					em.persist(question);
					em.getTransaction().commit();
				}
					em.getTransaction().begin();
					em.persist(quiz);
					em.getTransaction().commit();
				em.close();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameterMap().containsKey("newQuizEditor")){
			String json=request.getParameter("newQuizEditor");
			//System.out.println(json);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizServer");
			EntityManager em = emf.createEntityManager();
			JSONObject obj = null;
			try {
				obj = new JSONObject(json);
				Quiz quiz=new Quiz();
				quiz.setName_(obj.getString("name"));
				quiz.setPhoto_(obj.getString("photo"));
				JSONArray questions = obj.getJSONArray("question");
				for(int i=0;i<questions.length();++i){
					JSONObject questionObject=questions.getJSONObject(i);
					Question question=new Question();
					question.setName_(questionObject.getString("name"));
					question.setPoints_(questionObject.getInt("points"));
					question.setNumber_of_seconds_(questionObject.getInt("time"));
					JSONArray answers = questionObject.getJSONArray("answers");
					for(int ii=0;ii<answers.length();++ii){
						JSONObject answerObject=answers.getJSONObject(ii);
						Answer answer=new Answer();
						answer.setName_(answerObject.getString("name"));
						answer.setCorrect_(answerObject.getBoolean("isCorrect"));
						answer.setQuestion(question);
						question.addAnswer(answer);
						em.getTransaction().begin();
						em.persist(answer);
						em.getTransaction().commit();
					}
					question.setQuiz(quiz);
					quiz.addQuestion(question);
					em.getTransaction().begin();
					em.persist(question);
					em.getTransaction().commit();
				}
				String username=obj.getString("editor");
				//System.out.println(username);
				Editor editor =(Editor) em.createQuery("SELECT u FROM Editor u where u.username_='"+username+"'").getSingleResult();
				quiz.setEditor(editor);
				editor.addQuiz(quiz);
				em.getTransaction().begin();
 				em.persist(quiz);
 				em.persist(editor);
 				em.getTransaction().commit();
			em.close();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
