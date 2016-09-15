import java.util.List;
import java.util.ArrayList;

public class Category {
  private String mName;
  private static List<Category> instances = new ArrayList<Category>();//list of categories//
  private int mId;
  private List<Task> mTasks;//A list of tasks that belong to this category//
  
  public Category(String name) {
  mName = name;//name of category//
  instances.add(this);//adds category instances to the list of categories//
  mId = instances.size();
  mTasks = new ArrayList<Task>();//initialize an empty List of <Task>s//
  }
  
  public String getName() {
    return mName;//getter//
  }
  
  public static List<Category> all() {
    return instances;
  }
  
  public static void clear() {
    instances.clear();
  }
  
  public int getId(){
    return mId;
  }
  
  public static Category find(int id) {//This method is static because it must sift through all objects//
    return instances.get(id -1);}//instances.get() is a method called on ArrayLists//
  }
  
  public List<Task> getTasks() {
    return mTasks;
  }
  
  public void addTask(Task task) {
  mTasks.add(task);//adds task to the category's mtask ArrayList//
}
}
