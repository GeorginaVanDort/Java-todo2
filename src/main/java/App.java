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
  
    get("/categories/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/category-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    
    post("/categories", (request, response) -> {
    //submit the form, this route is triggered, and the contents are included in the queryParams// 
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");//call queryParams() upon the request object. We pass in "name" as an argument//
      // Category newCategory = new Category(name);/create a new Category and passing in the name//
      model.put("template", "templates/category-success.vtl");//Spark renders our category-success.vtl template//
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    
    get("/categories", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();//create our model with the HashMap constructor//
      model.put("categories", Category.all());//place all Category objects in our model under the key "categories"//
      model.put("template", "templates/categories.vtl");//place categories.vtl template in model//
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    
    get("/categories/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Category category = Category.find(Integer.parseInt(request.params(":id")));
      model.put("category", category);
      model.put("template", "templates/category.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    
    get("categories/:id/tasks/new", (request, response) -> {//create a Task as a sub-directory of the category detail page//
      Map<String, Object> model = new HashMap<String, Object>();
      Category category = Category.find(Integer.parseInt(request.params(":id")));
      model.put("category", category);
      model.put("template", "templates/category-tasks-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    //unique id will be right there in the url: get("categories/:id/tasks/new")//

    post("/tasks", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Category category = Category.find(Integer.parseInt(request.queryParams("categoryId")));
      //processes the form submission//
      
      String description = request.queryParams("description");
      Task newTask = new Task(description);//creates the new task //
      
      category.addTask(newTask);//adds it to the correct category//

      model.put("category", category);//puts category in model//
      model.put("template", "templates/category-tasks-success.vtl");//displays a success page//
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
