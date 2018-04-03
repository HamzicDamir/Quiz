package quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;

@Entity
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.TABLE)
		private long user_id_;
		private String first_name_;
		private String last_name_;
		private String e_mail_;
		@ManyToOne(cascade = CascadeType.PERSIST)
		@JoinTable(name="PlayerQuiz",joinColumns=@JoinColumn(name="Player_ID", 
		   referencedColumnName="user_id_"), inverseJoinColumns=@JoinColumn(name="Quiz_ID",
		   referencedColumnName="quiz_id_"))
		private Quiz quiz;
		@OneToMany(cascade = CascadeType.ALL,mappedBy="player")
		private Collection<PlayersAnswer> answers;
		public Player() {
			this.first_name_ = "";
			this.last_name_ = "";
			this.e_mail_ = "";
			this.quiz = null;
			this.answers = new ArrayList<PlayersAnswer>();
		}
		
		public Player(String first_name,String last_name,String e_mail)
		{
			this.first_name_ = first_name;
			this.last_name_ = last_name;
			this.e_mail_ = e_mail;
		}
		public long getUser_id_() {
			return this.user_id_;
		}

		public void setUser_id_(long user_id_) {
			this.user_id_ = user_id_;
		}   
		public String getFirst_name_() {
			return this.first_name_;
		}

		public void setFirst_name_(String first_name_) {
			this.first_name_ = first_name_;
		}   
		public String getLast_name_() {
			return this.last_name_;
		}

		public void setLast_name_(String last_name_) {
			this.last_name_ = last_name_;
		}
		public String getE_mail_() {
			return e_mail_;
		}
		public void setE_mail_(String e_mail_) {
			this.e_mail_ = e_mail_;
		}
		public Collection<PlayersAnswer> getPlayersAnswers_() {
			return this.answers;
		}
		public void setPlayersAnswers_(ArrayList<PlayersAnswer> answers_) {
			this.answers = answers_;
		}
		public Quiz getQuiz() {
			return this.quiz;
		}
		public void setQuiz(Quiz quiz) {
			this.quiz = quiz;
		}
		public void addPlayersAnswer(PlayersAnswer answer)
		{
			this.answers.add(answer);
		}
		public void printPlayerAnswers() {
			Iterator<PlayersAnswer>iterator = this.answers.iterator();
			while(iterator.hasNext())
			{
				PlayersAnswer a=iterator.next();
				System.out.println("Odgovor:"+" "+a.getName_()+" Tacan: "+a.getCorrect_());
			}
			
		}
		
}