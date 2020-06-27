package model;


import java.util.List;
import java.util.Vector;

public class Movie {


	public String id;
	public String title, score, title_sub, story, classid, releasetime, duration, regionid, langid;

	public Movie(String id, String title, String score, String title_sub, String story, String classid,
				 String releasetime, String duration, String regionid, String langid) {
		super();
		this.id = id;
		this.title = title;
		this.score = score;
		this.title_sub = title_sub;
		this.story = story;
		this.classid = classid;
		this.releasetime = releasetime;
		this.duration = duration;
		this.regionid = regionid;
		this.langid = langid;
	}

	public Movie(String title, String score, String title_sub, String story, String classid,
				 String releasetime, String duration, String regionid, String langid) {

		this.id = null;
		this.title = title;
		this.score = score;
		this.title_sub = title_sub;
		this.story = story;
		this.classid = classid;
		this.releasetime = releasetime;
		this.duration = duration;
		this.regionid = regionid;
		this.langid = langid;
	}

	public List<String> getMoiveList() {
		List<String> moivesList;
		moivesList = new Vector<String>();
		moivesList.add(id);
		moivesList.add(title);
		moivesList.add(score);
		moivesList.add(title_sub);
		moivesList.add(story);
		moivesList.add(classid);
		moivesList.add(releasetime);
		moivesList.add(duration);
		moivesList.add(regionid);
		moivesList.add(langid);
		return moivesList;
	}


}
