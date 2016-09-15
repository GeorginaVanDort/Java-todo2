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
  
    //route when we click a link to see a particular Task's detail page//
    get("/tasks/:id", (request, response) -> {//:id is a placeholde//
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params(":id")));//retrieves the value currently represented by :id//
      //then retrieves the Task whose mId matches the :id and assign it to var "task"//
      model.put("task, task");//place it into model//
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    //When the corresponding task.vtl template loads, it will then display the details for that particular Task//
  }
}
