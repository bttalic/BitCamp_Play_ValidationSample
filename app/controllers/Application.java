package controllers;

import javafx.geometry.Pos;
import models.Post;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;
import views.html.posts.*;
import views.html.posts.newPost;

public class Application extends Controller {

    private static Form<Post> postForm = Form.form(Post.class);

    public Result index() {
        return ok(index.render(Post.all()));
    }


    public Result newPost(){
        return ok(newPost.render(postForm));
    }

    //handles the form submit
    public Result formSubmit(){
        //get the form data from the request - do this only once
        Form<Post> binded = postForm.bindFromRequest();
        //if we have errors just return a bad request
        if(binded.hasErrors()){
            flash("error", "check your inputs");
            return badRequest(newPost.render(binded));
        } else {
            //get the object from the form, for revere take a look at someForm.fill(myObject)
            Post p = binded.get();
            p.save();
            flash("success", "post added");
            return redirect("/");
        }

    }

    /**
     * This will just validate the form for the AJAX call
     * @return ok if there are no errors or a JSON object representing the errors
     */
    public Result validateForm(){
        Form<Post> binded = postForm.bindFromRequest();
        if(binded.hasErrors()){
            return badRequest(binded.errorsAsJson());
        } else {
            return ok("we good, we good");
        }
    }

}
