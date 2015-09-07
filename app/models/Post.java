package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by benjamin on 07/09/15.
 */
@Entity
public class Post extends Model {

    //indicate that this is the PK
    @Id
    public int id;


    //define that the title is required and the minimum length is 2
    @Constraints.Required(message = "Title is required")
    @Constraints.MinLength(2)
    public String title;

    @Constraints.Required
    public String content;



    public static Finder<Integer, Post> finder = new Finder<Integer, Post>(Post.class);


    public static List<Post> all(){
        return finder.all();
    }


    /**
     * This method can do custom validation logic the form will use and
     * send a error message back
     * @return error message if there is one or null if the validation is ok
     */
    public String validate(){

        if(finder.where().eq("title", this.title).findRowCount() > 0)
            return "Title must be unique";

        return null;
    }

}
