package generate;

import lombok.Data;

import java.io.Serializable;

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
}