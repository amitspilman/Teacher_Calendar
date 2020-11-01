package TeacherCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Plan {
	
	private String Time;
	private String Title;
	private String Duration;
	private String Goal;
	private String Accessories;
	private String Opening;
	private String Budy;
	private String Summary;

	public Plan() {
		this.Time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		this.Title="";
		this.Duration="";
		this.Goal="";
		this.Accessories="";
		this.Opening="";
		this.Budy="";
		this.Summary="";
	}

	public Plan(String title, String duration, String goal, String accessories, String opening, String budy, String summary) {
		this.Time= new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		this.Title=title;
		this.Duration=duration;
		this.Goal=goal;
		this.Accessories=accessories;
		this.Opening=opening;
		this.Budy=budy;
		this.Summary=summary;
	}
	
	public Plan(String Time,String title, String duration, String goal, String accessories, String opening, String budy, String summary) {
		this.Time=Time;
		this.Title=title;
		this.Duration=duration;
		this.Goal=goal;
		this.Accessories=accessories;
		this.Opening=opening;
		this.Budy=budy;
		this.Summary=summary;
	}
	
	public Plan(Plan p) {
		this.Time=p.Time;
		this.Title=p.Title;
		this.Duration=p.Duration;
		this.Goal=p.Goal;
		this.Accessories=p.Accessories;
		this.Opening=p.Opening;
		this.Budy=p.Budy;
		this.Summary=p.Summary;
	}

	public String getPlanTime() {
		return this.Time;
	}

	public void setPlanTime(String Time) {
		this.Time = Time;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getDuration() {
		return this.Duration;
	}

	public void setDuration(String duration) {
		this.Duration = duration;
	}

	public String getGoal() {
		return this.Goal;
	}

	public void setGoal(String goal) {
		this.Goal = goal;
	}

	public String getAccessories() {
		return this.Accessories;
	}

	public void setAccessories(String accessories) {
		this.Accessories = accessories;
	}

	public String getOpening() {
		return this.Opening;
	}

	public void setOpening(String opening) {
		this.Opening = opening;
	}
	
	public String getBudy() {
		return this.Budy;
	}

	public void setBudy(String budy) {
		this.Budy = budy;
	}
	
	public String getSummary() {
		return this.Summary;
	}

	public void setSummary(String summary) {
		this.Summary = summary;
	}

	public String toString() {
		String str = "";
		str = "Plan Time: " + this.Time + "\n" + "Title: " + this.Title + "\n" +
				"Duration: " + this.Duration + "\n" + "Goal: " + this.Goal + "\n" + "Accessories: " + this.Accessories
				 +  "\n" + "Opening: " + this.Opening + "\n" + "Budy: " + this.Budy + "\n" +  "Summary: " + this.Summary;
		return str;
	}
}
