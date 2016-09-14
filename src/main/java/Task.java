import java.time.LocalDateTime;//java universal date stamp//
import java.util.ArrayList;
import java.util.List;

public class Task {
  private String mDescription;
  private boolean mCompleted; //marks task as complated or not//
  private LocalDateTime mCreatedAt; //creates an object of the LocalDateTime//
  private static List<Task> instances = new ArrayList<Task>();//Will hold a list of all objects created with the constructor//
  private int mId; //declares Id as an int//

  public Task(String description) { //Constructor. Takes the description as an argument//
    mDescription = description; //Takes description from the argument passed//
    mCompleted = false;//Auto marks as not completed//
    mCreatedAt = LocalDateTime.now();//Finds the current date//
    instances.add(this);//adds this current object to the list called "instances"//
    mId = instances.size();//Assigns an ID property which is related to the order it's added to the List.//
  }

  public String getDescription() {
    return mDescription;
  }

  public boolean isCompleted() {
    return mCompleted;
  }

  public LocalDateTime getCreatedAt() {
    return mCreatedAt;
  }

  public static List<Task> all() {
    return instances;//returns the list called "instances"//
  }

  public static void clear () {
    instances.clear();
  }//clears the list called "instances" clear() is static because clearing all Task objects will occur on the class-level not the instance-level//

  public int getId() {
    return mId;
  }

  public static Task find(int id) {
    return instances.get(id - 1);//static method to locate a Task using its mId property. This method is static because it must sift through all Task objects to find the specific one we're seeking. And, because it is static, we will call it on the entire class, like this: Task.find().//
  }
}
