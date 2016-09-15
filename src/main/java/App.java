import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());//render template//

    get("tasks/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/task-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());//render template//

    get("/tasks", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("tasks", Task.all());//place the ArrayList of all Tasks into the model,
      model.put("template", "templates/tasks.vtl");//makes available to the template tasks.vtl//
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request,response) -> {//where the form submits to <form action="/tasks" method="post">//
      Map<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("description");//gathers info from the form//
      Task newTask = new Task(description);//creates task//
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
