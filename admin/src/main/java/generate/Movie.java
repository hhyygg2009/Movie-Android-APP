package generate;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * movie
 *
 * @author
 */
@Data
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private String score;
    private String titleSub;
    private String story;
    private String classid;
    private String releasetime;
    private String duration;
    private String regionid;
    private String langid;
    private String custompicpos;

    public Movie(String id, String title, String score, String titleSub, String story, String classid,
                 String releasetime, String duration, String regionid, String langid) {
        super();
        this.id = Integer.getInteger(id);
        this.title = title;
        this.score = score;
        this.titleSub = titleSub;
        this.story = story;
        this.classid = classid;
        this.releasetime = releasetime;
        this.duration = duration;
        this.regionid = regionid;
        this.langid = langid;
    }

    public Movie(String title, String score, String titleSub, String story, String classid,
                 String releasetime, String duration, String regionid, String langid) {

        this.id = null;
        this.title = title;
        this.score = score;
        this.titleSub = titleSub;
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
        moivesList.add(String.valueOf(id));
        moivesList.add(title);
        moivesList.add(score);
        moivesList.add(titleSub);
        moivesList.add(story);
        moivesList.add(classid);
        moivesList.add(releasetime);
        moivesList.add(duration);
        moivesList.add(regionid);
        moivesList.add(langid);
        return moivesList;
    }
}

